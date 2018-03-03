package com.semonemo.latte.toyouproject.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kakao.kakaolink.AppActionBuilder;
import com.kakao.kakaolink.AppActionInfoBuilder;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.LinkObject;
import com.kakao.message.template.SocialObject;
import com.kakao.message.template.TextTemplate;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.KakaoParameterException;
import com.kakao.util.helper.log.Logger;
import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.util.SharedPreferenceManager;
import com.semonemo.latte.toyouproject.view.layout.AppbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.Url;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.iv_background_man)
    ImageView ivBackgroundMan;
    @BindView(R.id.iv_background_woman)
    ImageView ivBackgroundWoman;
    @BindView(R.id.et_invite_code)
    EditText etInviteCode;
    @BindView(R.id.tv_my_invite_code)
    TextView tvMyInviteCode;
    @BindView(R.id.appbar_signup)
    AppbarLayout appbarSignup;
    @BindView(R.id.btn_invate)
    Button btnInvate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();
    }

    private void bindView() {
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        appbarSignup.setTheme(AppbarLayout.SIGNUP_PAGE_APPBAR);
        etName.setText(SharedPreferenceManager.getInstance().getPrefStringData(SharedPreferenceManager.USER_NAME));
        etName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                tvComment.setVisibility(View.INVISIBLE);
            } else {
                tvComment.setVisibility(View.VISIBLE);

            }
        });
    }

    @OnClick({R.id.iv_background_man, R.id.iv_background_woman})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_background_man:
                ivBackgroundMan.setImageResource(R.color.dimmed_color);
                ivBackgroundWoman.setImageResource(R.color.transparent);
                break;
            case R.id.iv_background_woman:
                ivBackgroundMan.setImageResource(R.color.transparent);
                ivBackgroundWoman.setImageResource(R.color.dimmed_color);
                break;
        }
    }

    @OnClick(R.id.btn_invate)
    public void onViewClicked() {
        try {
            KakaoLink kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
            KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder
                    = kakaoLink.createKakaoTalkLinkMessageBuilder();
            kakaoTalkLinkMessageBuilder.addText(SharedPreferenceManager.getInstance()
                    .getPrefStringData(SharedPreferenceManager.USER_NAME)
                    +getResources().getString(R.string.link_message_content)
                    +SharedPreferenceManager.getInstance().getPrefLongData(SharedPreferenceManager.USER_CODE));
            kakaoTalkLinkMessageBuilder.addImage(getResources().getString(R.string.image_url),200,200);
            kakaoTalkLinkMessageBuilder.addAppButton("다운 받으러 가기",
                    new AppActionBuilder()
                            .addActionInfo(AppActionInfoBuilder.createAndroidActionInfoBuilder().setMarketParam("").build())
                            .build());
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);

        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }

       /* FeedTemplate params = FeedTemplate
                .newBuilder(ContentObject.newBuilder("ToYou 초대 메시지",
                        getResources().getString(R.string.image_url),
                        LinkObject.newBuilder().setWebUrl(getResources().getString(R.string.market_url))
                                .setMobileWebUrl(getResources().getString(R.string.market_url)).build())
                        .setDescrption(SharedPreferenceManager.getInstance()
                                .getPrefStringData(SharedPreferenceManager.USER_NAME)
                                +getResources().getString(R.string.link_message_content)
                                +SharedPreferenceManager.getInstance().getPrefLongData(SharedPreferenceManager.USER_CODE))
                        .build())
                .addButton(new ButtonObject(getResources().getString(R.string.link_message_button), LinkObject.newBuilder()
                        .setWebUrl(getResources().getString(R.string.market_url))
                        .setMobileWebUrl(getResources().getString(R.string.market_url))
                        .build()))
                .build();

        KakaoLinkService.getInstance().sendDefault(this, params, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.e(errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {

            }
        });*/



    }
}
