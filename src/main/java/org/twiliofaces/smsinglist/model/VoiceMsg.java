package org.twiliofaces.smsinglist.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VoiceMsg implements Serializable
{
   private static final long serialVersionUID = 1L;
   private Long id;
   private String twilioLang;
   private String from;
   private String to;
   private String recordingUrl;
   private String txt;
   private Date insertDate;

   public VoiceMsg()
   {
   }

   public VoiceMsg(String from, String to, String recordingUrl, String twilioLang)
   {
      this.from = from;
      this.to = to;
      this.recordingUrl = recordingUrl;
      this.insertDate = new Date();
      this.twilioLang = twilioLang;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   @Column(name = "FROMNUMBER")
   public String getFrom()
   {
      return from;
   }

   public void setFrom(String from)
   {
      this.from = from;
   }

   public String getRecordingUrl()
   {
      return recordingUrl;
   }

   public void setRecordingUrl(String recordingUrl)
   {
      this.recordingUrl = recordingUrl;
   }

   public String getTxt()
   {
      return txt;
   }

   public void setTxt(String txt)
   {
      this.txt = txt;
   }

   public Date getInsertDate()
   {
      return insertDate;
   }

   public void setInsertDate(Date insertDate)
   {
      this.insertDate = insertDate;
   }

   @Column(name = "TONUMBER")
   public String getTo()
   {
      return to;
   }

   public void setTo(String to)
   {
      this.to = to;
   }

   public String getTwilioLang()
   {
      return twilioLang;
   }

   public void setTwilioLang(String lang)
   {
      this.twilioLang = lang;
   }
}
