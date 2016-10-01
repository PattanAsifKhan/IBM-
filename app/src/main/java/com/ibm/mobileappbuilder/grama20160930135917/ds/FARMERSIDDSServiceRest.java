
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

public interface FARMERSIDDSServiceRest{

	@GET("/app/57ee6ffb11cb3e03009e921f/r/fARMERSIDDS")
	void queryFARMERSIDDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<FARMERSIDDSItem>> cb);

	@GET("/app/57ee6ffb11cb3e03009e921f/r/fARMERSIDDS/{id}")
	void getFARMERSIDDSItemById(@Path("id") String id, Callback<FARMERSIDDSItem> cb);

	@DELETE("/app/57ee6ffb11cb3e03009e921f/r/fARMERSIDDS/{id}")
  void deleteFARMERSIDDSItemById(@Path("id") String id, Callback<FARMERSIDDSItem> cb);

  @POST("/app/57ee6ffb11cb3e03009e921f/r/fARMERSIDDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<FARMERSIDDSItem>> cb);

  @POST("/app/57ee6ffb11cb3e03009e921f/r/fARMERSIDDS")
  void createFARMERSIDDSItem(@Body FARMERSIDDSItem item, Callback<FARMERSIDDSItem> cb);

  @PUT("/app/57ee6ffb11cb3e03009e921f/r/fARMERSIDDS/{id}")
  void updateFARMERSIDDSItem(@Path("id") String id, @Body FARMERSIDDSItem item, Callback<FARMERSIDDSItem> cb);

  @GET("/app/57ee6ffb11cb3e03009e921f/r/fARMERSIDDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57ee6ffb11cb3e03009e921f/r/fARMERSIDDS")
    void createFARMERSIDDSItem(
        @Part("data") FARMERSIDDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<FARMERSIDDSItem> cb);
    
    @Multipart
    @PUT("/app/57ee6ffb11cb3e03009e921f/r/fARMERSIDDS/{id}")
    void updateFARMERSIDDSItem(
        @Path("id") String id,
        @Part("data") FARMERSIDDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<FARMERSIDDSItem> cb);
}

