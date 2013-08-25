package org.twiliofaces.smsinglist.test;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.twiliofaces.smsinglist.util.ParserUtils;

public class ParserTest
{
   public static void main(String[] args)
   {
      String body = " SUBSCRIBE:fiorenzino asdsa ";
      System.out.println(ParserUtils.getNickname(body));

      body = " CHANGE:flower ";
      System.out.println(ParserUtils.getNickname(body));

      body = " INVITE: +393922274929 ";
      System.out.println(ParserUtils.getInviteNumber(body));

      evaluate(" PRIV:fiorenzino ciao", "(PRIV:)\\s*(\\S+)(.*)");
      String[] res = ParserUtils.getPrivateNicknameAndMsg(" PRIV:fiorenzino ciao");
      System.out.println(Arrays.toString(res));

      evaluate(" INVITE: +3922274929", "(INVITE:)\\s*(.*)");
      System.out.println(ParserUtils.getInviteNumber(" INVITE: +3922274929"));
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
