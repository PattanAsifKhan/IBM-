
package com.ibm.mobileappbuilder.grama20160930135917.presenters;

import com.ibm.mobileappbuilder.grama20160930135917.R;
import com.ibm.mobileappbuilder.grama20160930135917.ds.AsifDSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class GOTALKPresenter extends BasePresenter implements ListCrudPresenter<AsifDSItem>,
      Datasource.Listener<AsifDSItem>{

    private final CrudDatasource<AsifDSItem> crudDatasource;
    private final CrudListView<AsifDSItem> view;

    public GOTALKPresenter(CrudDatasource<AsifDSItem> crudDatasource,
                                         CrudListView<AsifDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(AsifDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<AsifDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(AsifDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(AsifDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(AsifDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

