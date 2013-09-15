package org.twiliofaces.smsinglist.jms;

import java.util.Enumeration;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ResourceAdapter;
import org.jboss.logging.Logger;
import org.twiliofaces.smsinglist.model.Sms;
import org.twiliofaces.smsinglist.repository.SmsRepository;
import org.twiliofaces.smsinglist.service.Analyzer;
import org.twiliofaces.smsinglist.util.SmsUtils;

@ResourceAdapter("twiliofaces-sms-ra-0.0.1-SNAPSHOT.rar")
@MessageDriven
public class SmsReceiverMDB implements MessageListener
{

   @Inject
   Analyzer analyzer;

   @Inject
   SmsRepository smsRepository;

   Logger logger = Logger.getLogger(getClass());

   public void onMessage(Message message)
   {
      logger.info("we received a new twilio sms message!");
      Sms sms = new Sms();
      if (message instanceof MapMessage)
      {
         MapMessage mess = (MapMessage) message;
         try
         {
            Enumeration<?> e = mess.getMapNames();
            while (e.hasMoreElements())
            {
               String key = (String) e.nextElement();
               String value = mess.getString(key);
               logger.info(key + ": " + value);
               SmsUtils.valorize(sms, key, value);
            }
         }
         catch (JMSException e1)
         {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         smsRepository.persist(sms);
         analyzer.checkSms(sms);
      }

   }
}
