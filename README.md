twiliofaces-smsing-list
=======================

The idea is simple: *create a sms list, like traditional mailing list*.
.. SUBSCRIBE a mailinglist ...choose a nickname..send/receive all messages delivered from all subscribers.

So, to try twiliofaces-smsing-list, you need a twilio number which can send and receive sms (we can't in italy, but i used a new york number to try my idea)

Below the initial list of commands:

- *SUBSCRIBE* (to start, send a sms containing SUBSCRIBE:nickname where nickname is your prefered nickname)
- *LEAVE* (send this text to unsubscribe from the sms list)
- *CHANGE* (to change your nickname, send a sms containing CHANGE:newNickname where newNickname is your new nickname)
- *PAUSE* (send this text to stop the receiving of sms - you are still in the sms list) 
- *UNPAUSE* (send this text to re-start the receiving of sms)  
- *INVITE* (send this text to invite someone to subscribe the sms list)   
- *HOWTO* (send this text to receive the list of commands)    
- *ALL* (send this text to know the list of subscribers)    


After the subscribe message, you will receive "WELCOME fiorenzino". after that time you can send sms to all sms-list subscribers.
They will receive sms which start "fiorenzino SAID: " + your message.

Coming soon a "PRIV" command to send sms visible only to a single nickname, with this syntax:
PRIV: nickname msg
