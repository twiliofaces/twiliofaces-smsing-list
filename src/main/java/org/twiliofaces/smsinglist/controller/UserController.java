package org.twiliofaces.smsinglist.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.giavacms.common.annotation.EditPage;
import org.giavacms.common.annotation.ListPage;
import org.giavacms.common.annotation.OwnRepository;
import org.giavacms.common.controller.AbstractLazyController;
import org.twiliofaces.smsinglist.model.User;
import org.twiliofaces.smsinglist.repository.UserRepository;

@Named
@SessionScoped
public class UserController extends
         AbstractLazyController<User>
{
   private static final long serialVersionUID = 1L;

   @ListPage
   protected static final String LIST_PAGE = "/users/list.xhtml";

   @EditPage
   protected static final String EDIT_PAGE = "/users/edit.xhtml";

   @Inject
   @OwnRepository(UserRepository.class)
   UserRepository usrRepository;

   public String pauseInline()
   {
      User user = (User) getModel().getRowData();
      user.setActive(false);
      if (getRepository().update(user))
      {
         setModel(null);
      }
      return listPage();
   }

   public String unpauseInline()
   {
      User user = (User) getModel().getRowData();
      user.setActive(true);
      if (getRepository().update(user))
      {
         setModel(null);
      }
      return listPage();
   }

}
