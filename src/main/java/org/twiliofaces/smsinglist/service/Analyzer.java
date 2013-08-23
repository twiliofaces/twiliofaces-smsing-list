package org.twiliofaces.smsinglist.service;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.twiliofaces.smsinglist.jms.operation.SendMessage2SmsSenderMDB;
import org.twiliofaces.smsinglist.model.MsgIn;
import org.twiliofaces.smsinglist.model.MsgOut;
import org.twiliofaces.smsinglist.model.Sms;
import org.twiliofaces.smsinglist.model.User;
import org.twiliofaces.smsinglist.model.enums.CommandsEnum;
import org.twiliofaces.smsinglist.repository.MsgInRepository;
import org.twiliofaces.smsinglist.repository.UserRepository;
import org.twiliofaces.smsinglist.util.MsgUtils;
import org.twiliofaces.smsinglist.util.ParserUtils;
import org.twiliofaces.smsinglist.util.SmsUtils;

@Stateless
@LocalBean
public class Analyzer
{
   @Inject
   UserRepository userRepository;

   @Inject
   MsgInRepository msgInRepository;

   @Asynchronous
   @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
   public void checkSms(Sms sms)
   {
      MsgIn msgIn = SmsUtils.toMsgIn(sms);
      // know i the number?
      User user = userRepository.findByNumber(sms.getFrom());
      MsgOut msgOut, msgOutN;
      // YES
      if (user != null)
      {
         msgInRepository.persist_withNewTx(msgIn);
         // I KNOW THE USER
         CommandsEnum commandInside = ParserUtils.containsCommand(msgIn.getTxt());
         switch (commandInside)
         {
         case ALL:
            List<String> nicknames = userRepository.getAllNicknamesNotIn(sms.getFrom());
            msgOut = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.all(nicknames),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOut);
            break;
         case CHANGE:
            String newNickname = ParserUtils.getNickname(sms.getBody());
            user.setNickname(newNickname);
            userRepository.change(user);
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.change(),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         case HELP:
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.help(),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         case INVITE:
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.comingsoon(),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         case NONE:
            List<String> numbers = userRepository.getNumbersNotIn(sms.getFrom());
            msgOut = new MsgOut(numbers, MsgUtils.said(user, msgIn.getTxt()), msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOut);
            break;
         case PAUSE:
            userRepository.pause(user);
            break;
         case PRIV:
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.comingsoon(),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         case UNPAUSE:
            userRepository.unpause(user);
            break;
         case UNSUBSCRIBE:
            userRepository.unsubscribe(user);
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.bye(user),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         default:
            System.out.println("ERRORE!!!!!!" + sms.toString());
            break;

         }
      }
      // NO!!!
      else
      {
         // I DON'T KNOW THE USER
         CommandsEnum commandInside = ParserUtils.containsCommand(msgIn.getTxt());
         switch (commandInside)
         {
         case HELP:
            msgInRepository.persist_withNewTx(msgIn);
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.help(),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         case SUBSCRIBE:
            msgInRepository.persist_withNewTx(msgIn);
            // AGGIUNGO NUOVO NUMERO E INVIO MSG CONFERMA
            String nickname = ParserUtils.getNickname(sms.getBody());
            User userN = new User(sms.getFrom(), nickname);
            userRepository.persist_withNewTx(userN);
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.welcome(userN),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         default:
            System.out.println("ERRORE!!!!!!" + sms.toString());
            break;
         }
      }

   }
}
