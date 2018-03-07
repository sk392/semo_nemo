package com.semonemo.latte.toyouproject.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.semonemo.latte.toyouproject.R;

/**
 * Created by latte on 2018. 3. 6..
 */

public class CommonImageBinding {
    //xmlns:app="http://schemas.android.com/apk/res-auto"
    // @BindingAdapter("commonImageUrl")는 commonImageUrl이라고 네임 스페이스에 등록이 된다
    @BindingAdapter("commonImageUrl")
    public static void setCommonImageUrl(ImageView view, String url){
        final Context context = view.getContext();

        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .into(view);

    }
}
