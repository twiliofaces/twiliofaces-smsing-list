package org.twiliofaces.smsinglist.util;

import java.util.Date;

import org.twiliofaces.smsinglist.model.MsgIn;
import org.twiliofaces.smsinglist.model.Sms;

public class SmsUtils
{

   public static MsgIn toMsgIn(Sms sms)
   {
      MsgIn msgIn = new MsgIn(sms.getFrom(), sms.getTo(), sms.getBody());
      return msgIn;
   }

   public static Sms toSms(MsgIn msgIn)
   {
      Sms sms = new Sms();
      sms.setTo(msgIn.getTo());
      sms.setFrom(msgIn.getFrom());
      sms.setBody(msgIn.getTxt());
      return sms;
   }

   public static void valorize(Sms sms, String key, String value)
   {
      sms.setInsertDate(new Date());
      if ("SmsSid".equals(key))
      {
         sms.setSmsSid(value);
      }
      else if ("AccountSid".equals(key))
      {
         sms.setAccountSid(value);
      }
      else if ("From".equals(key))
      {
         sms.setFrom(value);
      }
      else if ("To".equals(key))
      {
         sms.setTo(value);
      }
      else if ("Body".equals(key))
      {
         sms.setBody(value);
      }
      // else if ("FromCity".equals(key))
      // {
      // sms.setFromCity(value);
      // }
      // else if ("FromState".equals(key))
      // {
      // sms.setFromState(value);
      // }
      // else if ("FromZip".equals(key))
      // {
      // sms.setFromZip(value);
      // }
      else if ("FromCountry".equals(key))
      {
         sms.setFromCountry(value);
      }
      else if ("ToCity".equals(key))
      {
         sms.setToCity(value);
      }
      else if ("ToState".equals(key))
      {
         sms.setToState(value);
      }
      else if ("ToZip".equals(key))
      {
         sms.setToZip(value);
      }
      else if ("ToCountry".equals(key))
      {
         sms.setToCountry(value);
      }
   }
}
