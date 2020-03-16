package com.pensource.android.advice.api.model;

public class AdviceResponse {

    private Slip slip;

    public Slip getSlip() {
        return slip;
    }

    public void setSlip(Slip slip) {
        this.slip = slip;
    }

    public static class Slip {
        private String advice;
        private int slip_id;

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }

        public int getSlip_id() {
            return slip_id;
        }

        public void setSlip_id(int slip_id) {
            this.slip_id = slip_id;
        }
    }

}
