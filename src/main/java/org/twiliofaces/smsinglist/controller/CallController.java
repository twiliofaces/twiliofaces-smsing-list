package org.twiliofaces.smsinglist.controller;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.annotations.notification.CallSid;
import org.twiliofaces.annotations.notification.Digits;
import org.twiliofaces.annotations.notification.From;
import org.twiliofaces.annotations.notification.RecordingUrl;
import org.twiliofaces.annotations.notification.To;
import org.twiliofaces.annotations.scope.TwilioScope;
import org.twiliofaces.smsinglist.model.User;
import org.twiliofaces.smsinglist.model.VoiceMsg;
import org.twiliofaces.smsinglist.repository.UserRepository;
import org.twiliofaces.smsinglist.service.Analyzer;

@TwilioScope
@Named
public class CallController implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Inject
   UserRepository userRepository;

   @Inject
   Analyzer analyzer;

   @Inject
   @CallSid
   String callSid;

   @Inject
   @From
   Instance<String> from;

   @Inject
   @To
   Instance<String> to;

   @Inject
   @RecordingUrl
   Instance<String> recordingUrl;

   @Inject
   @Digits
   Instance<String> digits;

   private String language = "en-GB";
   private User user;

   public CallController()
   {
      System.out.println("TwilioScopeController: " + new Date() + " - "
               + getClass());
   }

   public String getCallSid()
   {
      return callSid;
   }

   public boolean isKnown()
   {
      this.user = userRepository.findByNumber(from.get());
      if (user != null)
         return true;
      return false;
   }

   public String getLanguageQuestion()
   {
      return "Welcome " + user.getNickname() + "!  Push one for italian language, push two for english language?";
   }

   public String getRecordingMessage()
   {
      if (digits.get().equals("1"))
      {
         language = "it-IT";
         return "Hai dieci secondi per il tuo messaggio!";
      }
      else
      {
         language = "en-GB";
         return "You have ten seconds for your message!";
      }
   }

   public String getByeMessage()
   {
      if (language.equals("it-IT"))
         return "ciao ciao!";
      return "bye bye";
   }

   public String getLanguage()
   {
      return language;
   }

   public void setLanguage(String language)
   {
      this.language = language;
   }

   public void sendMessage()
   {
      VoiceMsg voiceMsg = new VoiceMsg(from.get(), to.get(), recordingUrl.get(), language);
      analyzer.voice2sms(voiceMsg);
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }
}
