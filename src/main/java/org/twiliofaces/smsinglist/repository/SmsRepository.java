package org.twiliofaces.smsinglist.repository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.twiliofaces.smsinglist.model.Sms;
import org.twiliofaces.smsinglist.repository.api.BaseRepository;

@Named
@Stateless
@LocalBean
public class SmsRepository extends BaseRepository<Sms>
{
   private static final long serialVersionUID = 1L;

   @Override
   protected String getDefaultOrderBy()
   {
      return " smsSid desc ";
   }

}
