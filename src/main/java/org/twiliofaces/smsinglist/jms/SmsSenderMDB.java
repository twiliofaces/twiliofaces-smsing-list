package org.twiliofaces.smsinglist.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.commons.lang.SerializationUtils;
import org.twiliofaces.request.TwilioSmsSender;
import org.twiliofaces.smsinglist.management.AppConstants;
import org.twiliofaces.smsinglist.model.MsgOut;
import org.twiliofaces.smsinglist.repository.MsgOutRepository;

import com.twilio.sdk.TwilioRestException;

@MessageDriven(name = "SmsSenderMDB", activationConfig = {
         @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
         @ActivationConfigProperty(propertyName = "destination", propertyValue = AppConstants.SEND_SMS_Q),
         @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
         @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "20"),
         @ActivationConfigProperty(propertyName = "transactionTimeout", propertyValue = "3600"),
         @ActivationConfigProperty(propertyName = "dLQMaxResent", propertyValue = "0") })
public class SmsSenderMDB implements MessageListener
{

   @Inject
   TwilioSmsSender twilioSmsSender;

   @Inject
   MsgOutRepository msgOutRepository;

   public void onMessage(Message message)
   {
      MsgOut msgOut = null;
      MapMessage mess = (MapMessage) message;
      try
      {
         byte[] msgByte = (byte[]) mess.getObject(AppConstants.MSG_OUT);
         msgOut = (MsgOut) SerializationUtils.deserialize(msgByte);
         // SerializeUtils.deserialize(msgByte);
         for (String to : msgOut.getNumbers())
         {
            String sid = twilioSmsSender.setTo(to).setBody(msgOut.getTxt()).send();
            msgOut.setFrom(twilioSmsSender.getFrom());
            msgOut.setTo(twilioSmsSender.getTo());
            msgOut.setSid(sid);
            msgOutRepository.persist(msgOut);
         }
      }
      catch (JMSException e)
      {
         e.printStackTrace();
      }
      catch (TwilioRestException e)
      {
         e.printStackTrace();
      }

   }
}
