
package com.ibm.mobileappbuilder.grama20160930135917.presenters;

import com.ibm.mobileappbuilder.grama20160930135917.R;
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class LOANDetailPresenter extends BasePresenter implements DetailCrudPresenter<AsifDSItem>,
      Datasource.Listener<AsifDSItem> {

    private final CrudDatasource<AsifDSItem> datasource;
    private final DetailView view;

    public LOANDetailPresenter(CrudDatasource<AsifDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(AsifDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(AsifDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(AsifDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

