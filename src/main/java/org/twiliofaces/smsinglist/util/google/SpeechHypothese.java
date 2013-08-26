package org.twiliofaces.smsinglist.util.google;
import java.io.Serializable;

public class SpeechHypothese implements Serializable
{
   private static final long serialVersionUID = 1L;
   private String utterance;
   private double confidence;

   public String getUtterance()
   {
      return utterance;
   }

   public void setUtterance(String utterance)
   {
      this.utterance = utterance;
   }

   public double getConfidence()
   {
      return confidence;
   }

   public void setConfidence(double confidence)
   {
      this.confidence = confidence;
   }

   @Override
   public String toString()
   {
      return "SpeechHypothese [utterance=" + utterance + ", confidence=" + confidence + "]";
   }

}
