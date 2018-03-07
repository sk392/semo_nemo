package com.semonemo.latte.toyouproject.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.util.SharedPreferenceManager;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private SessionCallback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        callback = new SessionCallback();
        SharedPreferenceManager.getInstance().load(getApplicationContext());
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Logger.d(TAG,"onSessionOpened");
            requestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Logger.d(TAG,"onSessionOpenFailed");
            if (exception != null) {
                Logger.e(exception);
            }
            loginFailed();
        }
    }

    private void requestMe(){
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                loginFailed();
            }
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Logger.e(TAG,"onSessionClosed. msg= "+errorResult);
                loginFailed();
            }

            @Override
            public void onNotSignedUp() {
                Logger.e(TAG,"onNotSignedUp");
                loginFailed();

            }

            @Override
            public void onSuccess(UserProfile result) {
                Log.d(TAG,"onSuccess");
                SharedPreferenceManager.getInstance().setPrefStringData(SharedPreferenceManager.USER_NAME,result.getNickname());
                SharedPreferenceManager.getInstance().setPrefStringData(SharedPreferenceManager.USER_EMAIL,result.getEmail());
                SharedPreferenceManager.getInstance().setPrefLongData(SharedPreferenceManager.USER_ID,result.getId());
                long code = result.getId()%100000000;
                SharedPreferenceManager.getInstance().setPrefLongData(SharedPreferenceManager.USER_CODE,code);
                SharedPreferenceManager.getInstance().setPrefStringData(SharedPreferenceManager.USER_PROFILE,result.getProfileImagePath());
                SharedPreferenceManager.getInstance().setPrefStringData(SharedPreferenceManager.USER_TOKEN_ACCESS
                        ,Session.getCurrentSession().getTokenInfo().getAccessToken().toString());
                SharedPreferenceManager.getInstance().setPrefStringData(SharedPreferenceManager.USER_TOKEN_REFRESH
                        ,Session.getCurrentSession().getTokenInfo().getRefreshToken().toString());

                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });
    }
    private void loginFailed(){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        builder.title("로그인 실패")
                .content("네트워크에 연결되어 있는지 확인해 주세요. 또는 일시적 네트워크 오류일 수 있습니다.")
                .negativeText("종료").negativeColor(Color.DKGRAY)
                .onNegative((dialog, which) -> { System.exit(0); })
                .positiveText("재시도").positiveColor(getResources().getColor(R.color.com_kakao_brown))
                .onPositive((dialog, which) -> { redirectLoginActivity(); })
                .canceledOnTouchOutside(false);
        builder.build().show();
    }


    private void redirectLoginActivity(){

        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
