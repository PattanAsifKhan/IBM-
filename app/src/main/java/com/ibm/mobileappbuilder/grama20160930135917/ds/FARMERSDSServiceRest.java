
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

public interface FARMERSDSServiceRest{

	@GET("/app/57ee6ffb11cb3e03009e921f/r/fARMERSDS")
	void queryFARMERSDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<FARMERSDSItem>> cb);

	@GET("/app/57ee6ffb11cb3e03009e921f/r/fARMERSDS/{id}")
	void getFARMERSDSItemById(@Path("id") String id, Callback<FARMERSDSItem> cb);

	@DELETE("/app/57ee6ffb11cb3e03009e921f/r/fARMERSDS/{id}")
  void deleteFARMERSDSItemById(@Path("id") String id, Callback<FARMERSDSItem> cb);

  @POST("/app/57ee6ffb11cb3e03009e921f/r/fARMERSDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<FARMERSDSItem>> cb);

  @POST("/app/57ee6ffb11cb3e03009e921f/r/fARMERSDS")
  void createFARMERSDSItem(@Body FARMERSDSItem item, Callback<FARMERSDSItem> cb);

  @PUT("/app/57ee6ffb11cb3e03009e921f/r/fARMERSDS/{id}")
  void updateFARMERSDSItem(@Path("id") String id, @Body FARMERSDSItem item, Callback<FARMERSDSItem> cb);

  @GET("/app/57ee6ffb11cb3e03009e921f/r/fARMERSDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57ee6ffb11cb3e03009e921f/r/fARMERSDS")
    void createFARMERSDSItem(
        @Part("data") FARMERSDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<FARMERSDSItem> cb);
    
    @Multipart
    @PUT("/app/57ee6ffb11cb3e03009e921f/r/fARMERSDS/{id}")
    void updateFARMERSDSItem(
        @Path("id") String id,
        @Part("data") FARMERSDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<FARMERSDSItem> cb);
}

