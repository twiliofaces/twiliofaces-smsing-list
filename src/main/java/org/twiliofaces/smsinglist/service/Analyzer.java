package org.twiliofaces.smsinglist.service;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.giavacms.common.util.FileUtils;
import org.jboss.logging.Logger;
import org.twiliofaces.smsinglist.jms.operation.SendMessage2SmsSenderMDB;
import org.twiliofaces.smsinglist.management.AppConstants;
import org.twiliofaces.smsinglist.model.MsgIn;
import org.twiliofaces.smsinglist.model.MsgOut;
import org.twiliofaces.smsinglist.model.Sms;
import org.twiliofaces.smsinglist.model.User;
import org.twiliofaces.smsinglist.model.VoiceMsg;
import org.twiliofaces.smsinglist.model.enums.CommandsEnum;
import org.twiliofaces.smsinglist.repository.MsgInRepository;
import org.twiliofaces.smsinglist.repository.SmsRepository;
import org.twiliofaces.smsinglist.repository.UserRepository;
import org.twiliofaces.smsinglist.repository.VoiceMsgRepository;
import org.twiliofaces.smsinglist.util.GoogleResponseDecoder;
import org.twiliofaces.smsinglist.util.Mp3ToFlacDecoder;
import org.twiliofaces.smsinglist.util.MsgUtils;
import org.twiliofaces.smsinglist.util.ParserUtils;
import org.twiliofaces.smsinglist.util.SmsUtils;
import org.twiliofaces.smsinglist.util.SpeechRequestor;
import org.twiliofaces.smsinglist.util.TwilioMp3Downloader;
import org.twiliofaces.smsinglist.util.google.SpeechResponse;

@Stateless
@LocalBean
public class Analyzer implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Inject
   UserRepository userRepository;

   @Inject
   MsgInRepository msgInRepository;

   @Inject
   VoiceMsgRepository voiceMsgRepository;

   @Inject
   SmsRepository smsRepository;

   Logger logger = Logger.getLogger(getClass());

   @Asynchronous
   @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
   public void checkSms(Sms sms)
   {
      MsgIn msgIn = SmsUtils.toMsgIn(sms);
      User user = userRepository.findByNumber(sms.getFrom());
      MsgOut msgOut, msgOutN;
      if (user != null)
      {

         msgInRepository.persist_withNewTx(msgIn);
         // I KNOW THE USER
         CommandsEnum commandInside = ParserUtils.containsCommand(msgIn.getTxt());
         switch (commandInside)
         {
         case ALL:
            List<String> nicknames = userRepository.getAllNicknamesNotIn(sms.getFrom());
            if (nicknames != null && !nicknames.isEmpty())
            {
               logger.info("subscriber known - ALL CMD - " + Arrays.toString(nicknames.toArray()));
               msgOut = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.all(nicknames),
                        msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOut);
            }
            else
            {
               logger.info("subscriber known - ALL CMD - NOUSERS");
               msgOut = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.noUSers(),
                        msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOut);
            }
            break;
         case WHOAMI:
            logger.info("subscriber known - WHOAMI CMD ");
            msgOut = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }),
                     MsgUtils.yourNickname(user.getNickname()),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOut);
            break;
         case CHANGE:
            String newNickname = ParserUtils.getNickname(sms.getBody());
            User oldUser = userRepository.findByNickname(newNickname);
            if (oldUser == null)
            {
               logger.info("subscriber known - CHANGE CMD");
               user.setNickname(newNickname);
               userRepository.change(user);
               msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.change(),
                        msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOutN);
            }
            else
            {
               logger.info("subscriber known - CHANGE CMD - NICKNAME ALREADY USED");
               msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.nicknameAlreadyUsed(),
                        msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOutN);

            }
            break;
         case HOWTO:
            logger.info("subscriber known - HOWTO CMD");
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.help(),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         case INVITE:
            String newNumber = ParserUtils.getInviteNumber(sms.getBody());
            User inviteUser = userRepository.findByNumber(newNumber);
            if (inviteUser == null)
            {
               logger.info("subscriber known - INVITE CMD");
               msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.invite(newNumber,
                        user.getNickname()),
                        msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOutN);
            }
            else
            {
               logger.info("subscriber known - INVITE CMD - ALREADY SUBSCRIBED");
               msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }),
                        MsgUtils.inviteUserAlreadySubscribed(),
                        msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOutN);
            }
            break;
         case NONE:
            if (msgIn.getTxt() == null || msgIn.getTxt().trim().isEmpty())
            {
               logger.info("subscriber known - NONE CMD - MSG EMPTY");
               msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.msgEmpty(),
                        msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOutN);
               break;
            }
            List<String> numbers = userRepository.getNumbersNotIn(sms.getFrom());
            if (numbers != null && numbers.size() > 0)
            {
               logger.info("subscriber known - NONE CMD");
               msgOut = new MsgOut(numbers, MsgUtils.said(user.getNickname(), msgIn.getTxt()), msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOut);
            }
            else
            {
               logger.info("subscriber known - NONE CMD - NO USERS");
               msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.noUSers(),
                        msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOutN);
            }
            break;
         case PAUSE:
            logger.info("subscriber known - PAUSE CMD");
            userRepository.pause(user);
            break;
         case PRIV:
            String[] vars = ParserUtils.getPrivateNicknameAndMsg(sms.getBody());
            if (vars != null)
            {
               User privUser = userRepository.findByNickname(vars[0]);
               if (privUser != null && privUser.isActive())
               {
                  logger.info("subscriber known - PRIV CMD");
                  msgOutN = new MsgOut(Arrays.asList(new String[] { privUser.getNumber() }), MsgUtils.said(
                           user.getNickname(),
                           vars[1]),
                           msgIn.getId());
                  SendMessage2SmsSenderMDB.execute(msgOutN);
               }
               else
               {
                  logger.info("subscriber known - PRIV CMD - NO USER WITH NICKNAME");
                  msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.noUserWithNickname(),
                           msgIn.getId());
                  SendMessage2SmsSenderMDB.execute(msgOutN);
               }
            }

            break;
         case UNPAUSE:
            logger.info("subscriber known - UNPAUSE CMD");
            userRepository.unpause(user);
            break;
         case LEAVE:
            logger.info("subscriber known - LEAVE CMD");
            userRepository.unsubscribe(user);
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.bye(user.getNickname()),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         case SUBSCRIBE:
            logger.info("subscriber known - SUBSCRIBE CMD");
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.alreadySubscribed(),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         default:
            logger.info("subscriber known - ERROR" + commandInside + " - SMS: " + sms.toString() + " "
                     + msgIn.toString());
            break;

         }
      }
      // NO!!!
      else
      {
         // I DON'T KNOW THE USER

         CommandsEnum commandInside = ParserUtils.containsCommand(msgIn.getTxt());
         System.out.println(commandInside);
         switch (commandInside)
         {
         case HOWTO:
            logger.info("new subscriber - HOWTO CMD");
            msgInRepository.persist_withNewTx(msgIn);
            msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.help(),
                     msgIn.getId());
            SendMessage2SmsSenderMDB.execute(msgOutN);
            break;
         case SUBSCRIBE:
            msgInRepository.persist_withNewTx(msgIn);
            // AGGIUNGO NUOVO NUMERO E INVIO MSG CONFERMA
            String nickname = ParserUtils.getNickname(sms.getBody());
            User oldUser = userRepository.findByNickname(nickname);
            if (oldUser == null)
            {
               logger.info("new subscriber - SUBSCRIBE CMD");
               User userN = new User(sms.getFrom(), nickname);
               userRepository.persist_withNewTx(userN);
               msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }),
                        MsgUtils.welcome(userN.getNickname()),
                        msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOutN);
            }
            else
            {
               logger.info("new subscriber - SUBSCRIBE CMD - NICKNAME ALREADY USED");
               msgOutN = new MsgOut(Arrays.asList(new String[] { sms.getFrom() }), MsgUtils.nicknameAlreadyUsed(),
                        msgIn.getId());
               SendMessage2SmsSenderMDB.execute(msgOutN);
            }
            break;
         default:
            logger.info("new subscriber - ERROR " + commandInside + " - SMS: " + sms.toString() + " "
                     + msgIn.toString());
            break;
         }
      }

   }

   @Asynchronous
   @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
   public void voice2sms(VoiceMsg voiceMsg)
   {
      voiceMsgRepository.persist_withNewTx(voiceMsg);
      String origin = System.getenv(AppConstants.DATA_FOLDER_PROPERTY)
               + "/" + AppConstants.AUDIO_FOLDER + "/" + voiceMsg.getId() + ".mp3";
      String destination = System.getenv(AppConstants.DATA_FOLDER_PROPERTY)
               + "/" + AppConstants.AUDIO_FOLDER + "/" + voiceMsg.getId() + ".flac";
      String flacCommand = System.getenv(AppConstants.DATA_FOLDER_PROPERTY)
               + "/" + AppConstants.AUDIO_FOLDER + AppConstants.FFMPEG_CMD;

      // download mp3 - convert to flac - speech to text - persiste voiceMsg and delete audio files
      try
      {
         TwilioMp3Downloader.execute(voiceMsg.getRecordingUrl(), origin);
         Mp3ToFlacDecoder.execute(flacCommand, origin, destination);
         String response = SpeechRequestor.execute(voiceMsg.getTwilioLang(), 1, destination);
         SpeechResponse speechResponse = GoogleResponseDecoder.execute(response, true);
         if (speechResponse.getStatus() == 0 && speechResponse.getHypotheses() != null
                  && speechResponse.getHypotheses().size() > 0)
         {
            String txt = speechResponse.getHypotheses().get(0).getUtterance();
            voiceMsg.setTxt(txt);
            voiceMsgRepository.update_withNewTx(voiceMsg);
            Sms sms = new Sms();
            sms.setFrom(voiceMsg.getFrom());
            sms.setTo(voiceMsg.getTo());
            sms.setBody(voiceMsg.getTxt());
            sms.setInsertDate(new Date());
            sms.setSmsSid("SM" + System.currentTimeMillis());
            smsRepository.persist_withNewTx(sms);
            checkSms(sms);
         }
         FileUtils.deleteQuietly(origin);
         FileUtils.deleteQuietly(destination);

      }
      catch (MalformedURLException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }
}
