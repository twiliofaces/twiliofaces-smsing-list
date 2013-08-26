package org.twiliofaces.smsinglist.repository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.twiliofaces.smsinglist.model.VoiceMsg;
import org.twiliofaces.smsinglist.repository.api.BaseRepository;

@Named
@Stateless
@LocalBean
public class VoiceMsgRepository extends BaseRepository<VoiceMsg>
{
   private static final long serialVersionUID = 1L;

   @Override
   protected String getDefaultOrderBy()
   {
      return " id desc ";
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public VoiceMsg persist_withNewTx(VoiceMsg object)
   {
      return super.persist(object);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public boolean update_withNewTx(VoiceMsg object)
   {
      return super.update(object);
   }

}
