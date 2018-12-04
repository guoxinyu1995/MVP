package com.example.day07_mvp02.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.day07_mvp02.R;
import com.example.day07_mvp02.adaper.PersonAdaper;
import com.example.day07_mvp02.bean.UserBean;
import com.example.day07_mvp02.presenter.IpresenterImpl;

import me.maxwin.view.XListView;

/**
 * @author GXY
 */
public class MainActivity extends AppCompatActivity implements Iview{

    private XListView listView;
    private int mPage;
    private String url = "http://i.jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page=%d&custom_fields=thumb_c,views&dev=1";
    private PersonAdaper adaper;
    private IpresenterImpl ipresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipresenter = new IpresenterImpl(this);
        initView();
    }

    private void initView() {
        mPage = 1;
        //获取资源id
        listView = findViewById(R.id.x_list);
        //创建适配器
        adaper = new PersonAdaper(MainActivity.this);
        listView.setAdapter(adaper);
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mPage =1;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        initData();
    }
    private void initData() {
        ipresenter.staderRequest(String.format(url,mPage),null);
    }
    @Override
    public void success(Object o) {
        UserBean bean = (UserBean) o;
        if(mPage==1){
            adaper.setmData(bean.getPosts());
        }else{
            adaper.addmData(bean.getPosts());
        }
        mPage++;
        listView.stopRefresh();
        listView.stopLoadMore();
    }

    @Override
    public void error(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
    }
}
