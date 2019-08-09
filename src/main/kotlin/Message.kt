import com.google.gson.annotations.SerializedName

/**
 * Created by @author ali on 8/9/19
 */
class Message(mBuilder: Builder) {

    @SerializedName("registration_ids")
    private var mTokens: List<String>? = null

    @SerializedName("data")
    private var mData: Map<String, Any>? = null

    @SerializedName("priority")
    private var mPriority: String? = null

    @SerializedName("time_to_live")
    private var mTimeToLive: Long = 60L

    init {
        this.mTokens = mBuilder.mTokens
        this.mData = mBuilder.mData
        this.mPriority = mBuilder.mPriority?.mValue
        this.mTimeToLive = mBuilder.mTimeToLive
    }

    class Builder {

        internal var mTokens: List<String>? = null
        internal var mData: Map<String, Any>? = null
        internal var mPriority: MessagePriority? = null
        internal var mTimeToLive: Long = 60L

        fun setTokens(mTokens: List<String>) : Message.Builder {
            this.mTokens = mTokens
            return this
        }

        fun setData(mData: Map<String, Any>?) : Message.Builder {
            this.mData = mData
            return this
        }

        fun setTimeToLive(mTimeToLive: Long) : Message.Builder {
            this.mTimeToLive = mTimeToLive
            return this
        }

        fun setPriority(mPriority: MessagePriority?) : Message.Builder {
            this.mPriority = mPriority
            return this
        }

        fun build() : Message {
            return Message(this)
        }
    }

    enum class MessagePriority(val mValue: String) {
        HIGH("HIGH"),
        NORMAL("NORMAL")
    }
}