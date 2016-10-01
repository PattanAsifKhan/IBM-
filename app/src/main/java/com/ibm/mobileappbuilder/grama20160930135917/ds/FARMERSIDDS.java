

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
 * "FARMERSIDDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class FARMERSIDDS extends AppNowDatasource<FARMERSIDDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private FARMERSIDDSService service;

    public static FARMERSIDDS getInstance(SearchOptions searchOptions){
        return new FARMERSIDDS(searchOptions);
    }

    private FARMERSIDDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = FARMERSIDDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<FARMERSIDDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<FARMERSIDDSItem>>() {
                @Override
                public void onSuccess(List<FARMERSIDDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new FARMERSIDDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getFARMERSIDDSItemById(id, new Callback<FARMERSIDDSItem>() {
                @Override
                public void success(FARMERSIDDSItem result, Response response) {
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
    public void getItems(final Listener<List<FARMERSIDDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<FARMERSIDDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryFARMERSIDDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<FARMERSIDDSItem>>() {
            @Override
            public void success(List<FARMERSIDDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{null};
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
    public void create(FARMERSIDDSItem item, Listener<FARMERSIDDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createFARMERSIDDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createFARMERSIDDSItem(item, callbackFor(listener));
        
    }

    private Callback<FARMERSIDDSItem> callbackFor(final Listener<FARMERSIDDSItem> listener) {
      return new Callback<FARMERSIDDSItem>() {
          @Override
          public void success(FARMERSIDDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(FARMERSIDDSItem item, Listener<FARMERSIDDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateFARMERSIDDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateFARMERSIDDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(FARMERSIDDSItem item, final Listener<FARMERSIDDSItem> listener) {
                service.getServiceProxy().deleteFARMERSIDDSItemById(item.getIdentifiableId(), new Callback<FARMERSIDDSItem>() {
            @Override
            public void success(FARMERSIDDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<FARMERSIDDSItem> items, final Listener<FARMERSIDDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<FARMERSIDDSItem>>() {
            @Override
            public void success(List<FARMERSIDDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<FARMERSIDDSItem> items){
        List<String> ids = new ArrayList<>();
        for(FARMERSIDDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

