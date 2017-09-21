package com.fiap.arduinoexample;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by logonrm on 21/09/2017.
 */

public class ArduinoResponse {

    @SerializedName("channel")
    protected  ArduinoChannel channel;

    protected List<ArduinoFeed> feeds;

    public List<ArduinoFeed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<ArduinoFeed> feeds) {
        this.feeds = feeds;
    }

    public ArduinoChannel getChannel() {
        return channel;
    }

    public void setChannel(ArduinoChannel channel) {
        this.channel = channel;
    }

    public class ArduinoChannel{
        @SerializedName("id")
        protected int id;

        @SerializedName("field1")
        protected String field1;

        @SerializedName("field2")
        protected String field2;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }
    }

    public class ArduinoFeed{
        @SerializedName("field1")
        protected String field1;

        @SerializedName("field2")
        protected String field2;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }
    }

}
