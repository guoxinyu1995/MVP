package com.example.day07_mvp02.presenter;

import com.example.day07_mvp02.bean.UserBean;
import com.example.day07_mvp02.model.ImodelImpl;
import com.example.day07_mvp02.model.MyCallBack;
import com.example.day07_mvp02.view.Iview;


/**
 * @author GXY
 */
public class IpresenterImpl implements Ipresender {
    private Iview mIview;
    private ImodelImpl imodel;

    public IpresenterImpl(Iview iview) {
        mIview = iview;
        imodel = new ImodelImpl();
    }

    @Override
    public void staderRequest(String url, String params) {
        imodel.requestData(url, null, new MyCallBack<UserBean>() {
            @Override
            public void setData(UserBean data) {
                if(data==null || !data.isSuccess()){
                    mIview.error(data.getStatus());
                }else{
                    mIview.success(data);
                }

            }
        });
    }
    public void onDetach(){
        if(imodel!=null){
            imodel = null;
        }
        if (mIview!=null){
            mIview = null;
        }
    }
}
