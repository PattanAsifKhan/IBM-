
package com.ibm.mobileappbuilder.grama20160930135917.presenters;

import com.ibm.mobileappbuilder.grama20160930135917.R;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSIDDSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class FARMERSIDPresenter extends BasePresenter implements ListCrudPresenter<FARMERSIDDSItem>,
      Datasource.Listener<FARMERSIDDSItem>{

    private final CrudDatasource<FARMERSIDDSItem> crudDatasource;
    private final CrudListView<FARMERSIDDSItem> view;

    public FARMERSIDPresenter(CrudDatasource<FARMERSIDDSItem> crudDatasource,
                                         CrudListView<FARMERSIDDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(FARMERSIDDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<FARMERSIDDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(FARMERSIDDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(FARMERSIDDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(FARMERSIDDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

