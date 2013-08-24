package org.twiliofaces.smsinglist.util;

import org.twiliofaces.smsinglist.model.enums.CommandsEnum;

public class ParserUtils
{
   static String COLON = ":";

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
            if (txt.toUpperCase().trim().startsWith(cmd.name() + COLON))
               return cmd;
            break;
         case PAUSE:
         case UNPAUSE:
         case ALL:
         case HOWTO:
         case LEAVE:
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
               txt.trim().indexOf(CommandsEnum.INVITE.name() + COLON)
                        + (CommandsEnum.INVITE.name() + COLON).length()).trim();
   }

   public static String getNickname(String txt)
   {
      if (txt.toUpperCase().contains(CommandsEnum.SUBSCRIBE.name()))
         return txt.trim().substring(
                  txt.trim().indexOf(CommandsEnum.SUBSCRIBE.name() + COLON)
                           + (CommandsEnum.SUBSCRIBE.name() + COLON).length()).trim();
      if (txt.toUpperCase().contains(CommandsEnum.CHANGE.name()))
         return txt.trim().substring(
                  txt.trim().indexOf(CommandsEnum.CHANGE.name() + COLON)
                           + (CommandsEnum.CHANGE.name() + COLON).length()).trim();
      return null;
   }
}
