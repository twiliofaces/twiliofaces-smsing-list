package org.twiliofaces.smsinglist.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.twiliofaces.smsinglist.util.MsgUtils;

public class MsgUtilTest
{
   @Test
   public void test()
   {
      List<String> nomi = new ArrayList<String>();
      nomi.add("fiorenzino");
      nomi.add("mario");
      assertTrue(MsgUtils.all(nomi).equals("fiorenzino, mario"));
   }
}
