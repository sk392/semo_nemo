package com.semonemo.latte.toyouproject.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.dto.LetterDto;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.semonemo.latte.toyouproject.view.HomeActivity.LETTER_DATA;

public class LetterDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_letter_detail_to)
    TextView tvLetterDetailTo;
    @BindView(R.id.tv_to_name)
    TextView tvToName;
    @BindView(R.id.tv_letter_content)
    TextView tvLetterContent;
    @BindView(R.id.tv_letter_detail_from)
    TextView tvLetterDetailFrom;
    @BindView(R.id.tv_from_name)
    TextView tvFromName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();
    }

    private void bindView(){
        setContentView(R.layout.activity_letter_detail);
        ButterKnife.bind(this);
        LetterDto letter = (LetterDto)getIntent().getExtras().getSerializable(LETTER_DATA);
        tvFromName.setText(letter.getFromUser());
        tvToName.setText(letter.getToUser());

        tvLetterDetailTo.setTypeface(Typeface.createFromAsset(getAssets(),getResources().getString(R.string.fontname_fish_chips)));
        tvLetterDetailFrom.setTypeface(Typeface.createFromAsset(getAssets(),getResources().getString(R.string.fontname_fish_chips)));
        tvFromName.setTypeface(Typeface.createFromAsset(getAssets(),getResources().getString(R.string.fontname_nanum_ultra_light)));
        tvToName.setTypeface(Typeface.createFromAsset(getAssets(),getResources().getString(R.string.fontname_nanum_ultra_light)));
        tvLetterContent.setTypeface(Typeface.createFromAsset(getAssets(),getResources().getString(R.string.fontname_nanum_ultra_light)));

    }

    @OnClick(R.id.btn_close)
    public void onViewClicked() {
        onBackPressed();
    }
}
