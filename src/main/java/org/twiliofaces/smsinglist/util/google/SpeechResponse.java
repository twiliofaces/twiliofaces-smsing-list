package org.twiliofaces.smsinglist.util.google;
import java.io.Serializable;
import java.util.List;

public class SpeechResponse implements Serializable
{
   private static final long serialVersionUID = 1L;
   private int status;
   private String id;
   private List<SpeechHypothese> hypotheses;

   public int getStatus()
   {
      return status;
   }

   public void setStatus(int status)
   {
      this.status = status;
   }

   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public List<SpeechHypothese> getHypotheses()
   {
      return hypotheses;
   }

   public void setHypotheses(List<SpeechHypothese> hypotheses)
   {
      this.hypotheses = hypotheses;
   }

   @Override
   public String toString()
   {
      return "SpeechResponse [status=" + status + ", id=" + id + ", hypotheses=" + hypotheses + "]";
   }

}
