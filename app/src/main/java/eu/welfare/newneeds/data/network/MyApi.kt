package eu.welfare.newneeds.data.network

import com.google.gson.Gson
import eu.welfare.newneeds.data.network.responses.AuthResponse
import eu.welfare.newneeds.data.network.responses.CategoryResponse
import eu.welfare.newneeds.data.network.responses.QuotesResponse
import net.simplifiedcoding.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//This will be an interface for our retrofit calls

interface MyApi {

    // This function will return us the Retrofilt Call
    @FormUrlEncoded
    @POST("login")

    // This method cannot communicate directly with the LoginActivity. Have to go through ViewModel
   suspend fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ) : Response<AuthResponse>


   @FormUrlEncoded
   @POST("signup")
   suspend fun userSignup(
       @Field("name") name: String,
       @Field("email") email: String,
       @Field("password") password: String
   ) : Response<AuthResponse>

   // Our repository will communicate with this API
   @GET("quotes")
   suspend fun getQuotes() : Response<QuotesResponse>

   @GET( "listcategory")
   suspend fun getCategory(@Field("category") category: String) : Response<CategoryResponse>

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

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }



}