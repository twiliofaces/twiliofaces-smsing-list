package org.twiliofaces.smsinglist.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.giavacms.common.annotation.ListPage;
import org.giavacms.common.annotation.OwnRepository;
import org.giavacms.common.controller.AbstractLazyController;
import org.twiliofaces.smsinglist.model.VoiceMsg;
import org.twiliofaces.smsinglist.repository.VoiceMsgRepository;
import org.twiliofaces.smsinglist.service.Analyzer;

@Named
@SessionScoped
public class VoiceMsgController extends
         AbstractLazyController<VoiceMsg>
{
   private static final long serialVersionUID = 1L;

   @Inject
   Analyzer analyzer;

   @ListPage
   protected static final String LIST_PAGE = "/voice/list.xhtml";

   @Inject
   @OwnRepository(VoiceMsgRepository.class)
   VoiceMsgRepository voiceMsgRepository;

   public String reworkInline()
   {
      VoiceMsg voiceMsg = (VoiceMsg) getModel().getRowData();
      voiceMsg.setId(null);
      analyzer.voice2sms(voiceMsg);
      return listPage();
   }

}
