package com.caimao.socketclient.entity;

/**
 * Created by ppz on 2016/8/25.
 */
public class TickerResponse {

    /**
     * success : true
     * data : {"open":40.6,"high":40.71,"low":40.3,"preClose":40.63,"vol":4.8325276E7,"lastVol":996,"last":40.49,"turnover":1.96127083059E9,"change":-0.14,"rate":-0.34,"settle":0,"previousSettle":0,"rate5":-0.04256,"highLimit":36.567}
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
     * vol : 4.8325276E7
     * lastVol : 996.0
     * last : 40.49
     * turnover : 1.96127083059E9
     * change : -0.14
     * rate : -0.34
     * settle : 0.0
     * previousSettle : 0.0
     * rate5 : -0.04256
     * highLimit : 36.567
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
        private double open;
        private double high;
        private double low;
        private double preClose;
        private double vol;
        private double lastVol;
        private double last;
        private double turnover;
        private double change;
        private double rate;
        private double settle;
        private double previousSettle;
        private double rate5;
        private double highLimit;

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getPreClose() {
            return preClose;
        }

        public void setPreClose(double preClose) {
            this.preClose = preClose;
        }

        public double getVol() {
            return vol;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }

        public double getLastVol() {
            return lastVol;
        }

        public void setLastVol(double lastVol) {
            this.lastVol = lastVol;
        }

        public double getLast() {
            return last;
        }

        public void setLast(double last) {
            this.last = last;
        }

        public double getTurnover() {
            return turnover;
        }

        public void setTurnover(double turnover) {
            this.turnover = turnover;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getSettle() {
            return settle;
        }

        public void setSettle(double settle) {
            this.settle = settle;
        }

        public double getPreviousSettle() {
            return previousSettle;
        }

        public void setPreviousSettle(double previousSettle) {
            this.previousSettle = previousSettle;
        }

        public double getRate5() {
            return rate5;
        }

        public void setRate5(double rate5) {
            this.rate5 = rate5;
        }

        public double getHighLimit() {
            return highLimit;
        }

        public void setHighLimit(double highLimit) {
            this.highLimit = highLimit;
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
