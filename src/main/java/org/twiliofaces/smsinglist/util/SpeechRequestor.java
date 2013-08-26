package org.twiliofaces.smsinglist.util;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;

public class SpeechRequestor
{

   static Logger logger = Logger.getLogger(SpeechRequestor.class.getName());

   private static final String GOOGLE_RECOGNIZER_URL = "https://www.google.com/speech-api/v1/recognize?xjerr=1&client=chromium&lang=%s&pfilter=0&maxresults=%s";

   public static final String LANG_UK_ENGLISH = "en-GB";
   public static final String LANG_IT = "it-IT";

   public static void main(String[] args) throws Exception
   {
      execute(LANG_IT, 10, "src/main/resources/secondo.flac");
   }

   public static String execute(String lang, int maxResults, String fileName) throws Exception
   {
      if (lang == null || lang.isEmpty())
         lang = LANG_UK_ENGLISH;
      String ulrl = String.format(GOOGLE_RECOGNIZER_URL, lang, maxResults);
      logger.info(ulrl);
      HttpClient httpclient = new DefaultHttpClient();
      HttpPost httppost = new HttpPost(ulrl);
      httppost.addHeader("Content-Type", "audio/x-flac; rate=8000");
      MultipartEntity reqEntity = new MultipartEntity(
               HttpMultipartMode.BROWSER_COMPATIBLE);
      FileBody bin = new FileBody(
               new File(fileName));
      reqEntity.addPart("attachment_field", bin);

      httppost.setEntity(reqEntity);

      logger.info("executing request " + httppost.getRequestLine());
      HttpResponse response = httpclient.execute(httppost);
      HttpEntity resEntity = response.getEntity();
      if (resEntity != null)
      {
         return EntityUtils.toString(resEntity);
      }
      return null;
   }
}
