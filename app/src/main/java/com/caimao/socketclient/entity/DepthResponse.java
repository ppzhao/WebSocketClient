package com.caimao.socketclient.entity;

/**
 * Created by ppz on 2016/8/25.
 */
public class DepthResponse {

    /**
     * success : true
     * data : {"buy":"40.49,4958.0|40.48,9970.0|40.47,29595.0|40.46,37110.0|40.45,48135.0","sell":"40.5,2439.0|40.51,4545.0|40.52,3384.0|40.53,6556.0|40.54,3277.0"}
     * code : 200
     * channel : depth_AG.NJS
     * event : depth
     * param : {"symbol":"AG.NJS"}
     * version : 0.0.1
     */

    private boolean success;
    /**
     * buy : 40.49,4958.0|40.48,9970.0|40.47,29595.0|40.46,37110.0|40.45,48135.0
     * sell : 40.5,2439.0|40.51,4545.0|40.52,3384.0|40.53,6556.0|40.54,3277.0
     */

    private DataBean data;
    private int code;
    private String channel;
    private String event;
    /**
     * symbol : AG.NJS
     */

    private ParamBean param;
    private String version;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        private String buy;
        private String sell;

        public String getBuy() {
            return buy;
        }

        public void setBuy(String buy) {
            this.buy = buy;
        }

        public String getSell() {
            return sell;
        }

        public void setSell(String sell) {
            this.sell = sell;
        }
    }

    public static class ParamBean {
        private String symbol;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }
}
