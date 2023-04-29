package co.edu.uan.android.demojetpack421.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface CatApi {
    @GET("/v1/images/search")
    suspend fun getCats(): List<Cat>

    @POST("/v1/auth/login")
    suspend fun login(@Header("user") username: String, @Header("passwd") password: String): String

    companion object {
        fun getInstance() : CatApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CatApi::class.java)
        }
    }
}