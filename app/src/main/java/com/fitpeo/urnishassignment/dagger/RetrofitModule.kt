package com.fitpeo.urnishassignment.dagger

import androidx.lifecycle.MutableLiveData
import com.fitpeo.urnishassignment.BuildConfig
import com.fitpeo.urnishassignment.retrofit.Endpoints
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

data class Error(val code: Int, val msg: String)

@Module
class RetrofitModule {

    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BASIC
    }
    val onServerError by lazy {
        MutableLiveData<Error>()
    }

    private val client =
        OkHttpClient().newBuilder().apply {
            this.addInterceptor(loggingInterceptor)
            this.connectTimeout(30, TimeUnit.SECONDS)
            this.readTimeout(30, TimeUnit.SECONDS)
            this.addInterceptor { chain: Interceptor.Chain ->

                val requestBuilder: Request.Builder = chain.request().newBuilder()
                chain.proceed(requestBuilder.build())
            }
            this.addInterceptor { chain: Interceptor.Chain ->
                val request = chain.request()

                val response = chain.proceed(request)

                if (response.code == 400 ||
                    response.code == 401 ||
                    response.code == 403 ||
                    response.code == 500 ||
                    response.code == 502 ||
                    response.code == 503
                ) {
                    onServerError.postValue(Error(response.code, ""))
                }
                response
            }
        }.build()

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit): Endpoints {
        return retrofit.create(Endpoints::class.java)
    }
}