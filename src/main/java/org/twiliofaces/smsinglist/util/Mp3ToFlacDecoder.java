package org.twiliofaces.smsinglist.util;

import java.io.File;

import org.jboss.logging.Logger;

public class Mp3ToFlacDecoder
{
   static Logger logger = Logger.getLogger(Mp3ToFlacDecoder.class.getName());

   public static boolean execute(String flacCommand, String origin, String destination)
   {
      Process p;
      if (!new File(origin).exists())
      {
         logger.info("origin file not exists!");
      }
      if (new File(destination).exists())
      {
         logger.info("destination file exists: i will delete before create a new file!");
         new File(destination).delete();
      }
      try
      {
         String command = flacCommand + " " + origin + " " + destination;
         logger.info("executing command " + command);
         p = Runtime.getRuntime().exec(command);
         int exitVal = p.waitFor();
         // System.out.println("Exited with error code " + exitVal);
         if (exitVal == 0)
            return true;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return false;
   }

   public static void main(String[] args)
   {
      String origin = "src/main/resources/prova.mp3";
      String destination = "src/main/resources/prova.flac";
      String flacCommand = "ffmpeg -i";
      System.out.println(execute(flacCommand, origin, destination));
   }

}
