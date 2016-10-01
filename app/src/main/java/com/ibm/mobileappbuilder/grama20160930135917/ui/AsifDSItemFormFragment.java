
package com.ibm.mobileappbuilder.grama20160930135917.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.GOTALKFormPresenter;
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
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDS;

public class AsifDSItemFormFragment extends FormFragment<AsifDSItem> {

    private CrudDatasource<AsifDSItem> datasource;

    public static AsifDSItemFormFragment newInstance(Bundle args){
        AsifDSItemFormFragment fr = new AsifDSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public AsifDSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new GOTALKFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected AsifDSItem newItem() {
        return new AsifDSItem();
    }

    private AsifDSService getRestService(){
        return AsifDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.gotalk_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final AsifDSItem item, View view) {
        
        bindString(R.id.asifds_text1, item.text1, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.text1 = s.toString();
            }
        });
        
        
        bindLong(R.id.asifds_text2, item.text2, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.text2 = StringUtils.parseLong(s.toString());
            }
        });
        
        
        bindImage(R.id.asifds_picture,
            item.picture != null ?
                getRestService().getImageUrl(item.picture) : null,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.picture = null;
                    item.pictureUri = null;
                    ((ImagePicker) getView().findViewById(R.id.asifds_picture)).clear();
                }
            }
        );
        
        
        bindLong(R.id.asifds_text3, item.text3, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.text3 = StringUtils.parseLong(s.toString());
            }
        });
        
        
        bindLong(R.id.asifds_pending, item.pENDING, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.pENDING = StringUtils.parseLong(s.toString());
            }
        });
        
    }

    @Override
    public Datasource<AsifDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = AsifDS.getInstance(new SearchOptions());
        return datasource;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            AsifDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
              imageUri = data.getData();
              switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            picker = (ImagePicker) getView().findViewById(R.id.asifds_picture);
                            break;
                        
                default:
                  return;
              }

              picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
				      switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            picker = (ImagePicker) getView().findViewById(R.id.asifds_picture);
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

