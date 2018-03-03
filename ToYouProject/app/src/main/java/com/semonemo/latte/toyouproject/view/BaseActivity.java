package com.semonemo.latte.toyouproject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.util.SharedPreferenceManager;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferenceManager.getInstance().load(getApplicationContext());
    }
}
