package org.twiliofaces.smsinglist.repository;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.twiliofaces.smsinglist.model.User;
import org.twiliofaces.smsinglist.repository.api.BaseRepository;

@Named
@Stateless
@LocalBean
public class UserRepository extends BaseRepository<User>
{
   private static final long serialVersionUID = 1L;

   @Override
   protected String getDefaultOrderBy()
   {
      return " nickname ";
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public User persist_withNewTx(User user)
   {
      return super.persist(user);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public boolean pause(User user)
   {
      user.setActive(false);
      return super.update(user);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public boolean change(User user)
   {
      return super.update(user);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public boolean unpause(User user)
   {
      user.setActive(true);
      return super.update(user);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public boolean unsubscribe(User user)
   {

      return super.delete(user.getId());
   }

   public User findByNumber(String number)
   {
      try
      {
         @SuppressWarnings("unchecked")
         List<User> users = (List<User>) getEm().createQuery(
                  "select U FROM "
                           + User.class.getSimpleName() + " U where U.number = :NUMBER AND U.active = :ACTIVE")
                  .setParameter("NUMBER", number)
                  .setParameter("ACTIVE", true)
                  .getResultList();
         if (users != null && users.size() == 1)
            return users.get(0);
      }
      catch (Exception e)
      {
         logger.error(e.getMessage(), e);
      }
      return null;
   }

   public List<String> getNumbersNotIn(String number)
   {
      try
      {
         @SuppressWarnings("unchecked")
         List<String> list = (List<String>) getEm().createQuery(
                  "select distinct U.number FROM "
                           + User.class.getSimpleName() + " U where U.number != :NUMBER AND U.active = :ACTIVE")
                  .setParameter("ACTIVE", true)
                  .setParameter("NUMBER", number)
                  .getResultList();
         return list;
      }
      catch (Exception e)
      {
         logger.error(e.getMessage(), e);
      }
      return null;
   }

   public List<String> getAllNicknamesNotIn(String number)
   {
      try
      {
         @SuppressWarnings("unchecked")
         List<String> list = (List<String>) getEm().createQuery(
                  "select distinct U.nickname FROM "
                           + User.class.getSimpleName() + " U where U.number != :NUMBER AND U.active = :ACTIVE")
                  .setParameter("ACTIVE", true)
                  .setParameter("NUMBER", number)
                  .getResultList();
         return list;
      }
      catch (Exception e)
      {
         logger.error(e.getMessage(), e);
      }
      return null;
   }

}
