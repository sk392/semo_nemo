package com.semonemo.latte.toyouproject.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;
import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.dto.UserDto;
import com.semonemo.latte.toyouproject.util.SharedPreferenceManager;
import com.semonemo.latte.toyouproject.view.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.semonemo.latte.toyouproject.util.CommonNameSpace.DATABASE_NAME;
import static com.semonemo.latte.toyouproject.util.CommonNameSpace.DATABASE_TABLE_USER;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private long delayTime =2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Handler handler =new Handler();
        handler.postDelayed(() -> {


                //첫 유저
                startActivityForTarget(LoginActivity.class);
        },delayTime);
    }
    private void startActivityForTarget(Class targetClass){

        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),targetClass);
        startActivity(intent);
        finish();
    }

    public static void toastMessage(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public static void checkSharedColor(){

    }
}
