package com.semonemo.latte.toyouproject.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.dto.LetterDto;
import com.semonemo.latte.toyouproject.util.SharedPreferenceManager;
import com.semonemo.latte.toyouproject.view.layout.AppbarLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.semonemo.latte.toyouproject.util.CommonNameSpace.DELAY_DAY_DEFAULT;
import static com.semonemo.latte.toyouproject.view.MainActivity.checkSharedColor;

public class HomeActivity extends BaseActivity {
    public static final String LETTER_DATA = "_letter_data_";

    @BindView(R.id.pi_home)
    PageIndicatorView piHome;
    @BindView(R.id.cl_home)
    ConstraintLayout clHome;
    @BindView(R.id.iv_home_background_man)
    ImageView ivHomeBackgroundMan;
    private List<LetterDto> letters;
    private LetterViewPagerAdapter letterViewPagerAdapter;
    @BindView(R.id.appbar_home)
    AppbarLayout appbarHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        letters = getLetters();
        bindView();
    }

    private void bindView() {
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        clHome.setBackgroundColor(getResources().getColor(SharedPreferenceManager.getInstance()
                .getPrefIntData(SharedPreferenceManager.THEME_BACKGROUND_COLOR)));
        ivHomeBackgroundMan.setBackground(getResources().getDrawable(SharedPreferenceManager.getInstance()
                .getPrefIntData(SharedPreferenceManager.THEME_BACKGROUND_IMAGE)));
        checkSharedColor();
        appbarHome.setTheme(AppbarLayout.MAIN_PAGE_APPBAR);
        letterViewPagerAdapter = new LetterViewPagerAdapter(getSupportFragmentManager());
        vpHome.setAdapter(letterViewPagerAdapter);
        piHome.setAnimationType(AnimationType.DROP);
        piHome.setViewPager(vpHome);
    }

    private List<LetterDto> getLetters() {
        List<LetterDto> mLetterDtos = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            mLetterDtos.add(new LetterDto(i
                    , i + "편지이이이이이이이이이다다아아아아당당다아다아다앋아다아다아다ㅏㅇ다아다아닫"
                    , 323l, "증장이", 525l, "유리닝", new Date(), DELAY_DAY_DEFAULT));
        }
        return mLetterDtos;
    }

    private class LetterViewPagerAdapter extends FragmentPagerAdapter {
        public LetterViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return LetterFragment.newInstance(letters.get(position));
        }

        @Override
        public int getCount() {
            return letters.size();
        }
    }

    public static class LetterFragment extends Fragment {
        private static String LETTER = "LETTER_NAME";
        @BindView(R.id.tv_d_day)
        TextView tvDDay;
        @BindView(R.id.iv_item_letter)
        ImageView ivItemLetter;
        private LetterDto letterDto;

        public LetterFragment() {
        }

        public static LetterFragment newInstance(LetterDto letter) {
            LetterFragment letterFragment = new LetterFragment();
            Bundle args = new Bundle();
            args.putSerializable(LETTER, letter);
            letterFragment.setArguments(args);
            return letterFragment;

        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
                , @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_letter, container, false);
            ButterKnife.bind(this, rootView);
            letterDto = (LetterDto) getArguments().getSerializable(LETTER);

            long reqDateTime = letterDto.getRegDate().getTime();

            long curDateTime = new Date().getTime();

            long minute = (curDateTime - reqDateTime);

            Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "FISH&CHIPS-Regular.ttf");
            tvDDay.setTypeface(type);
            tvDDay.setText("D-" + minute);
            return rootView;
        }

        @OnClick(R.id.iv_item_letter)
        public void onViewClicked() {
            Intent intent = new Intent(getActivity().getApplicationContext(), LetterDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(LETTER_DATA, letterDto);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
