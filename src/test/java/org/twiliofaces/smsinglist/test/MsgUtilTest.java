package org.twiliofaces.smsinglist.test;

import java.util.ArrayList;
import java.util.List;

import org.twiliofaces.smsinglist.util.MsgUtils;

public class MsgUtilTest
{
   public static void main(String[] args)
   {
      List<String> nomi = new ArrayList<String>();
      nomi.add("fiorenzino");
      nomi.add("mario");
      System.out.println(MsgUtils.all(nomi));
   }
}
