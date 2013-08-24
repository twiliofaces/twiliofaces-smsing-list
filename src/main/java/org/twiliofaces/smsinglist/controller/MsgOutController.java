package org.twiliofaces.smsinglist.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.giavacms.common.annotation.ListPage;
import org.giavacms.common.annotation.OwnRepository;
import org.giavacms.common.controller.AbstractLazyController;
import org.twiliofaces.smsinglist.model.MsgOut;
import org.twiliofaces.smsinglist.repository.MsgOutRepository;

@Named
@SessionScoped
public class MsgOutController extends
         AbstractLazyController<MsgOut>
{
   private static final long serialVersionUID = 1L;

   @ListPage
   protected static final String LIST_PAGE = "/msg/msgout.xhtml";

   @Inject
   @OwnRepository(MsgOutRepository.class)
   MsgOutRepository msgOutRepository;

}
