package org.twiliofaces.smsinglist.test;

import org.twiliofaces.smsinglist.util.GoogleResponseDecoder;
import org.twiliofaces.smsinglist.util.Mp3ToFlacDecoder;
import org.twiliofaces.smsinglist.util.SpeechRequestor;
import org.twiliofaces.smsinglist.util.TwilioMp3Downloader;

public class Speech2TxtTest
{
   static String mp3Url = "https://api.twilio.com/2010-04-01/Accounts/AC2999a90e54ebe0bdab34fab15f241040/Recordings/RE481acb7ff1ec1d50c6d70fe3c631cf3a";
   static String origin = "src/main/resources/secondo.mp3";
   static String destination = "src/main/resources/secondo.flac";

   public static void main(String[] args) throws Exception
   {
      TwilioMp3Downloader.execute(mp3Url, origin);
      Mp3ToFlacDecoder.execute("ffmpeg -i", origin, destination);
      String response = SpeechRequestor.execute(SpeechRequestor.LANG_IT, 1, destination);
      GoogleResponseDecoder.execute(response, true);
   }
}
