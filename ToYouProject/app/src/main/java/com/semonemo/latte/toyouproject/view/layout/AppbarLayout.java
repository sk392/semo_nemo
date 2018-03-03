package com.semonemo.latte.toyouproject.view.layout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.semonemo.latte.toyouproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by latte on 2018. 3. 3..
 */

public class AppbarLayout extends FrameLayout {

    private Context mContext;
    private View mView;

    public static final int MAIN_PAGE_APPBAR = 1;
    public static final int MY_PAGE_APPBAR = 2;
    public static final int WRITE_PAGE_APPBAR = 3;
    public static final int SIGNUP_PAGE_APPBAR = 4;

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
        this.addView(mView);
        ButterKnife.bind(this);
    }

    public void setTheme(int theme) {
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

        appbarLeftBtn.setBackground(getResources().getDrawable(R.drawable.account));
        appbarRightBtn.setBackground(getResources().getDrawable(R.drawable.account));
        appbarRightLeftBtn.setBackground(getResources().getDrawable(R.drawable.account));
        appbarTitle.setText(R.string.title_main);
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
        appbarTitle.setText(R.string.title_main);

    }

    private void setThemeSignup() {
        appbarLeftBtn.setVisibility(VISIBLE);
        appbarRightBtn.setVisibility(VISIBLE);
        appbarRightLeftBtn.setVisibility(GONE);
        appbarTitle.setVisibility(GONE);

        appbarLeftBtn.setBackground(getResources().getDrawable(R.drawable.kakaostory_icon));
        appbarRightBtn.setBackground(getResources().getDrawable(R.drawable.kakaostory_icon));
    }
    private void setThemedefault() {
        appbarLeftBtn.setVisibility(GONE);
        appbarRightBtn.setVisibility(GONE);
        appbarRightLeftBtn.setVisibility(GONE);
        appbarTitle.setVisibility(GONE);
    }
}
