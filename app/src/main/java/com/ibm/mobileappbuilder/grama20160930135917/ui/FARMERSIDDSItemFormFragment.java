
package com.ibm.mobileappbuilder.grama20160930135917.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSIDDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSIDDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.FARMERSIDFormPresenter;
import com.ibm.mobileappbuilder.grama20160930135917.R;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.views.ImagePicker;
import java.io.IOException;
import java.io.File;

import static android.net.Uri.fromFile;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSIDDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSIDDS;

public class FARMERSIDDSItemFormFragment extends FormFragment<FARMERSIDDSItem> {

    private CrudDatasource<FARMERSIDDSItem> datasource;

    public static FARMERSIDDSItemFormFragment newInstance(Bundle args){
        FARMERSIDDSItemFormFragment fr = new FARMERSIDDSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public FARMERSIDDSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new FARMERSIDFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected FARMERSIDDSItem newItem() {
        return new FARMERSIDDSItem();
    }

    private FARMERSIDDSService getRestService(){
        return FARMERSIDDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.farmersid_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final FARMERSIDDSItem item, View view) {
        
        bindImage(R.id.farmersidds_picture,
            item.picture != null ?
                getRestService().getImageUrl(item.picture) : null,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.picture = null;
                    item.pictureUri = null;
                    ((ImagePicker) getView().findViewById(R.id.farmersidds_picture)).clear();
                }
            }
        );
        
    }

    @Override
    public Datasource<FARMERSIDDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = FARMERSIDDS.getInstance(new SearchOptions());
        return datasource;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            FARMERSIDDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
              imageUri = data.getData();
              switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            picker = (ImagePicker) getView().findViewById(R.id.farmersidds_picture);
                            break;
                        
                default:
                  return;
              }

              picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
				      switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            picker = (ImagePicker) getView().findViewById(R.id.farmersidds_picture);
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

