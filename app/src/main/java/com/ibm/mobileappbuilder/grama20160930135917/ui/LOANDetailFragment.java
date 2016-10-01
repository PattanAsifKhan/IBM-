
package com.ibm.mobileappbuilder.grama20160930135917.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.LOANDetailPresenter;
import com.ibm.mobileappbuilder.grama20160930135917.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.ShareBehavior;
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
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDS;

public class LOANDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<AsifDSItem> implements ShareBehavior.ShareListener  {

    private CrudDatasource<AsifDSItem> datasource;
    public static LOANDetailFragment newInstance(Bundle args){
        LOANDetailFragment fr = new LOANDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public LOANDetailFragment(){
        super();
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
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new LOANDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<AsifDSItem>) getPresenter()).editForm(getItem());
            }
        }));
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.loandetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final AsifDSItem item, View view) {
        if (item.text1 != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.text1);
            
        }
        if (item.text2 != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.text2.toString());
            
        }
        
        ImageView view2 = (ImageView) view.findViewById(R.id.view2);
        URL view2Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view2Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view2.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view2Media.toExternalForm())
                                   .withTargetView(view2)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view2.setImageDrawable(null);
        }
        if (item.text3 != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.text3.toString());
            
        }
        if (item.pENDING != null){
            
            TextView view4 = (TextView) view.findViewById(R.id.view4);
            view4.setText(item.pENDING.toString());
            
        }
    }

    @Override
    protected void onShow(AsifDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), AsifDSItemFormActivity.class);
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
            ((DetailCrudPresenter<AsifDSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onShare() {
        AsifDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.text1 != null ? item.text1 : "" ) + "\n" +
                    (item.text2 != null ? item.text2.toString() : "" ) + "\n" +
                    (item.text3 != null ? item.text3.toString() : "" ) + "\n" +
                    (item.pENDING != null ? item.pENDING.toString() : "" ));
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

