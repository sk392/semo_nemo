package com.semonemo.latte.toyouproject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.kakao.auth.Session;
import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.util.SharedPreferenceManager;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String name = SharedPreferenceManager.getInstance().getPrefStringData(SharedPreferenceManager.USER_NAME);
        Toast.makeText(getApplicationContext(),"안녕하세요"+name + SharedPreferenceManager.getInstance().getPrefStringData(SharedPreferenceManager.test),Toast.LENGTH_SHORT).show();
        Session.getCurrentSession().close();
    }
}
