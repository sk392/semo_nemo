package com.semonemo.latte.toyouproject.view.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.util.SharedPreferenceManager;
import com.semonemo.latte.toyouproject.view.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by latte on 2018. 3. 3..
 */

public class AppbarLayout extends FrameLayout {

    private Context mContext;
    private View mView;
    private int themeType;
    public static final int MAIN_PAGE_APPBAR = 101;
    public static final int MY_PAGE_APPBAR = 102;
    public static final int WRITE_PAGE_APPBAR = 103;
    public static final int SIGNUP_PAGE_APPBAR = 104;
    public static final int APPBAR_LEFT_BUTTON = 1;
    public static final int APPBAR_RIGHT_BUTTON = 2;
    public static final int APPBAR_RIGHT_LEFT_BUTTON = 3;

    @BindView(R.id.appbar_left_btn)
    ImageView appbarLeftBtn;
    @BindView(R.id.appbar_right_btn)
    ImageView appbarRightBtn;
    @BindView(R.id.appbar_title)
    TextView appbarTitle;
    @BindView(R.id.appbar_right_left_btn)
    ImageView appbarRightLeftBtn;

    public AppbarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.layout_appbar, this, false);
        mView.setBackgroundColor(getResources().getColor(SharedPreferenceManager.getInstance()
                .getPrefIntData(SharedPreferenceManager.THEME_BACKGROUND_COLOR)));
        ((Activity)context).getWindow().setStatusBarColor(getResources().getColor(SharedPreferenceManager.getInstance()
                .getPrefIntData(SharedPreferenceManager.THEME_BACKGROUND_COLOR)));

        this.addView(mView);
        ButterKnife.bind(this);
        appbarTitle.setTypeface(Typeface.createFromAsset(mContext.getAssets(),"FISH&CHIPS-Regular.ttf"));
    }

    public void setTheme(int theme) {
        this.themeType = theme;
        switch (theme) {
            case MAIN_PAGE_APPBAR:
                setThemeMain();
                break;
            case MY_PAGE_APPBAR:
                setThemeMy();
                break;
            case WRITE_PAGE_APPBAR:
                setThemeWrite();
                break;
            case SIGNUP_PAGE_APPBAR:
                setThemeSignup();
                break;
            default:
                setThemedefault();
                break;
        }
    }

    private void setThemeMain() {
        appbarLeftBtn.setVisibility(VISIBLE);
        appbarRightBtn.setVisibility(VISIBLE);
        appbarRightLeftBtn.setVisibility(VISIBLE);
        appbarTitle.setVisibility(VISIBLE);

        appbarLeftBtn.setImageResource(R.drawable.my_icon);
        appbarRightLeftBtn.setImageResource(R.drawable.add_letter_icon);
        appbarRightBtn.setImageResource(R.drawable.setting_icon);
        appbarTitle.setText(R.string.appbar_title_main);
    }

    private void setThemeMy() {
        appbarLeftBtn.setVisibility(VISIBLE);
        appbarRightBtn.setVisibility(VISIBLE);
        appbarRightLeftBtn.setVisibility(GONE);
        appbarTitle.setVisibility(VISIBLE);

        appbarLeftBtn.setBackground(getResources().getDrawable(R.drawable.kakao_account_logo));
        appbarRightBtn.setBackground(getResources().getDrawable(R.drawable.kakao_account_logo));
        appbarTitle.setText(R.string.title_my);
    }

    private void setThemeWrite() {
        appbarLeftBtn.setVisibility(VISIBLE);
        appbarRightBtn.setVisibility(GONE);
        appbarRightLeftBtn.setVisibility(GONE);
        appbarTitle.setVisibility(VISIBLE);

        appbarLeftBtn.setBackground(getResources().getDrawable(R.drawable.kakao_default_profile_image));
        appbarTitle.setText(R.string.appbar_title_main);

    }

    private void setThemeSignup() {
        appbarLeftBtn.setVisibility(GONE);
        appbarRightBtn.setVisibility(VISIBLE);
        appbarRightLeftBtn.setVisibility(GONE);
        appbarTitle.setVisibility(GONE);

        appbarRightBtn.setBackground(getResources().getDrawable(R.drawable.kakaostory_icon));
    }

    private void setThemedefault() {
        appbarLeftBtn.setVisibility(GONE);
        appbarRightBtn.setVisibility(GONE);
        appbarRightLeftBtn.setVisibility(GONE);
        appbarTitle.setVisibility(GONE);
    }

    public void setAppbarOnClickListener(OnClickListener onClickListener,int position) {
        switch (position){
            case APPBAR_LEFT_BUTTON :
                appbarLeftBtn.setOnClickListener(onClickListener);
                break;
            case APPBAR_RIGHT_BUTTON :
                appbarRightBtn.setOnClickListener(onClickListener);
                break;
            case APPBAR_RIGHT_LEFT_BUTTON :
                appbarRightLeftBtn.setOnClickListener(onClickListener);
                break;

        }
    }

    public int getThemeType() {
        return this.themeType;
    }

}
