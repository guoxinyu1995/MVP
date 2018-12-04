package com.example.day01_register.Bean;

public class RegisterBean {

    private String msg;
    private String code;
    private String s = "0";
    public boolean isSuccess(){
        return code.equals(s);
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
