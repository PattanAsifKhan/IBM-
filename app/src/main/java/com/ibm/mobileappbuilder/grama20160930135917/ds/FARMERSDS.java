

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
 * "FARMERSDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class FARMERSDS extends AppNowDatasource<FARMERSDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private FARMERSDSService service;

    public static FARMERSDS getInstance(SearchOptions searchOptions){
        return new FARMERSDS(searchOptions);
    }

    private FARMERSDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = FARMERSDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<FARMERSDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<FARMERSDSItem>>() {
                @Override
                public void onSuccess(List<FARMERSDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new FARMERSDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getFARMERSDSItemById(id, new Callback<FARMERSDSItem>() {
                @Override
                public void success(FARMERSDSItem result, Response response) {
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
    public void getItems(final Listener<List<FARMERSDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<FARMERSDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryFARMERSDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<FARMERSDSItem>>() {
            @Override
            public void success(List<FARMERSDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"nAME", "pHONENO"};
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
    public void create(FARMERSDSItem item, Listener<FARMERSDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createFARMERSDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createFARMERSDSItem(item, callbackFor(listener));
        
    }

    private Callback<FARMERSDSItem> callbackFor(final Listener<FARMERSDSItem> listener) {
      return new Callback<FARMERSDSItem>() {
          @Override
          public void success(FARMERSDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(FARMERSDSItem item, Listener<FARMERSDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateFARMERSDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateFARMERSDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(FARMERSDSItem item, final Listener<FARMERSDSItem> listener) {
                service.getServiceProxy().deleteFARMERSDSItemById(item.getIdentifiableId(), new Callback<FARMERSDSItem>() {
            @Override
            public void success(FARMERSDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<FARMERSDSItem> items, final Listener<FARMERSDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<FARMERSDSItem>>() {
            @Override
            public void success(List<FARMERSDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<FARMERSDSItem> items){
        List<String> ids = new ArrayList<>();
        for(FARMERSDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

