package com.smartral.fcm_notifier;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by @author ali on 8/9/19
 */
public class Message {

    @SerializedName("registration_ids")
    private List<String> mTokens;

    @SerializedName("data")
    private Map<String, Object> mData;

    @SerializedName("priority")
    private String mPriority;

    @SerializedName("time_to_live")
    private Long mTimeToLive = 60L;

    public Message(Builder mBuilder) {
        this.mTokens = mBuilder.mTokens;
        this.mData = mBuilder.mData;
        this.mPriority = mBuilder.mPriority.mValue;
        this.mTimeToLive = mBuilder.mTimeToLive;
    }

    public enum MessagePriority {
        HIGH("HIGH"),
        NORMAL("NORMAL");

        private String mValue;

        MessagePriority(String mValue) {
            this.mValue = mValue;
        }

        public String getmValue() {
            return mValue;
        }
    }

    class Builder {

        List<String> mTokens;
        Map<String, Object> mData = new HashMap<>();
        MessagePriority mPriority;
        Long mTimeToLive;

        public Builder setTokens(List<String> mTokens) {
            this.mTokens = mTokens;
            return this;
        }

        public Builder addData(String key, Object data) {
            this.mData.put(key, data);
            return this;
        }

        public Builder setPriority(MessagePriority mPriority) {
            this.mPriority = mPriority;
            return this;
        }

        public Builder setTimeToLive(Long mTimeToLive) {
            this.mTimeToLive = mTimeToLive;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}