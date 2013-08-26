package org.twiliofaces.smsinglist.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TwilioMp3Downloader
{

   public static void execute(String url, String file) throws MalformedURLException, IOException
   {
      URLConnection conn = new URL(url).openConnection();
      InputStream is = conn.getInputStream();

      OutputStream outstream = new FileOutputStream(new File(file));
      byte[] buffer = new byte[4096];
      int len;
      while ((len = is.read(buffer)) > 0)
      {
         outstream.write(buffer, 0, len);
      }
      outstream.close();
   }

   public static void main(String[] args)
   {
      String MP3 = "https://api.twilio.com/2010-04-01/Accounts/AC2999a90e54ebe0bdab34fab15f241040/Recordings/REcb837c226ed5e4116ebe4f8c23ec7b36";
      try
      {
         execute(MP3, "src/main/resources/prova.mp3");
      }
      catch (MalformedURLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
