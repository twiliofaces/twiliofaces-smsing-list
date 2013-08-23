package org.twiliofaces.smsinglist.util;

import org.twiliofaces.smsinglist.model.enums.CommandsEnum;

public class ParserUtils
{
   static String DOUBLE_P = ":";

   public static CommandsEnum containsCommand(String txt)
   {
      for (CommandsEnum cmd : CommandsEnum.values())
      {
         switch (cmd)
         {
         case SUBSCRIBE:
         case INVITE:
         case PRIV:
         case CHANGE:
            if (txt.toUpperCase().trim().startsWith(cmd.name() + DOUBLE_P))
               return cmd;
            break;
         case PAUSE:
         case UNPAUSE:
         case ALL:
         case HELP:
         case UNSUBSCRIBE:
            if (txt.toUpperCase().trim().startsWith(cmd.name()))
               return cmd;
            break;
         }
      }
      return CommandsEnum.NONE;
   }

   public static String getInviteNumber(String txt)
   {
      return txt.substring(
               txt.trim().indexOf(CommandsEnum.INVITE.name() + DOUBLE_P)
                        + (CommandsEnum.INVITE.name() + DOUBLE_P).length()).trim();
   }

   public static String getNickname(String txt)
   {
      if (txt.toUpperCase().contains(CommandsEnum.SUBSCRIBE.name()))
         return txt.substring(
                  txt.trim().indexOf(CommandsEnum.SUBSCRIBE.name() + DOUBLE_P)
                           + (CommandsEnum.SUBSCRIBE.name() + DOUBLE_P).length()).trim();
      if (txt.toUpperCase().contains(CommandsEnum.CHANGE.name()))
         return txt.substring(
                  txt.trim().indexOf(CommandsEnum.CHANGE.name() + DOUBLE_P)
                           + (CommandsEnum.SUBSCRIBE.name() + DOUBLE_P).length()).trim();
      return null;
   }
}
