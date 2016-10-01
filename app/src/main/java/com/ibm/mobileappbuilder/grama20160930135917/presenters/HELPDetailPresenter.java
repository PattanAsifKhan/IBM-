
package com.ibm.mobileappbuilder.grama20160930135917.presenters;

import com.ibm.mobileappbuilder.grama20160930135917.R;
import com.ibm.mobileappbuilder.grama20160930135917.ds.HELPDSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class HELPDetailPresenter extends BasePresenter implements DetailCrudPresenter<HELPDSItem>,
      Datasource.Listener<HELPDSItem> {

    private final CrudDatasource<HELPDSItem> datasource;
    private final DetailView view;

    public HELPDetailPresenter(CrudDatasource<HELPDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(HELPDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(HELPDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(HELPDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

