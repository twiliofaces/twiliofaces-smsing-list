package org.twiliofaces.smsinglist.model;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MsgOut implements Serializable
{

   private static final long serialVersionUID = 1L;
   private Long id;
   private String to;
   private String txt;
   private Date insertDate;
   private Long msgInId;
   private String sid;
   private List<String> numbers;

   public MsgOut()
   {
   }

   public MsgOut(String sid, String to, String txt, Long msgInId)
   {
      this.txt = txt;
      this.msgInId = msgInId;
      this.insertDate = new Date();
      this.to = to;
      this.sid = sid;

   }

   public MsgOut(List<String> numbers, String txt, Long msgInId)
   {
      this.numbers = numbers;
      this.txt = txt;
      this.msgInId = msgInId;
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

   @Column(name = "TONUMBER")
   public String getTo()
   {
      return to;
   }

   public void setTo(String to)
   {
      this.to = to;
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

   public Long getMsgInId()
   {
      return msgInId;
   }

   public void setMsgInId(Long msgInId)
   {
      this.msgInId = msgInId;
   }

   public String getSid()
   {
      return sid;
   }

   public void setSid(String sid)
   {
      this.sid = sid;
   }

   @Transient
   public List<String> getNumbers()
   {
      if (numbers == null)
         this.numbers = new ArrayList<String>();
      return numbers;
   }

   public void setNumbers(List<String> numbers)
   {
      this.numbers = numbers;
   }

   public MsgOut addNumber(String number)
   {
      getNumbers().add(number);
      return this;
   }

}
