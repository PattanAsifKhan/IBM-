
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
import com.ibm.mobileappbuilder.grama20160930135917.ds.LOCALMARTDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.LOCALMARTDetailPresenter;
import com.ibm.mobileappbuilder.grama20160930135917.R;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MailAction;
import ibmmobileappbuilder.actions.PhoneAction;
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
import com.ibm.mobileappbuilder.grama20160930135917.ds.LOCALMARTDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.LOCALMARTDS;

public class LOCALMARTDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<LOCALMARTDSItem> implements ShareBehavior.ShareListener  {

    private CrudDatasource<LOCALMARTDSItem> datasource;
    public static LOCALMARTDetailFragment newInstance(Bundle args){
        LOCALMARTDetailFragment fr = new LOCALMARTDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public LOCALMARTDetailFragment(){
        super();
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
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new LOCALMARTDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<LOCALMARTDSItem>) getPresenter()).editForm(getItem());
            }
        }));
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.localmartdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final LOCALMARTDSItem item, View view) {
        if (item.contactName != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.contactName);
            
        }
        if (item.email != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.email);
            bindAction(view1, new MailAction(
            new ActivityIntentLauncher()
            , item.email));
        }
        if (item.city != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText(item.city);
            
        }
        if (item.phone != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.phone);
            bindAction(view3, new PhoneAction(
            new ActivityIntentLauncher()
            , item.phone));
        }
        
        ImageView view4 = (ImageView) view.findViewById(R.id.view4);
        URL view4Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view4Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view4.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view4Media.toExternalForm())
                                   .withTargetView(view4)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view4.setImageDrawable(null);
        }
        if (item.pricePERKG != null){
            
            TextView view5 = (TextView) view.findViewById(R.id.view5);
            view5.setText(item.pricePERKG.toString());
            
        }
    }

    @Override
    protected void onShow(LOCALMARTDSItem item) {
        // set the title for this fragment
        getActivity().setTitle("LOCAL MART");
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), LOCALMARTDSItemFormActivity.class);
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
            ((DetailCrudPresenter<LOCALMARTDSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onShare() {
        LOCALMARTDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.contactName != null ? item.contactName : "" ) + "\n" +
                    (item.email != null ? item.email : "" ) + "\n" +
                    (item.city != null ? item.city : "" ) + "\n" +
                    (item.phone != null ? item.phone : "" ) + "\n" +
                    (item.pricePERKG != null ? item.pricePERKG.toString() : "" ));
        intent.putExtra(Intent.EXTRA_SUBJECT, "LOCAL MART");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

