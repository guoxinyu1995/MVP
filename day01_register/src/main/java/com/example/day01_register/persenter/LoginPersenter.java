package com.example.day01_register.persenter;

import android.content.Intent;
import android.widget.Toast;

import com.example.day01_register.Bean.RegisterBean;
import com.example.day01_register.Util.NetUtil;
import com.example.day01_register.ui.activity.Iview;


public class LoginPersenter {
    /**
     *  持有view的实例
     */
    private Iview mIview;
    public LoginPersenter(Iview iview) {
        mIview = iview;
    }
    //提交
    public void submit(String registerUrl, String names, String passwords) {
        NetUtil.getIntance().getRequest(String.format(registerUrl,names,passwords), RegisterBean.class, new NetUtil.CallBack<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean o) {
                if(o!=null && o.isSuccess()){
                    //登录成功
                    //通过view的实例，把数据回调给view
                    mIview.success(o.getMsg());
                }else{
                    //登录失败
                    //通过view的实例，把数据回调给view
                    mIview.error(o.getMsg());
                }
            }
        });
    }
    public void datechView(){
        mIview = null;
    }
}
