package org.twiliofaces.smsinglist.repository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.twiliofaces.smsinglist.model.MsgOut;
import org.twiliofaces.smsinglist.repository.api.BaseRepository;

@Named
@Stateless
@LocalBean
public class MsgOutRepository extends BaseRepository<MsgOut>
{
   private static final long serialVersionUID = 1L;

   @Override
   protected String getDefaultOrderBy()
   {
      return " id ";
   }

   public boolean persistMultiOut(MsgOut msgOut)
   {
      for (String number : msgOut.getNumbers())
      {
         MsgOut msgOutP = new MsgOut(msgOut.getSid(), number, msgOut.getTxt(), msgOut.getMsgInId());
         persist(msgOutP);
      }
      return true;
   }
}
