package com.semonemo.latte.toyouproject.view;

import android.content.Intent;
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
import com.kakao.kakaolink.AppActionBuilder;
import com.kakao.kakaolink.AppActionInfoBuilder;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;
import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.databinding.ActivitySignupBinding;
import com.semonemo.latte.toyouproject.dto.UserDto;
import com.semonemo.latte.toyouproject.util.SharedPreferenceManager;
import com.semonemo.latte.toyouproject.view.layout.AppbarLayout;
import com.semonemo.latte.toyouproject.view.viewmodel.SignupViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.semonemo.latte.toyouproject.util.CommonNameSpace.DATABASE_NAME;
import static com.semonemo.latte.toyouproject.util.CommonNameSpace.DATABASE_TABLE_USER;
import static com.semonemo.latte.toyouproject.util.GeneratedInviteCode.getUserIdFromInviteCode;
import static com.semonemo.latte.toyouproject.view.MainActivity.checkSharedColor;
import static com.semonemo.latte.toyouproject.view.MainActivity.toastMessage;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = SignupActivity.class.getSimpleName();
    private static final int THEME_MAN = 101;
    private static final int THEME_WOMAN = 202;

    @BindView(R.id.appbar_signup)
    AppbarLayout appbarSignup;
    @BindView(R.id.iv_background_man)
    ImageView ivBackgroundMan;
    @BindView(R.id.iv_background_woman)
    ImageView ivBackgroundWoman;
    ActivitySignupBinding binding;
    private SignupViewModel mViewModel;
    private DatabaseReference mDatabaseReference;
    private int mTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(DATABASE_NAME);
        bindView();
        watchTargetCode();
        mViewModel.user.set(generatedData());
    }

    private void bindView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        mViewModel = new SignupViewModel();
        binding.setViewModel(mViewModel);
        ButterKnife.bind(this);
        appbarSignup.setTheme(AppbarLayout.SIGNUP_PAGE_APPBAR);
        setAppbarOnClickListener();
    }

    private void watchTargetCode() {
        mDatabaseReference.child(DATABASE_TABLE_USER)
                .child(String.valueOf(SharedPreferenceManager.getInstance().getPrefLongData(SharedPreferenceManager.USER_ID)))
                .child("targetCode")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //targetCode defaultCode =0
                        if (!dataSnapshot.getValue().equals("0")) {
                            SharedPreferenceManager.getInstance().setPrefStringData(SharedPreferenceManager.USER_TARGET_CODE, dataSnapshot.getValue().toString());
                            toastHelloMessage(dataSnapshot.getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void toastHelloMessage(String targetCode) {
        mDatabaseReference.child(DATABASE_TABLE_USER)
                .child(String.valueOf(getUserIdFromInviteCode(targetCode)))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserDto targetUser = dataSnapshot.getValue(UserDto.class);
                        toastMessage(getApplicationContext(), targetUser.getName() + "님과 연결되었습니다! 서로에게 메세지를 보내보세요!");
                        if(mTheme==THEME_MAN){
                            SharedPreferenceManager.getInstance().setPrefIntData(SharedPreferenceManager.THEME_BACKGROUND_COLOR,R.color.themeManBackgroundColor);
                            SharedPreferenceManager.getInstance().setPrefIntData(SharedPreferenceManager.THEME_BACKGROUND_IMAGE,R.drawable.man);
                            SharedPreferenceManager.getInstance().setPrefIntData(SharedPreferenceManager.THEME_BACKGROUND_LETTER_IMAGE,R.drawable.letter_detail_bg);
                            SharedPreferenceManager.getInstance().setPrefIntData(SharedPreferenceManager.THEME_FONT_COLOR,R.color.themeManFontColor);
                        }else{
                            SharedPreferenceManager.getInstance().setPrefIntData(SharedPreferenceManager.THEME_BACKGROUND_COLOR,R.color.themeWomanBackgroundColor);
                            SharedPreferenceManager.getInstance().setPrefIntData(SharedPreferenceManager.THEME_BACKGROUND_IMAGE,R.drawable.woman);
                            SharedPreferenceManager.getInstance().setPrefIntData(SharedPreferenceManager.THEME_BACKGROUND_LETTER_IMAGE,R.drawable.letterbg_w);
                            SharedPreferenceManager.getInstance().setPrefIntData(SharedPreferenceManager.THEME_FONT_COLOR,R.color.themeWomanFontColor);

                        }
                        checkSharedColor();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }


    @OnClick(R.id.btn_invate)
    public void onViewClicked() {
        try {
            KakaoLink kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
            KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder
                    = kakaoLink.createKakaoTalkLinkMessageBuilder();
            kakaoTalkLinkMessageBuilder.addText(SharedPreferenceManager.getInstance()
                    .getPrefStringData(SharedPreferenceManager.USER_NAME)
                    + getResources().getString(R.string.link_message_content)
                    + SharedPreferenceManager.getInstance().getPrefStringData(SharedPreferenceManager.USER_CODE));
            kakaoTalkLinkMessageBuilder.addImage(getResources().getString(R.string.image_url), 200, 200);
            kakaoTalkLinkMessageBuilder.addAppButton("다운 받으러 가기",
                    new AppActionBuilder()
                            .addActionInfo(AppActionInfoBuilder.createAndroidActionInfoBuilder().setMarketParam("").build())
                            .build());
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);

        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }

    }

    @OnClick({R.id.iv_background_man, R.id.iv_background_woman})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_background_man:
                mTheme = THEME_MAN;
                ivBackgroundMan.setImageResource(R.color.dimmed_color);
                ivBackgroundWoman.setImageResource(R.color.transparent);
                break;
            case R.id.iv_background_woman:
                mTheme = THEME_WOMAN;
                ivBackgroundWoman.setImageResource(R.color.dimmed_color);
                ivBackgroundMan.setImageResource(R.color.transparent);
                break;
        }
    }

    private UserDto generatedData() {
        UserDto signupResult = new UserDto();
        signupResult.setId(SharedPreferenceManager.getInstance().getPrefLongData(SharedPreferenceManager.USER_ID));
        signupResult.setInviteCode(SharedPreferenceManager.getInstance().getPrefStringData(SharedPreferenceManager.USER_CODE));
        signupResult.setName(SharedPreferenceManager.getInstance().getPrefStringData(SharedPreferenceManager.USER_NAME));
        return signupResult;
    }


    public void setAppbarOnClickListener() {
        appbarSignup.setAppbarOnClickListener(v -> {
            if (binding.etInviteCode.getText().toString().length() == 9) {
                targetInviteCodeCheck(binding.etInviteCode.getText().toString());
            } else {
                toastMessage(getApplicationContext(), "초대 코드는 9자리 입니다.");
            }

        }, AppbarLayout.APPBAR_RIGHT_BUTTON);
    }

    private void targetInviteCodeCheck(String inviteCode) {
        mDatabaseReference.child(DATABASE_TABLE_USER)
                .child(String.valueOf(getUserIdFromInviteCode(inviteCode)))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            updateUserName(binding.etName.getText().toString());
                            updateTargetCode(inviteCode);
                        } else {
                            toastMessage(getApplicationContext(), "일치하는 초대 코드가 없습니다. 코드를 다시 확인해주세요.");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    private void updateUserName(String name) {
        mDatabaseReference.child(DATABASE_TABLE_USER)
                .child(String.valueOf(SharedPreferenceManager.getInstance().getPrefLongData(SharedPreferenceManager.USER_ID)))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserDto userDto = dataSnapshot.getValue(UserDto.class);
                        userDto.setName(name);
                        FirebaseDatabase.getInstance().getReference(DATABASE_NAME).child(DATABASE_TABLE_USER)
                                .child(String.valueOf(userDto.getId())).setValue(userDto);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        com.kakao.util.helper.log.Logger.d(databaseError.getMessage());
                    }
                });
    }

    private void updateTargetCode(String targetCode) {
        //myCode Change
        mDatabaseReference.child(DATABASE_TABLE_USER)
                .child(String.valueOf(SharedPreferenceManager.getInstance().getPrefLongData(SharedPreferenceManager.USER_ID)))
                .child("targetCode").setValue(targetCode);
        //targetUserCode Change
        mDatabaseReference.child(DATABASE_TABLE_USER)
                .child(String.valueOf(getUserIdFromInviteCode(targetCode)))
                .child("targetCode").setValue(SharedPreferenceManager.getInstance().getPrefStringData(SharedPreferenceManager.USER_CODE));
    }
}
