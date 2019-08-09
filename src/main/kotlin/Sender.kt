
import com.google.gson.Gson
import org.apache.commons.io.IOUtils
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by @author ali on 8/9/19
 */
class Sender {
    private var mServerKey : String? = null

    /**
     *
     */
    fun setServerKey(mKey : String?) {
        mServerKey = mKey
    }

    /**
     *
     */
    fun send(mPayload: Message, func:(mResult: String?)-> Unit) {
        // Send.
        val url = URL("https://fcm.googleapis.com/fcm/send")
        val conn = (url.openConnection() as HttpURLConnection).apply {
            setRequestProperty("Authorization", "key=$mServerKey")
            setRequestProperty("Content-Type", "application/json")
            requestMethod = "POST"
            doOutput = true
        }

        val mMessage = Gson().toJson(mPayload)
        print(mMessage)

        // Send GCM message content.
        conn.outputStream.apply {
            write(mMessage.toByteArray())
            flush()
        }

        // Read GCM response.
        val inputStream = conn.inputStream
        val resp = IOUtils.toString(inputStream)
        func(resp)
    }
}