package org.twiliofaces.smsinglist.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.logging.Logger;
import org.twiliofaces.smsinglist.util.google.SpeechHypothese;
import org.twiliofaces.smsinglist.util.google.SpeechResponse;

public class GoogleResponseDecoder
{

   static Logger logger = Logger.getLogger(GoogleResponseDecoder.class.getName());

   public static SpeechResponse execute(String response, boolean print)
   {
      logger.info("executing google response deconding");
      ObjectMapper mapper = new ObjectMapper();
      try
      {
         SpeechResponse value = mapper.readValue(response, SpeechResponse.class);
         if (print)
         {
            logger.info(value.getStatus());
            logger.info(value.getId());
            for (SpeechHypothese hypo : value.getHypotheses())
            {
               System.out.println(hypo);
            }
         }
         return value;
      }
      catch (JsonParseException e)
      {
         e.printStackTrace();
      }
      catch (JsonMappingException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      return null;
   }
}
