package com.example.day07_mvp02.model;

import android.widget.Toast;

import com.example.day07_mvp02.Util.NetUtil;
import com.example.day07_mvp02.bean.UserBean;

/**
 * @author GXY
 */
public class ImodelImpl implements Imodel {
    private MyCallBack myCallBack;
    @Override
    public void requestData(String url, String params, final MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        NetUtil.getIntance().getRequest(url, UserBean.class, new NetUtil.CallBack<UserBean>() {
            @Override
            public void onSuccess(UserBean o) {
                    myCallBack.setData(o);
            }
        });
    }
}
