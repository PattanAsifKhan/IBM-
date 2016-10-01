
package com.ibm.mobileappbuilder.grama20160930135917.presenters;

import com.ibm.mobileappbuilder.grama20160930135917.R;
import com.ibm.mobileappbuilder.grama20160930135917.ds.LOCALMARTDSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class LOCALMARTDetailPresenter extends BasePresenter implements DetailCrudPresenter<LOCALMARTDSItem>,
      Datasource.Listener<LOCALMARTDSItem> {

    private final CrudDatasource<LOCALMARTDSItem> datasource;
    private final DetailView view;

    public LOCALMARTDetailPresenter(CrudDatasource<LOCALMARTDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(LOCALMARTDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(LOCALMARTDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(LOCALMARTDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

