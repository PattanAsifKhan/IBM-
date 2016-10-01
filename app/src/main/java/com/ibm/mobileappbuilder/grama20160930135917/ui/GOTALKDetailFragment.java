
package com.ibm.mobileappbuilder.grama20160930135917.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.GOTALKDetailPresenter;
import com.ibm.mobileappbuilder.grama20160930135917.R;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MailAction;
import ibmmobileappbuilder.actions.PhoneAction;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDS;

public class GOTALKDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<AsifDSItem> implements ShareBehavior.ShareListener  {

    private CrudDatasource<AsifDSItem> datasource;
    public static GOTALKDetailFragment newInstance(Bundle args){
        GOTALKDetailFragment fr = new GOTALKDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public GOTALKDetailFragment(){
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
        setPresenter(new GOTALKDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.gotalkdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final AsifDSItem item, View view) {
        
        TextView view0 = (TextView) view.findViewById(R.id.view0);
        view0.setText("click here to drop your request");
        bindAction(view0, new MailAction(
        new ActivityIntentLauncher()
        , "farmersgov@gmail.com"));
        
        TextView view1 = (TextView) view.findViewById(R.id.view1);
        view1.setText("1800-800-900");
        bindAction(view1, new PhoneAction(
        new ActivityIntentLauncher()
        , "1800-800-900"));
    }

    @Override
    protected void onShow(AsifDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
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

        intent.putExtra(Intent.EXTRA_TEXT, "click here to drop your request" + "\n" +
                    "1800-800-900");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

