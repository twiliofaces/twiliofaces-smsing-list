package org.twiliofaces.smsinglist.repository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.twiliofaces.smsinglist.model.OpLog;
import org.twiliofaces.smsinglist.repository.api.BaseRepository;

@Named
@Stateless
@LocalBean
public class OpLogRepository extends BaseRepository<OpLog>
{
   private static final long serialVersionUID = 1L;

   @Override
   protected String getDefaultOrderBy()
   {
      return " id ";
   }

}
