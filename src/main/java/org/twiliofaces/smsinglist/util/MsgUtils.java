package org.twiliofaces.smsinglist.util;

import java.util.Arrays;
import java.util.List;

import org.twiliofaces.smsinglist.model.User;

public class MsgUtils
{
   public static String said(User user, String txt)
   {
      return user.getNickname() + " SAID: " + txt;
   }

   public static String welcome(User user)
   {
      return "WELCOME " + user.getNickname();
   }

   public static String change()
   {
      return "NICKNAME CHANGED";
   }

   public static String invite()
   {
      return "CHOICE NICKNAME AND RESPOND WITH 'SUBSCRIBE: your_nickname'";
   }

   public static String comingsoon()
   {
      return "COMING SOON!";
   }

   public static String help()
   {
      return "...UUUHH..";
   }

   public static String bye(User user)
   {
      return "BYE BYE " + user.getNickname();
   }

   public static String all(List<String> nicknames)
   {
      return Arrays.toString(nicknames.toArray()).replace("[", "").replace("]", "");
   }

   public static void main(String[] args)
   {
      String[] nomi = { "fiorenzo", "mario" };
      System.out.println(Arrays.toString(Arrays.asList(nomi).toArray()).replace("[", "").replace("]", ""));
   }
}
