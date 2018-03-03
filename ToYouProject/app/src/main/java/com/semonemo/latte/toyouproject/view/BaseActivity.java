package com.semonemo.latte.toyouproject.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.util.SharedPreferenceManager;
import com.tsengvn.typekit.TypekitContextWrapper;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferenceManager.getInstance().load(getApplicationContext());
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
