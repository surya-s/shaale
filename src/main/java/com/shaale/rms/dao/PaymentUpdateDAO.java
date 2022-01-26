package com.shaale.rms.dao;


import com.shaale.rms.entity.Payment;

public class PaymentUpdateDAO {
    public enum Action {
        UPDATE,
        DELETE
    }

    private Action action;
    private Payment data;

    public PaymentUpdateDAO() {}

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Payment getData() {
        return data;
    }

    public void setData(Payment data) {
        this.data = data;
    }

}
