package com.caimao.socketclient.entity;

/**
 * Created by ppz on 2016/8/25.
 */
public class SocketResponseBase {

    /**
     * success : true
     * data : {"open":40.6,"high":40.71,"low":40.3,"preClose":40.63,"vol":4.8246214E7,"lastVol":488,"last":40.52,"turnover":1.95806828605E9,"change":-0.11,"rate":-0.27,"settle":0,"previousSettle":0,"rate5":-0.04185,"highLimit":36.567}
     * code : 200
     * channel : ticker_AG.NJS
     * event : ticker
     * param : {"symbol":"AG.NJS"}
     * version : 0.0.1
     */

    private boolean success;
    /**
     * open : 40.6
     * high : 40.71
     * low : 40.3
     * preClose : 40.63
     * vol : 4.8246214E7
     * lastVol : 488.0
     * last : 40.52
     * turnover : 1.95806828605E9
     * change : -0.11
     * rate : -0.27
     * settle : 0.0
     * previousSettle : 0.0
     * rate5 : -0.04185
     * highLimit : 36.567
     */

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
