package com.semonemo.latte.toyouproject.view.viewmodel;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableLong;
import android.util.Log;

import com.semonemo.latte.toyouproject.dto.UserDto;

/**
 * Created by latte on 2018. 3. 5..
 */

public class SignupViewModel {

    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableLong userId = new ObservableLong();
    public final ObservableField<String> userInviteCode = new ObservableField<>();
    public final ObservableField<UserDto> user = new ObservableField<>();

    public SignupViewModel() {
        user.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                UserDto result =  user.get();
                userName.set(result.getName());
                userId.set(result.getId());
                userInviteCode.set(result.getInviteCode());

            }
        });

    }
}
