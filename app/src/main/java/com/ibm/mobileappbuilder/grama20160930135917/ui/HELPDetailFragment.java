
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
import com.ibm.mobileappbuilder.grama20160930135917.ds.HELPDSService;
import com.ibm.mobileappbuilder.grama20160930135917.presenters.HELPDetailPresenter;
import com.ibm.mobileappbuilder.grama20160930135917.R;
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
import com.ibm.mobileappbuilder.grama20160930135917.ds.HELPDSItem;
import com.ibm.mobileappbuilder.grama20160930135917.ds.HELPDS;

public class HELPDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<HELPDSItem> implements ShareBehavior.ShareListener  {

    private CrudDatasource<HELPDSItem> datasource;
    public static HELPDetailFragment newInstance(Bundle args){
        HELPDetailFragment fr = new HELPDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public HELPDetailFragment(){
        super();
    }

    @Override
    public Datasource<HELPDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = HELPDS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new HELPDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<HELPDSItem>) getPresenter()).editForm(getItem());
            }
        }));
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.helpdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final HELPDSItem item, View view) {
        if (item.nAME != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.nAME);
            
        }
        if (item.eMAIL != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.eMAIL);
            
        }
        if (item.pHONENO != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText(item.pHONENO);
            
        }
    }

    @Override
    protected void onShow(HELPDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), HELPDSItemFormActivity.class);
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
            ((DetailCrudPresenter<HELPDSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onShare() {
        HELPDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.nAME != null ? item.nAME : "" ) + "\n" +
                    (item.eMAIL != null ? item.eMAIL : "" ) + "\n" +
                    (item.pHONENO != null ? item.pHONENO : "" ));
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

