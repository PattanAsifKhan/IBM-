

package com.ibm.mobileappbuilder.grama20160930135917.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ibmmobileappbuilder.ui.BaseDetailActivity;

/**
 * FARMERSIDDSItemFormActivity form activity
 */
public class FARMERSIDDSItemFormActivity extends BaseDetailActivity {
  	
  	@Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return FARMERSIDDSItemFormFragment.class;
    }
}


