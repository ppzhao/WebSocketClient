package com.caimao.socketclient.entity;

import java.util.List;

/**
 * Created by ppz on 2016/8/25.
 */
public class KlineResponse {

    /**
     * success : true
     * data : ["1472122260|1472122260|40.53|40.54|40.52|40.52|11586.0|469576.28|-0.01|-0.02|40.53"]
     * code : 200
     * channel : kline_AG.NJS_0
     * event : kline
     * param : {"symbol":"AG.NJS","kType":"0"}
     * version : 0.0.1
     * volHandUnit : 1
     */

    private boolean success;
    private int code;
    private String channel;
    private String event;
    /**
     * symbol : AG.NJS
     * kType : 0
     */

    private ParamBean param;
    private String version;
    private int volHandUnit;
    private List<String> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVolHandUnit() {
        return volHandUnit;
    }

    public void setVolHandUnit(int volHandUnit) {
        this.volHandUnit = volHandUnit;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public static class ParamBean {
        private String symbol;
        private String kType;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getKType() {
            return kType;
        }

        public void setKType(String kType) {
            this.kType = kType;
        }
    }
}
