package com.example.day01_register.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.day01_register.Bean.RegisterBean;
import com.example.day01_register.R;
import com.example.day01_register.Util.NetUtil;
import com.example.day01_register.Util.NonNull;
import com.example.day01_register.persenter.LoginPersenter;

public class BeginActivity extends AppCompatActivity implements Iview{
    private String beginUrl = "http://120.27.23.105/user/reg?mobile=%s&password=%s";
    private EditText name;
    private EditText password;
    private Button btn_register;
    private ImageButton imageButton;
    private LoginPersenter persenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        initView();
        //创建一个Presenter实例
        initPresenter();
    }
    /**
     * 绑定Presenter
     */
    private void initPresenter() {
        //把view传给presenter进行绑定
        persenter = new LoginPersenter(this);
    }
    private void initView() {
        imageButton = findViewById(R.id.btn_return);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        btn_register = findViewById(R.id.btn_register);
        //注册
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值
                String names = name.getText().toString();
                String passwords = password.getText().toString();
                if(NonNull.getInstance().isNonNull(names,passwords)){
                    //通过presenter的实例，调用presenter中的方法
                    persenter.submit(beginUrl,names,passwords);
                }else{
                    Toast.makeText(BeginActivity.this,"手机号或密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //接受到了结果，进行数据展示
    @Override
    public void success(Object o) {
        String s = String.valueOf(o);
        Toast.makeText(BeginActivity.this,s,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(BeginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    //接受到了结果，进行数据展示
    @Override
    public void error(String str) {
        Toast.makeText(BeginActivity.this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.datechView();
    }
}
