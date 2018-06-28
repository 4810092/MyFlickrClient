package com.gka.myflickclient.network

import com.gka.myflickclient.models.PhotosResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("/services/rest")
    fun photosSearch(@Query("method") method: String,
                     @Query("api_key") api_key: String,
                     @Query("text") text: String?,
                     @Query("media") media: String,
                     @Query("per_page") per_page: Int,
                     @Query("page") page: Int,
                     @Query("format") format: String,
                     @Query("nojsoncallback") noJsonCallback: String): Observable<PhotosResponse>
}