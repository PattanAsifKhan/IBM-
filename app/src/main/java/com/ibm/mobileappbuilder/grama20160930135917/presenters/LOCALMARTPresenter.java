
package com.ibm.mobileappbuilder.grama20160930135917.presenters;

import com.ibm.mobileappbuilder.grama20160930135917.R;
import com.ibm.mobileappbuilder.grama20160930135917.ds.LOCALMARTDSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class LOCALMARTPresenter extends BasePresenter implements ListCrudPresenter<LOCALMARTDSItem>,
      Datasource.Listener<LOCALMARTDSItem>{

    private final CrudDatasource<LOCALMARTDSItem> crudDatasource;
    private final CrudListView<LOCALMARTDSItem> view;

    public LOCALMARTPresenter(CrudDatasource<LOCALMARTDSItem> crudDatasource,
                                         CrudListView<LOCALMARTDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(LOCALMARTDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<LOCALMARTDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(LOCALMARTDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(LOCALMARTDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(LOCALMARTDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

