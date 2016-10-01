
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

public interface HELPDSServiceRest{

	@GET("/app/57ee6ffb11cb3e03009e921f/r/hELPDS")
	void queryHELPDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<HELPDSItem>> cb);

	@GET("/app/57ee6ffb11cb3e03009e921f/r/hELPDS/{id}")
	void getHELPDSItemById(@Path("id") String id, Callback<HELPDSItem> cb);

	@DELETE("/app/57ee6ffb11cb3e03009e921f/r/hELPDS/{id}")
  void deleteHELPDSItemById(@Path("id") String id, Callback<HELPDSItem> cb);

  @POST("/app/57ee6ffb11cb3e03009e921f/r/hELPDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<HELPDSItem>> cb);

  @POST("/app/57ee6ffb11cb3e03009e921f/r/hELPDS")
  void createHELPDSItem(@Body HELPDSItem item, Callback<HELPDSItem> cb);

  @PUT("/app/57ee6ffb11cb3e03009e921f/r/hELPDS/{id}")
  void updateHELPDSItem(@Path("id") String id, @Body HELPDSItem item, Callback<HELPDSItem> cb);

  @GET("/app/57ee6ffb11cb3e03009e921f/r/hELPDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
}

