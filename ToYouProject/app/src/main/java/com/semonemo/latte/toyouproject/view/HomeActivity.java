package com.semonemo.latte.toyouproject.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.dto.LetterDto;
import com.semonemo.latte.toyouproject.view.layout.AppbarLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.pi_home)
    PageIndicatorView piHome;
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
                    , "증장이", "유리닝", new Date()));
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
            LetterDto letterDto = (LetterDto) getArguments().getSerializable(LETTER);

            long reqDateTime = letterDto.getRegDate().getTime();

            long curDateTime = new Date().getTime();

            long minute = (curDateTime - reqDateTime);
            tvDDay.setText(minute + "분");
            return rootView;
        }

    }
}
