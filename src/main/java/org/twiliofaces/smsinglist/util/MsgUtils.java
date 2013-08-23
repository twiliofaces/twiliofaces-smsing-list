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

   public static String comingsoon()
   {
      return "COMING SOON!";
   }

   public static String invite(String number, String nickame)
   {
      return nickame + " INVITE YOU! CHOICE NICKNAME AND RESPOND TO THIS SMS WITH 'SUBSCRIBE: your_nickname'";
   }

   public static String help()
   {
      return "CMD LIST: CHANGE: new_nick_name - SUBSCRIBE: nickname - LEAVE, PAUSE, " +
               "INVITE: number_to_invite - UNPAUSE, HOWTO, ALL, PRIV: nickname msg";
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
