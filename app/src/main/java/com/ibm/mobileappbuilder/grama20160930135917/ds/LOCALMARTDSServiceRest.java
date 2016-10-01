
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

public interface LOCALMARTDSServiceRest{

	@GET("/app/57ee6ffb11cb3e03009e921f/r/lOCALMARTDS")
	void queryLOCALMARTDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<LOCALMARTDSItem>> cb);

	@GET("/app/57ee6ffb11cb3e03009e921f/r/lOCALMARTDS/{id}")
	void getLOCALMARTDSItemById(@Path("id") String id, Callback<LOCALMARTDSItem> cb);

	@DELETE("/app/57ee6ffb11cb3e03009e921f/r/lOCALMARTDS/{id}")
  void deleteLOCALMARTDSItemById(@Path("id") String id, Callback<LOCALMARTDSItem> cb);

  @POST("/app/57ee6ffb11cb3e03009e921f/r/lOCALMARTDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<LOCALMARTDSItem>> cb);

  @POST("/app/57ee6ffb11cb3e03009e921f/r/lOCALMARTDS")
  void createLOCALMARTDSItem(@Body LOCALMARTDSItem item, Callback<LOCALMARTDSItem> cb);

  @PUT("/app/57ee6ffb11cb3e03009e921f/r/lOCALMARTDS/{id}")
  void updateLOCALMARTDSItem(@Path("id") String id, @Body LOCALMARTDSItem item, Callback<LOCALMARTDSItem> cb);

  @GET("/app/57ee6ffb11cb3e03009e921f/r/lOCALMARTDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57ee6ffb11cb3e03009e921f/r/lOCALMARTDS")
    void createLOCALMARTDSItem(
        @Part("data") LOCALMARTDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<LOCALMARTDSItem> cb);
    
    @Multipart
    @PUT("/app/57ee6ffb11cb3e03009e921f/r/lOCALMARTDS/{id}")
    void updateLOCALMARTDSItem(
        @Path("id") String id,
        @Part("data") LOCALMARTDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<LOCALMARTDSItem> cb);
}

