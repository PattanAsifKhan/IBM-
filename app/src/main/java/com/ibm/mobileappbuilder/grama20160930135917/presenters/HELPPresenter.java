
package com.ibm.mobileappbuilder.grama20160930135917.presenters;

import com.ibm.mobileappbuilder.grama20160930135917.R;
import com.ibm.mobileappbuilder.grama20160930135917.ds.HELPDSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class HELPPresenter extends BasePresenter implements ListCrudPresenter<HELPDSItem>,
      Datasource.Listener<HELPDSItem>{

    private final CrudDatasource<HELPDSItem> crudDatasource;
    private final CrudListView<HELPDSItem> view;

    public HELPPresenter(CrudDatasource<HELPDSItem> crudDatasource,
                                         CrudListView<HELPDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(HELPDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<HELPDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(HELPDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(HELPDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(HELPDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

