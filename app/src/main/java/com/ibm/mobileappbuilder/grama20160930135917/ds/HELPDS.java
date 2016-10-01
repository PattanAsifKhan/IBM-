

package com.ibm.mobileappbuilder.grama20160930135917.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "HELPDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class HELPDS extends AppNowDatasource<HELPDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private HELPDSService service;

    public static HELPDS getInstance(SearchOptions searchOptions){
        return new HELPDS(searchOptions);
    }

    private HELPDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = HELPDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<HELPDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<HELPDSItem>>() {
                @Override
                public void onSuccess(List<HELPDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new HELPDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getHELPDSItemById(id, new Callback<HELPDSItem>() {
                @Override
                public void success(HELPDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<HELPDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<HELPDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryHELPDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<HELPDSItem>>() {
            @Override
            public void success(List<HELPDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"nAME", "eMAIL", "pHONENO"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(HELPDSItem item, Listener<HELPDSItem> listener) {
                          service.getServiceProxy().createHELPDSItem(item, callbackFor(listener));
          }

    private Callback<HELPDSItem> callbackFor(final Listener<HELPDSItem> listener) {
      return new Callback<HELPDSItem>() {
          @Override
          public void success(HELPDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(HELPDSItem item, Listener<HELPDSItem> listener) {
                          service.getServiceProxy().updateHELPDSItem(item.getIdentifiableId(), item, callbackFor(listener));
          }

    @Override
    public void deleteItem(HELPDSItem item, final Listener<HELPDSItem> listener) {
                service.getServiceProxy().deleteHELPDSItemById(item.getIdentifiableId(), new Callback<HELPDSItem>() {
            @Override
            public void success(HELPDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<HELPDSItem> items, final Listener<HELPDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<HELPDSItem>>() {
            @Override
            public void success(List<HELPDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<HELPDSItem> items){
        List<String> ids = new ArrayList<>();
        for(HELPDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

