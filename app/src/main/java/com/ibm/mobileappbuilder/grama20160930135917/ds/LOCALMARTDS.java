

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
 * "LOCALMARTDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class LOCALMARTDS extends AppNowDatasource<LOCALMARTDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private LOCALMARTDSService service;

    public static LOCALMARTDS getInstance(SearchOptions searchOptions){
        return new LOCALMARTDS(searchOptions);
    }

    private LOCALMARTDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = LOCALMARTDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<LOCALMARTDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<LOCALMARTDSItem>>() {
                @Override
                public void onSuccess(List<LOCALMARTDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new LOCALMARTDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getLOCALMARTDSItemById(id, new Callback<LOCALMARTDSItem>() {
                @Override
                public void success(LOCALMARTDSItem result, Response response) {
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
    public void getItems(final Listener<List<LOCALMARTDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<LOCALMARTDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryLOCALMARTDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<LOCALMARTDSItem>>() {
            @Override
            public void success(List<LOCALMARTDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"contactName", "email", "city", "phone"};
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
    public void create(LOCALMARTDSItem item, Listener<LOCALMARTDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createLOCALMARTDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createLOCALMARTDSItem(item, callbackFor(listener));
        
    }

    private Callback<LOCALMARTDSItem> callbackFor(final Listener<LOCALMARTDSItem> listener) {
      return new Callback<LOCALMARTDSItem>() {
          @Override
          public void success(LOCALMARTDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(LOCALMARTDSItem item, Listener<LOCALMARTDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateLOCALMARTDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateLOCALMARTDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(LOCALMARTDSItem item, final Listener<LOCALMARTDSItem> listener) {
                service.getServiceProxy().deleteLOCALMARTDSItemById(item.getIdentifiableId(), new Callback<LOCALMARTDSItem>() {
            @Override
            public void success(LOCALMARTDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<LOCALMARTDSItem> items, final Listener<LOCALMARTDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<LOCALMARTDSItem>>() {
            @Override
            public void success(List<LOCALMARTDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<LOCALMARTDSItem> items){
        List<String> ids = new ArrayList<>();
        for(LOCALMARTDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

