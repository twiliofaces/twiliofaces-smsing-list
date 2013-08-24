package org.twiliofaces.smsinglist.producer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.twiliofaces.smsinglist.model.User;
import org.twiliofaces.smsinglist.repository.UserRepository;

@Named
@SessionScoped
public class PropertiesProducer
         implements Serializable
{
   private static final long serialVersionUID = 1L;

   Logger logger = Logger.getLogger(getClass());
   private SelectItem[] userItems = null;

   @Inject
   UserRepository userRepository;

   @Produces
   @Named
   public SelectItem[] getUserItems()
   {
      List<SelectItem> userItemsList = new ArrayList<SelectItem>();
      userItemsList.add(new SelectItem(null, "user..."));
      List<User> users = userRepository.getAllList();
      if (users != null && users.size() > 0)
      {
         for (User user : users)
         {
            userItemsList.add(new SelectItem(user.getNumber(), user.getNickname()));
         }
         userItems = userItemsList
                  .toArray(new SelectItem[] {});
      }
      return userItems;
   }

   public void reset()
   {
      userItems = null;
   }
}
