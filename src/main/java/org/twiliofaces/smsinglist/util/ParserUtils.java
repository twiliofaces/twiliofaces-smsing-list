package org.twiliofaces.smsinglist.util;

import org.twiliofaces.smsinglist.model.enums.CommandsEnum;

public class ParserUtils
{
   static String DOUBLE_P = ":";

   public static CommandsEnum containsCommand(String txt)
   {
      // SUBSCRIBE
      if (txt.trim().startsWith(CommandsEnum.SUBSCRIBE.name() + DOUBLE_P))
         return CommandsEnum.SUBSCRIBE;
      // UNSUBSCRIBE
      if (txt.trim().startsWith(CommandsEnum.UNSUBSCRIBE.name()))
         return CommandsEnum.UNSUBSCRIBE;
      // INVITE
      if (txt.trim().startsWith(CommandsEnum.INVITE.name() + DOUBLE_P))
         return CommandsEnum.INVITE;
      // PAUSE
      if (txt.trim().startsWith(CommandsEnum.PAUSE.name()))
         return CommandsEnum.PAUSE;
      // UNPAUSE
      if (txt.trim().startsWith(CommandsEnum.UNPAUSE.name()))
         return CommandsEnum.UNPAUSE;

      // NONE
      return CommandsEnum.NONE;
   }

   public static String getNickname(String txt)
   {
      if (txt.contains(CommandsEnum.SUBSCRIBE.name()))
         return txt.substring(txt.trim().indexOf(CommandsEnum.SUBSCRIBE.name() + DOUBLE_P) + 1).trim();
      if (txt.contains(CommandsEnum.CHANGE.name()))
         return txt.substring(txt.trim().indexOf(CommandsEnum.CHANGE.name() + DOUBLE_P) + 1).trim();
      return null;
   }
}
