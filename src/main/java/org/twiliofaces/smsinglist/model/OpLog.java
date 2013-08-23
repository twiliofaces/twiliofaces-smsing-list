package org.twiliofaces.smsinglist.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OpLog implements Serializable
{

   private static final long serialVersionUID = 1L;
   private Long id;
   private String operation;
   private String number;
   private String nickname;
   private Long idUser;
   private Date insertDate;

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

   public String getOperation()
   {
      return operation;
   }

   public void setOperation(String operation)
   {
      this.operation = operation;
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

   public Long getIdUser()
   {
      return idUser;
   }

   public void setIdUser(Long idUser)
   {
      this.idUser = idUser;
   }

   public Date getInsertDate()
   {
      return insertDate;
   }

   public void setInsertDate(Date insertDate)
   {
      this.insertDate = insertDate;
   }

}
