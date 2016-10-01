
package com.ibm.mobileappbuilder.grama20160930135917.presenters;

import com.ibm.mobileappbuilder.grama20160930135917.R;
import com.ibm.mobileappbuilder.grama20160930135917.ds.FARMERSDSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class FARMERSCOMMUNITYPresenter extends BasePresenter implements ListCrudPresenter<FARMERSDSItem>,
      Datasource.Listener<FARMERSDSItem>{

    private final CrudDatasource<FARMERSDSItem> crudDatasource;
    private final CrudListView<FARMERSDSItem> view;

    public FARMERSCOMMUNITYPresenter(CrudDatasource<FARMERSDSItem> crudDatasource,
                                         CrudListView<FARMERSDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(FARMERSDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<FARMERSDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(FARMERSDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(FARMERSDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(FARMERSDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

