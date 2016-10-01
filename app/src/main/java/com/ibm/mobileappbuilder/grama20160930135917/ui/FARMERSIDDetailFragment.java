
package com.ibm.mobileappbuilder.grama20160930135917.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSIDDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.FARMERSIDDetailPresenter;
import com.ibm.mobileappbuilder.grama20160930135917.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import java.net.URL;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSIDDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSIDDS;

public class FARMERSIDDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<FARMERSIDDSItem>  {

    private CrudDatasource<FARMERSIDDSItem> datasource;
    public static FARMERSIDDetailFragment newInstance(Bundle args){
        FARMERSIDDetailFragment fr = new FARMERSIDDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public FARMERSIDDetailFragment(){
        super();
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
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new FARMERSIDDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<FARMERSIDDSItem>) getPresenter()).editForm(getItem());
            }
        }));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.farmersiddetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final FARMERSIDDSItem item, View view) {
        
        ImageView view1 = (ImageView) view.findViewById(R.id.view1);
        URL view1Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view1Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view1.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view1Media.toExternalForm())
                                   .withTargetView(view1)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view1.setImageDrawable(null);
        }
    }

    @Override
    protected void onShow(FARMERSIDDSItem item) {
        // set the title for this fragment
        getActivity().setTitle("YOUR PASS BOOK");
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), FARMERSIDDSItemFormActivity.class);
        intent.putExtras(args);
        startActivityForResult(intent, Constants.MODE_EDIT);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_menu, menu);

        MenuItem item = menu.findItem(R.id.action_delete);
        ColorUtils.tintIcon(item, R.color.textBarColor, getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete){
            ((DetailCrudPresenter<FARMERSIDDSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

