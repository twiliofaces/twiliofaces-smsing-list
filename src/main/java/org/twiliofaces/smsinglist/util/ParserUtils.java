package org.twiliofaces.smsinglist.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
      Pattern pattern = Pattern.compile("(INVITE:)\\s*(.*)");
      Matcher matcher = pattern.matcher(txt.trim());
      if (matcher.find() && matcher.groupCount() == 2)
         return matcher.group(2);
      return null;
   }

   public static String getNickname(String txt)
   {
      if (txt.toUpperCase().contains(CommandsEnum.SUBSCRIBE.name()))
      {
         Pattern pattern = Pattern.compile("(SUBSCRIBE:)\\s*(.*)");
         Matcher matcher = pattern.matcher(txt.trim());
         if (matcher.find() && matcher.groupCount() == 2)
            return matcher.group(2);
         return null;
      }
      else if (txt.toUpperCase().contains(CommandsEnum.CHANGE.name()))
      {
         Pattern pattern = Pattern.compile("(CHANGE:)\\s*(.*)");
         Matcher matcher = pattern.matcher(txt.trim());
         if (matcher.find() && matcher.groupCount() == 2)
            return matcher.group(2);
         return null;
      }
      return null;
   }

   public static String[] getPrivateNicknameAndMsg(String txt)
   {
      String regEx = "(PRIV:)\\s*(\\S+)(.*)";
      Pattern pattern = Pattern.compile(regEx);
      Matcher matcher = pattern.matcher(txt.trim());
      if (matcher.find() && matcher.groupCount() > 2)
         return new String[] { matcher.group(2), matcher.group(3) };
      return null;
   }

   public static void main(String[] args)
   {
      String body = " SUBSCRIBE:fiorenzino asdsa ";
      System.out.println(getNickname(body));

      body = " CHANGE:flower ";
      System.out.println(getNickname(body));

      body = " INVITE: +393922274929 ";
      System.out.println(getInviteNumber(body));

      evaluate(" PRIV:fiorenzino ciao", "(PRIV:)\\s*(\\S+)(.*)");
      String[] res = getPrivateNicknameAndMsg(" PRIV:fiorenzino ciao");
      System.out.println(Arrays.toString(res));

      evaluate(" INVITE: +3922274929", "(INVITE:)\\s*(.*)");
      System.out.println(getInviteNumber(" INVITE: +3922274929"));
   }

   private static void evaluate(String line, String regEx)
   {
      Pattern pattern = Pattern.compile(regEx);
      Matcher matcher = pattern.matcher(line);
      if (matcher.find())
      {
         for (int i = 0; i <= matcher.groupCount(); i++)
         {
            System.out.println(i + ": " + matcher.group(i));
         }
      }
      else
      {
         System.out.println("NOT FOUND: " + regEx + " IN: " + line);
      }
   }
}
