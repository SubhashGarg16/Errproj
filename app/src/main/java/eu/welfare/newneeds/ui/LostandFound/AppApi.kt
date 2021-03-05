package eu.welfare.newneeds.ui.LostandFound

import com.google.gson.GsonBuilder
import eu.welfare.newneeds.data.network.MyApi
import eu.welfare.newneeds.ui.LostandFound.Model.LostansFoundModel
import net.simplifiedcoding.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AppApi {



    @POST("savelostandfound")

    // This method cannot communicate directly with the LoginActivity. Have to go through ViewModel
    @Headers("Content-Type: application/json")
    suspend fun savelostandfound( @Body lostandfound: LostansFoundModel

    ) : Response<String>




    // Our repository will communicate with this API

    // This operator invoke will give us MyApi
//    companion object{
//        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : MyApi{
//
//            val okHttpClient = OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor)
//                .build()
//
//            return Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(MyApi::class.java)
//        }
//    }
    companion object{
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : MyApi{

            val okHttpClient = OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                // .baseUrl(BASE_URL)
                .baseUrl("http://mar2nddev-env.eba-3nracpbu.us-east-1.elasticbeanstalk.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(MyApi::class.java)
        }
    }
}