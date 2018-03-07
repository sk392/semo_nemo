package com.semonemo.latte.toyouproject.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.databinding.ActivitySignupBinding;
import com.semonemo.latte.toyouproject.util.SharedPreferenceManager;
import com.semonemo.latte.toyouproject.view.data.SignupResult;
import com.semonemo.latte.toyouproject.view.layout.AppbarLayout;
import com.semonemo.latte.toyouproject.view.viewmodel.SignupViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = SignupActivity.class.getSimpleName();

    @BindView(R.id.appbar_signup)
    AppbarLayout appbarSignup;
    @BindView(R.id.iv_background_man)
    ImageView ivBackgroundMan;
    @BindView(R.id.iv_background_woman)
    ImageView ivBackgroundWoman;

    private SignupViewModel mViewModel;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindView();
    }

    private void bindView() {
        ActivitySignupBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        mViewModel = new SignupViewModel();
        binding.setViewModel(mViewModel);
        ButterKnife.bind(this);
        appbarSignup.setTheme(AppbarLayout.SIGNUP_PAGE_APPBAR);
        setAppbarOnClickListener(appbarSignup);
        mViewModel.signup.set(generatedData());
        myRef = FirebaseDatabase.getInstance().getReference("toyou");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                mViewModel.userName.set(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


    @OnClick(R.id.btn_invate)
    public void onViewClicked() {
       /* try {
            KakaoLink kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
            KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder
                    = kakaoLink.createKakaoTalkLinkMessageBuilder();
            kakaoTalkLinkMessageBuilder.addText(SharedPreferenceManager.getInstance()
                    .getPrefStringData(SharedPreferenceManager.USER_NAME)
                    + getResources().getString(R.string.link_message_content)
                    + SharedPreferenceManager.getInstance().getPrefLongData(SharedPreferenceManager.USER_CODE));
            kakaoTalkLinkMessageBuilder.addImage(getResources().getString(R.string.image_url), 200, 200);
            kakaoTalkLinkMessageBuilder.addAppButton("다운 받으러 가기",
                    new AppActionBuilder()
                            .addActionInfo(AppActionInfoBuilder.createAndroidActionInfoBuilder().setMarketParam("").build())
                            .build());
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);

        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }*/

    }

    @OnClick({R.id.iv_background_man, R.id.iv_background_woman})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_background_man:
                ivBackgroundMan.setImageResource(R.color.dimmed_color);
                ivBackgroundWoman.setImageResource(R.color.transparent);
                break;
            case R.id.iv_background_woman:
                ivBackgroundWoman.setImageResource(R.color.dimmed_color);
                ivBackgroundMan.setImageResource(R.color.transparent);
                break;
        }
    }

    private SignupResult generatedData() {
        SignupResult signupResult = new SignupResult();
        signupResult.setUserId(SharedPreferenceManager.getInstance().getPrefLongData(SharedPreferenceManager.USER_ID));
        signupResult.setUserInviteCode(SharedPreferenceManager.getInstance().getPrefLongData(SharedPreferenceManager.USER_CODE));
        signupResult.setUserName(SharedPreferenceManager.getInstance().getPrefStringData(SharedPreferenceManager.USER_NAME));
        signupResult.setUserProfileImageUrl(SharedPreferenceManager.getInstance().getPrefStringData(SharedPreferenceManager.USER_PROFILE));
        return signupResult;
    }

    public void setAppbarOnClickListener(AppbarLayout appbarLayout) {
        appbarLayout.setAppbarOnClickListener(v -> {
            onBackPressed();
        }, AppbarLayout.APPBAR_LEFT_BUTTON);
        appbarLayout.setAppbarOnClickListener(v -> {

        }, AppbarLayout.APPBAR_RIGHT_BUTTON);
    }
}
