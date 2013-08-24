package org.twiliofaces.smsinglist.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.giavacms.common.annotation.ListPage;
import org.giavacms.common.annotation.OwnRepository;
import org.giavacms.common.controller.AbstractLazyController;
import org.twiliofaces.smsinglist.model.MsgIn;
import org.twiliofaces.smsinglist.repository.MsgInRepository;
import org.twiliofaces.smsinglist.service.Analyzer;
import org.twiliofaces.smsinglist.util.SmsUtils;

@Named
@SessionScoped
public class MsgInController extends
         AbstractLazyController<MsgIn>
{
   private static final long serialVersionUID = 1L;

   @Inject
   Analyzer analyzer;

   @ListPage
   protected static final String LIST_PAGE = "/msg/list.xhtml";

   @Inject
   @OwnRepository(MsgInRepository.class)
   MsgInRepository msgInRepository;

   public String reworkInline()
   {
      MsgIn msgIn = (MsgIn) getModel().getRowData();
      analyzer.checkSms(SmsUtils.toSms(msgIn));
      return listPage();
   }

}
