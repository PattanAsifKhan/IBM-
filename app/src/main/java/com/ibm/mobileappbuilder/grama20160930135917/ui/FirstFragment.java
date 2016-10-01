

package com.ibm.mobileappbuilder.grama20160930135917.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.grama20160930135917.R;

import java.util.ArrayList;
import java.util.List;

import ibmmobileappbuilder.MenuItem;

import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.util.Constants;

/**
 * FirstFragment menu fragment.
 */
public class FirstFragment extends ibmmobileappbuilder.ui.MenuFragment {

    /**
     * Default constructor
     */
    public FirstFragment(){
        super();
    }

    // Factory method
    public static FirstFragment newInstance(Bundle args) {
        FirstFragment fragment = new FirstFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
                }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("GO-TALK")
            .setIcon(R.drawable.jpg_go52)
            .setAction(new StartActivityAction(GOTALKActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("HELP")
            .setIcon(R.drawable.png_helppagesign571)
            .setAction(new StartActivityAction(HELPActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("LOAN")
            .setIcon(R.drawable.jpg_loans1828)
            .setAction(new StartActivityAction(LOANActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("FARMERS ID")
            .setIcon(R.drawable.jpg_imgthing836)
            .setAction(new StartActivityAction(FARMERSIDActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("GRAM PANCHAYAT")
            .setIcon(R.drawable.jpg_grampanchayat22334206)
            .setAction(new StartActivityAction(FARMERSCOMMUNITYActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("LOCAL MART")
            .setIcon(R.drawable.jpg_3830)
            .setAction(new StartActivityAction(LOCALMARTActivity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_grid;
    }

    @Override
    public int getItemLayout() {
        return R.layout.first_item;
    }
}

