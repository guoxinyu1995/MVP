package com.example.day01_register.Util;

import android.text.TextUtils;

public class NonNull {
    private static NonNull instance;

    public NonNull() {
    }

    public static NonNull getInstance() {
        if (instance == null){
            instance = new NonNull();
        }
        return instance;
    }
    //判断是否为空
    public boolean isNonNull(String name,String pass){
        return !TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pass);
    }
}
