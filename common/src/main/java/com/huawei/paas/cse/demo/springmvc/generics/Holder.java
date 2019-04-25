package com.huawei.paas.cse.demo.springmvc.generics;

public class Holder<T> {
    private int status;

    private T data;

    public Holder() {

    }

    public Holder(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
