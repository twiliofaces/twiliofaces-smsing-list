package org.twiliofaces.smsinglist.controller;

import java.util.Arrays;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.giavacms.common.annotation.EditPage;
import org.giavacms.common.annotation.ListPage;
import org.giavacms.common.annotation.OwnRepository;
import org.giavacms.common.controller.AbstractController;
import org.twiliofaces.annotations.configuration.TwilioNumber;
import org.twiliofaces.smsinglist.jms.operation.SendMessage2SmsSenderMDB;
import org.twiliofaces.smsinglist.model.MsgOut;
import org.twiliofaces.smsinglist.model.Sms;
import org.twiliofaces.smsinglist.repository.SmsRepository;
import org.twiliofaces.smsinglist.service.Analyzer;

@Named
@SessionScoped
public class SmsController extends AbstractController<Sms>
{
   private static final long serialVersionUID = 1L;

   @Inject
   Analyzer analyzer;

   @Inject
   @TwilioNumber
   String twilioNumber;

   @ListPage
   protected static final String LIST_PAGE = "/sms/list.xhtml";

   @EditPage
   protected static final String EDIT_PAGE = "/sms/send.xhtml";

   @Inject
   @OwnRepository(SmsRepository.class)
   SmsRepository smsRepository;

   public String prepareSms()
   {
      setElement(new Sms());
      getElement().setTo(twilioNumber);
      return editPage();
   }

   public String sendSmsInternally()
   {
      getElement().setSmsSid("SM" + System.currentTimeMillis());
      getElement().setInsertDate(new Date());
      smsRepository.persist(getElement());
      analyzer.checkSms(getElement());
      return listPage();
   }

   public String sendSmsReally()
   {
      MsgOut msgOut = new MsgOut(Arrays.asList(new String[] { getElement().getFrom() }), getElement().getBody(),
               null);
      SendMessage2SmsSenderMDB.execute(msgOut);
      return listPage();
   }
}
