
package com.ibm.mobileappbuilder.grama20160930135917.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface AsifDSServiceRest{

	@GET("/app/57ee6ffb11cb3e03009e921f/r/asifDS")
	void queryAsifDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<AsifDSItem>> cb);

	@GET("/app/57ee6ffb11cb3e03009e921f/r/asifDS/{id}")
	void getAsifDSItemById(@Path("id") String id, Callback<AsifDSItem> cb);

	@DELETE("/app/57ee6ffb11cb3e03009e921f/r/asifDS/{id}")
  void deleteAsifDSItemById(@Path("id") String id, Callback<AsifDSItem> cb);

  @POST("/app/57ee6ffb11cb3e03009e921f/r/asifDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<AsifDSItem>> cb);

  @POST("/app/57ee6ffb11cb3e03009e921f/r/asifDS")
  void createAsifDSItem(@Body AsifDSItem item, Callback<AsifDSItem> cb);

  @PUT("/app/57ee6ffb11cb3e03009e921f/r/asifDS/{id}")
  void updateAsifDSItem(@Path("id") String id, @Body AsifDSItem item, Callback<AsifDSItem> cb);

  @GET("/app/57ee6ffb11cb3e03009e921f/r/asifDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57ee6ffb11cb3e03009e921f/r/asifDS")
    void createAsifDSItem(
        @Part("data") AsifDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<AsifDSItem> cb);
    
    @Multipart
    @PUT("/app/57ee6ffb11cb3e03009e921f/r/asifDS/{id}")
    void updateAsifDSItem(
        @Path("id") String id,
        @Part("data") AsifDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<AsifDSItem> cb);
}

