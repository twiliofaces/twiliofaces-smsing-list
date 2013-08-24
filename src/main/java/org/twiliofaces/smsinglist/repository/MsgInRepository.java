package org.twiliofaces.smsinglist.repository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.twiliofaces.smsinglist.model.MsgIn;
import org.twiliofaces.smsinglist.repository.api.BaseRepository;

@Named
@Stateless
@LocalBean
public class MsgInRepository extends BaseRepository<MsgIn>
{
   private static final long serialVersionUID = 1L;

   @Override
   protected String getDefaultOrderBy()
   {
      return " id desc ";
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public MsgIn persist_withNewTx(MsgIn object)
   {
      return super.persist(object);
   }

}
