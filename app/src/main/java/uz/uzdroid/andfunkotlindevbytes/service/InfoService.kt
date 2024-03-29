package uz.uzdroid.andfunkotlindevbytes.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import uz.uzdroid.andfunkotlindevbytes.model.Videos

private const val BASE_URL = "https://devbytes.udacity.com/"

interface InfoService {

    @GET("/devbytes.json")
    fun getInfo(): Deferred<Videos>
}

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

object InfoApi {
    val infoService: InfoService by lazy {
        retrofit.create(InfoService::class.java)
    }
}
