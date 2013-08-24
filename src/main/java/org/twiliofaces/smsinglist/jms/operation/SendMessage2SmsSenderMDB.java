package org.twiliofaces.smsinglist.jms.operation;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.lang.SerializationUtils;
import org.jboss.logging.Logger;
import org.twiliofaces.smsinglist.management.AppConstants;
import org.twiliofaces.smsinglist.model.MsgOut;

public class SendMessage2SmsSenderMDB
{

   Logger logger = Logger.getLogger(getClass());

   public static boolean execute(MsgOut msgOut)
   {
      Connection connection = null;
      Session session = null;
      try
      {
         Context ic = new InitialContext();
         ConnectionFactory cf = (ConnectionFactory) ic.lookup("JmsXA");
         Queue queue = (Queue) ic.lookup(AppConstants.SEND_SMS_Q);
         connection = cf.createConnection();
         session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
         MessageProducer publisher = session.createProducer(queue);
         connection.start();

         MapMessage msg = session.createMapMessage();
         // CREO OBJ SERIALIZABLE

         msg.setObject(AppConstants.MSG_OUT, SerializationUtils.serialize(msgOut));
         // SerializeUtils.serialize(msgOut));
         publisher.send(msg);
         publisher.send(msg);
         return true;

      }
      catch (Exception e)
      {
         e.printStackTrace();
         return false;
      }
      finally
      {
         if (session != null)
         {
            try
            {
               session.close();
            }
            catch (JMSException e)
            {
               e.printStackTrace();
            }
         }
         if (connection != null)
         {
            try
            {
               connection.close();
            }
            catch (JMSException e)
            {
               e.printStackTrace();
            }
         }
      }
   }
}
