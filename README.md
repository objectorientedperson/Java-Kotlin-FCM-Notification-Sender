Send bulk or one message to FCM from your server. Grab the .jar from lib directory.

Usage:

```
// Add your message content in a map.
val mData = HashMap<String, Any>().apply {
                  put("message", "your_message_content_here")
                  put("extra", "your_message_extra_here")
              }

// Create payload.
val data = Message.Builder().apply {
              setData(mData)
              setPriority(Message.MessagePriority.HIGH)
              setTimeToLive(60000L)
              setTokens(arrayListOf("one_or_more_tokens_here"))
          }

// Send message.
Sender().apply {
         setServerKey("your_fcm_server_key")
         send(data.build()) {
             print(it) // Handle message here.
         }
     }
```