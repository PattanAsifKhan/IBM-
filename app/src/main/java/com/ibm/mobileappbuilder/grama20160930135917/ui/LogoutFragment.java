package com.ibm.mobileappbuilder.grama20160930135917.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.grama20160930135917.MyApplication;

import ibmmobileappbuilder.ui.BaseFragment;
import ibmmobileappbuilder.util.LoginUtils;

public class LogoutFragment extends BaseFragment {

    public static LogoutFragment newInstance(Bundle bundle) {
        return new LogoutFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginUtils.logOut(((MyApplication) getActivity().getApplication()).getSecureSharedPreferences(),
                LoginActivity.class,
                getActivity()
        );
    }
}

