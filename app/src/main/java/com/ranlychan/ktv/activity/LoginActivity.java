package com.ranlychan.ktv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ranlychan.ktv.R;
import com.ranlychan.ktv.service.impl.LoginService;

import java.util.LinkedHashMap;

public class LoginActivity extends BaseActivity {
    private EditText etUserName;
    private EditText etPwd;
    private Button btnLogin;
    private LinkedHashMap<String,String> param;
    private Handler handler;

    private final int LOGIN_ERROR = 0;
    private final int LOGIN_SUCCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initHandler();
        initView();

    }

    private void initHandler(){
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case LOGIN_ERROR:
                        Log.d("LoginActivity","登录失败！");
                        Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                        break;
                    case LOGIN_SUCCESS:
                        Log.d("LoginActivity","登录成功！");
                        Intent intent = new Intent(LoginActivity.this,ViewPagerActivityMain.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        };
    }

    private void initView(){
        etUserName = findViewById(R.id.et_login_activity_username);
        etPwd = findViewById(R.id.et_login_activity_pwd);
        btnLogin = findViewById(R.id.btn_login_activity_confirm);

        param = new LinkedHashMap<>();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param.put("username",etUserName.getText().toString().trim());
                param.put("password",etPwd.getText().toString().trim());

                LoginService.getLoginService(getApplicationContext()).login(param, new LoginService.OnLoginListener() {
                    @Override
                    public void onLoginComplete(Object msg) {

                    }

                    @Override
                    public void onLoginError(Throwable throwable) {

                    }
                });
            }
        });
    }
}