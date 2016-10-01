
package com.ibm.mobileappbuilder.grama20160930135917.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.grama20160930135917.ds.HELPDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.HELPDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.HELPFormPresenter;
import com.ibm.mobileappbuilder.grama20160930135917.R;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.grama20160930135917.ds.HELPDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.HELPDS;

public class HELPDSItemFormFragment extends FormFragment<HELPDSItem> {

    private CrudDatasource<HELPDSItem> datasource;

    public static HELPDSItemFormFragment newInstance(Bundle args){
        HELPDSItemFormFragment fr = new HELPDSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public HELPDSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new HELPFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected HELPDSItem newItem() {
        return new HELPDSItem();
    }

    private HELPDSService getRestService(){
        return HELPDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.help_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final HELPDSItem item, View view) {
        
        bindString(R.id.helpds_name, item.nAME, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.nAME = s.toString();
            }
        });
        
        
        bindString(R.id.helpds_email, item.eMAIL, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.eMAIL = s.toString();
            }
        });
        
        
        bindString(R.id.helpds_phoneno, item.pHONENO, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.pHONENO = s.toString();
            }
        });
        
    }

    @Override
    public Datasource<HELPDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = HELPDS.getInstance(new SearchOptions());
        return datasource;
    }
}

