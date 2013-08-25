package org.twiliofaces.smsinglist.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.twiliofaces.smsinglist.util.ParserUtils;

public class ParserTest
{
   @Test
   public void nicknameTest()
   {
      String body = " SUBSCRIBE:fiorenzino asdsa ";
      assertFalse(ParserUtils.getNickname(body).equals("fiorenzino"));

      body = " SUBSCRIBE:fiorenzino ";
      assertTrue(ParserUtils.getNickname(body).equals("fiorenzino"));

      body = " subscribe:fiorenzino ";
      assertTrue(ParserUtils.getNickname(body).equals("fiorenzino"));
   }

   @Test
   public void changeTest()
   {
      String body = " CHANGE:flower ";
      assertTrue(ParserUtils.getNickname(body).equals("flower"));

      body = " change:flower ";
      assertTrue(ParserUtils.getNickname(body).equals("flower"));
   }

   @Test
   public void inviteTest()
   {
      String body = " INVITE: +393922274929 ";
      assertTrue(ParserUtils.getInviteNumber(body).equals("+393922274929"));

      body = " invite: +393922274929 ";
      assertTrue(ParserUtils.getInviteNumber(body).equals("+393922274929"));
   }

   @Test
   public void privateTest()
   {
      String[] res = ParserUtils.getPrivateNicknameAndMsg(" PRIV:fiorenzino ciao");
      assertTrue(res[0].equals("fiorenzino"));
      assertTrue(res[1].equals(" ciao"));
   }
}
