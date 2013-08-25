package org.twiliofaces.smsinglist.util;

import java.util.Arrays;
import java.util.List;

public class MsgUtils
{
   public static String said(String nickname, String txt)
   {
      return nickname + " SAID:" + txt;
   }

   public static String yourNickname(String nickname)
   {
      return "YOUR NICKNAME IS " + nickname;
   }

   public static String welcome(String nickname)
   {
      return "WELCOME " + nickname + "!";
   }

   public static String nicknameAlreadyUsed()
   {
      return "NICKNAME ALREADY USED. CHANGE IT!";
   }

   public static String alreadySubscribed()
   {
      return "YOU ALREADY SUBSCRIBED!";
   }

   public static String noUserWithNickname()
   {
      return "DON'T EXIST SUBSCRIBER WITH THIS NICKNAME ";
   }

   public static String inviteUserAlreadySubscribed()
   {
      return "YOUR FRIEND IS ALREADY SUBSCRIBED!";
   }

   public static String noUSers()
   {
      return "NO USERS IN CHAT! INVITE SOMEONE!";
   }

   public static String msgEmpty()
   {
      return "YOUR SMS IS EMPTY";
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
               "INVITE: number_to_invite - UNPAUSE, HOWTO, ALL, PRIV: nickname msg, WHOAMI";
   }

   public static String bye(String nickname)
   {
      return "BYE BYE " + nickname + "!";
   }

   public static String all(List<String> nicknames)
   {
      return Arrays.toString(nicknames.toArray()).replace("[", "").replace("]", "");
   }

}
