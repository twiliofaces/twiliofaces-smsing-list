package org.twiliofaces.smsinglist.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable
{

   private static final long serialVersionUID = 1L;
   private Long id;
   private String number;
   private String nickname;
   private Date insertDate;
   private Date lastMessage;
   private boolean active;

   public User(String number, String nickname)
   {
      this.number = number;
      this.nickname = nickname;
      this.insertDate = new Date();
      this.active = true;
   }

   public User()
   {
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

   public String getNumber()
   {
      return number;
   }

   public void setNumber(String number)
   {
      this.number = number;
   }

   public String getNickname()
   {
      return nickname;
   }

   public void setNickname(String nickname)
   {
      this.nickname = nickname;
   }

   public Date getInsertDate()
   {
      return insertDate;
   }

   public void setInsertDate(Date insertDate)
   {
      this.insertDate = insertDate;
   }

   public Date getLastMessage()
   {
      return lastMessage;
   }

   public void setLastMessage(Date lastMessage)
   {
      this.lastMessage = lastMessage;
   }

   public boolean isActive()
   {
      return active;
   }

   public void setActive(boolean active)
   {
      this.active = active;
   }

}
