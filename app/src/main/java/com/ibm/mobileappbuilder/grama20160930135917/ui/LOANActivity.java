

package com.ibm.mobileappbuilder.grama20160930135917.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.grama20160930135917.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * LOANActivity list activity
 */
public class LOANActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.loanActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return LOANFragment.class;
    }

}

