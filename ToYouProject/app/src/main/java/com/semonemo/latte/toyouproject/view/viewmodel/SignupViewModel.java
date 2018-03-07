package com.semonemo.latte.toyouproject.view.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableLong;
import android.util.Log;
import android.view.LayoutInflater;

import com.kakao.util.helper.log.Logger;
import com.semonemo.latte.toyouproject.R;
import com.semonemo.latte.toyouproject.view.data.SignupResult;

/**
 * Created by latte on 2018. 3. 5..
 */

public class SignupViewModel {

    public final ObservableField<String> userProfileImageUrl = new ObservableField<>();
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableLong userId = new ObservableLong();
    public final ObservableLong userInviteCode = new ObservableLong();
    public final ObservableField<SignupResult> signup = new ObservableField<>();

    public SignupViewModel() {
        signup.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Log.d("ㅅㅁㅎ","콜백 호출잼");
                SignupResult result =  SignupViewModel.this.signup.get();
                userProfileImageUrl.set(result.getUserProfileImageUrl());
                userName.set(result.getUserName());
                userId.set(result.getUserId());
                userInviteCode.set(result.getUserInviteCode());

            }
        });

    }






}
