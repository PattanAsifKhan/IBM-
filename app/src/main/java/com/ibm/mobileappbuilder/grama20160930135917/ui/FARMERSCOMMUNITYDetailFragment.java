
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
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.FARMERSCOMMUNITYDetailPresenter;
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
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSDS;

public class FARMERSCOMMUNITYDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<FARMERSDSItem> implements ShareBehavior.ShareListener  {

    private CrudDatasource<FARMERSDSItem> datasource;
    public static FARMERSCOMMUNITYDetailFragment newInstance(Bundle args){
        FARMERSCOMMUNITYDetailFragment fr = new FARMERSCOMMUNITYDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public FARMERSCOMMUNITYDetailFragment(){
        super();
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
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new FARMERSCOMMUNITYDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<FARMERSDSItem>) getPresenter()).editForm(getItem());
            }
        }));
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.farmerscommunitydetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final FARMERSDSItem item, View view) {
        if (item.nAME != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.nAME);
            
        }
        if (item.iD != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.iD.toString());
            
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
        if (item.pHONENO != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.pHONENO);
            bindAction(view3, new PhoneAction(
            new ActivityIntentLauncher()
            , item.pHONENO));
        }
        
        TextView view4 = (TextView) view.findViewById(R.id.view4);
        view4.setText("Send message");
        bindAction(view4, new MailAction(
        new ActivityIntentLauncher()
        , "aisf.khan.399@gmail.com"));
    }

    @Override
    protected void onShow(FARMERSDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), FARMERSDSItemFormActivity.class);
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
            ((DetailCrudPresenter<FARMERSDSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onShare() {
        FARMERSDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.nAME != null ? item.nAME : "" ) + "\n" +
                    (item.iD != null ? item.iD.toString() : "" ) + "\n" +
                    (item.pHONENO != null ? item.pHONENO : "" ) + "\n" +
                    "Send message");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

