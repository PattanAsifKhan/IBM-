
package com.ibm.mobileappbuilder.grama20160930135917.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.grama20160930135917.ds.LOCALMARTDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.LOCALMARTDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.LOCALMARTFormPresenter;
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
import com.ibm.mobileappbuilder.grama20160930135917.ds.LOCALMARTDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.LOCALMARTDS;

public class LOCALMARTDSItemFormFragment extends FormFragment<LOCALMARTDSItem> {

    private CrudDatasource<LOCALMARTDSItem> datasource;

    public static LOCALMARTDSItemFormFragment newInstance(Bundle args){
        LOCALMARTDSItemFormFragment fr = new LOCALMARTDSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public LOCALMARTDSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new LOCALMARTFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected LOCALMARTDSItem newItem() {
        return new LOCALMARTDSItem();
    }

    private LOCALMARTDSService getRestService(){
        return LOCALMARTDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.localmart_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final LOCALMARTDSItem item, View view) {
        
        bindString(R.id.localmartds_contactname, item.contactName, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.contactName = s.toString();
            }
        });
        
        
        bindString(R.id.localmartds_email, item.email, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.email = s.toString();
            }
        });
        
        
        bindString(R.id.localmartds_city, item.city, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.city = s.toString();
            }
        });
        
        
        bindString(R.id.localmartds_phone, item.phone, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.phone = s.toString();
            }
        });
        
        
        bindImage(R.id.localmartds_picture,
            item.picture != null ?
                getRestService().getImageUrl(item.picture) : null,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.picture = null;
                    item.pictureUri = null;
                    ((ImagePicker) getView().findViewById(R.id.localmartds_picture)).clear();
                }
            }
        );
        
        
        bindLong(R.id.localmartds_priceperkg, item.pricePERKG, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.pricePERKG = StringUtils.parseLong(s.toString());
            }
        });
        
    }

    @Override
    public Datasource<LOCALMARTDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = LOCALMARTDS.getInstance(new SearchOptions());
        return datasource;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            LOCALMARTDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
              imageUri = data.getData();
              switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            picker = (ImagePicker) getView().findViewById(R.id.localmartds_picture);
                            break;
                        
                default:
                  return;
              }

              picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
				      switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            picker = (ImagePicker) getView().findViewById(R.id.localmartds_picture);
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

