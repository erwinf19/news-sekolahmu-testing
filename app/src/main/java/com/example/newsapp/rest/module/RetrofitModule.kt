package com.example.newsapp.rest.module

import android.content.Context
import com.example.newsapp.rest.api.ApiClient
import com.example.newsapp.rest.api.ApiService
import com.google.gson.GsonBuilder
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class RetrofitModule {

    private lateinit var apiInterface: ApiService
    private lateinit var httpClient: OkHttpClient
    private var retrofit: Retrofit? = null
    var url : String = ""


    fun getDefaultInterface () : ApiService {

        var gson = GsonBuilder().setLenient().create()
        var spec: ConnectionSpec? = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build()

        url = ApiClient.BASE_URL
        httpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

        httpClient.connectTimeoutMillis
        httpClient.readTimeoutMillis

        if (retrofit == null) {
            var retrofitBuilder = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .baseUrl(url)
            retrofit = retrofitBuilder.build()
        }
        apiInterface = retrofit!!.create(ApiService::class.java)
        return apiInterface
    }

}