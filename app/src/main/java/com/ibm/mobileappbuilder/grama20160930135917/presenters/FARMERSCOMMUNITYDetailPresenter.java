
package com.ibm.mobileappbuilder.grama20160930135917.presenters;

import com.ibm.mobileappbuilder.grama20160930135917.R;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSDSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class FARMERSCOMMUNITYDetailPresenter extends BasePresenter implements DetailCrudPresenter<FARMERSDSItem>,
      Datasource.Listener<FARMERSDSItem> {

    private final CrudDatasource<FARMERSDSItem> datasource;
    private final DetailView view;

    public FARMERSCOMMUNITYDetailPresenter(CrudDatasource<FARMERSDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(FARMERSDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(FARMERSDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(FARMERSDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

