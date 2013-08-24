package org.twiliofaces.smsinglist.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sms implements Serializable
{
   private static final long serialVersionUID = 1L;
   private String smsSid;
   private String accountSid;
   private String smsStatus;
   private String body;
   private String toCity;
   private String toZip;
   private String toState;
   private String to;
   private String toCountry;
   private String fromCountry;
   private String smsMessageSid;
   private String from;
   private String apiVersion;
   private Date insertDate;

   @Id
   public String getSmsSid()
   {
      return smsSid;
   }

   public void setSmsSid(String smsSid)
   {
      this.smsSid = smsSid;
   }

   public String getAccountSid()
   {
      return accountSid;
   }

   public void setAccountSid(String accountSid)
   {
      this.accountSid = accountSid;
   }

   public String getSmsStatus()
   {
      return smsStatus;
   }

   public void setSmsStatus(String smsStatus)
   {
      this.smsStatus = smsStatus;
   }

   public String getBody()
   {
      return body;
   }

   public void setBody(String body)
   {
      this.body = body;
   }

   public String getToCity()
   {
      return toCity;
   }

   public void setToCity(String toCity)
   {
      this.toCity = toCity;
   }

   public String getToZip()
   {
      return toZip;
   }

   public void setToZip(String toZip)
   {
      this.toZip = toZip;
   }

   public String getToState()
   {
      return toState;
   }

   public void setToState(String toState)
   {
      this.toState = toState;
   }

   @Column(name = "smsTo")
   public String getTo()
   {
      return to;
   }

   public void setTo(String to)
   {
      this.to = to;
   }

   public String getToCountry()
   {
      return toCountry;
   }

   public void setToCountry(String toCountry)
   {
      this.toCountry = toCountry;
   }

   public String getFromCountry()
   {
      return fromCountry;
   }

   public void setFromCountry(String fromCountry)
   {
      this.fromCountry = fromCountry;
   }

   public String getSmsMessageSid()
   {
      return smsMessageSid;
   }

   public void setSmsMessageSid(String smsMessageSid)
   {
      this.smsMessageSid = smsMessageSid;
   }

   @Column(name = "smsFrom")
   public String getFrom()
   {
      return from;
   }

   public void setFrom(String from)
   {
      this.from = from;
   }

   public String getApiVersion()
   {
      return apiVersion;
   }

   public void setApiVersion(String apiVersion)
   {
      this.apiVersion = apiVersion;
   }

   public Date getInsertDate()
   {
      return insertDate;
   }

   public void setInsertDate(Date insertDate)
   {
      this.insertDate = insertDate;
   }

   @Override
   public String toString()
   {
      return "Sms [smsSid=" + smsSid + ", accountSid=" + accountSid + ", smsStatus=" + smsStatus + ", body=" + body
               + ", toCity=" + toCity + ", toZip=" + toZip + ", toState=" + toState + ", to=" + to + ", toCountry="
               + toCountry + ", fromCountry=" + fromCountry + ", smsMessageSid=" + smsMessageSid + ", from=" + from
               + ", apiVersion=" + apiVersion + "]";
   }

}
