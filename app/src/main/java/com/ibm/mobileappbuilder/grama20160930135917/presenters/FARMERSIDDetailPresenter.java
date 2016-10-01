
package com.ibm.mobileappbuilder.grama20160930135917.presenters;

import com.ibm.mobileappbuilder.grama20160930135917.R;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSIDDSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class FARMERSIDDetailPresenter extends BasePresenter implements DetailCrudPresenter<FARMERSIDDSItem>,
      Datasource.Listener<FARMERSIDDSItem> {

    private final CrudDatasource<FARMERSIDDSItem> datasource;
    private final DetailView view;

    public FARMERSIDDetailPresenter(CrudDatasource<FARMERSIDDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(FARMERSIDDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(FARMERSIDDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(FARMERSIDDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

