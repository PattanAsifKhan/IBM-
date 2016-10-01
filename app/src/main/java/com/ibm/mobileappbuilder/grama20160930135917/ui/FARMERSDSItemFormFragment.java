
package com.ibm.mobileappbuilder.grama20160930135917.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.FARMERSCOMMUNITYFormPresenter;
import com.ibm.mobileappbuilder.grama20160930135917.R;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.views.ImagePicker;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import java.io.IOException;
import java.io.File;

import static android.net.Uri.fromFile;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSDS;

public class FARMERSDSItemFormFragment extends FormFragment<FARMERSDSItem> {

    private CrudDatasource<FARMERSDSItem> datasource;

    public static FARMERSDSItemFormFragment newInstance(Bundle args){
        FARMERSDSItemFormFragment fr = new FARMERSDSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public FARMERSDSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new FARMERSCOMMUNITYFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected FARMERSDSItem newItem() {
        return new FARMERSDSItem();
    }

    private FARMERSDSService getRestService(){
        return FARMERSDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.farmerscommunity_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final FARMERSDSItem item, View view) {
        
        bindString(R.id.farmersds_name, item.nAME, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.nAME = s.toString();
            }
        });
        
        
        bindImage(R.id.farmersds_picture,
            item.picture != null ?
                getRestService().getImageUrl(item.picture) : null,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.picture = null;
                    item.pictureUri = null;
                    ((ImagePicker) getView().findViewById(R.id.farmersds_picture)).clear();
                }
            }
        );
        
        
        bindString(R.id.farmersds_phoneno, item.pHONENO, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.pHONENO = s.toString();
            }
        });
        
    }

    @Override
    public Datasource<FARMERSDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = FARMERSDS.getInstance(new SearchOptions());
        return datasource;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            FARMERSDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
              imageUri = data.getData();
              switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            picker = (ImagePicker) getView().findViewById(R.id.farmersds_picture);
                            break;
                        
                default:
                  return;
              }

              picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
				      switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            picker = (ImagePicker) getView().findViewById(R.id.farmersds_picture);
                            imageUri = fromFile(picker.getImageFile());
                        		item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            break;
                        
                default:
                  return;
              }
              picker.setImageUri(imageUri);
            }
        }
    }
}

