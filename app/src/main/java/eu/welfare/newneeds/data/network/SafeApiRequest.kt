package eu.welfare.newneeds.data.network

import eu.welfare.newneeds.data.network.responses.AuthResponse
import eu.welfare.newneeds.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequest {

    // We define this fn as suspend as this is network operation which is to be performed asynchronously
    suspend fun<T:Any> apiRequest(call: () -> Response<AuthResponse>) : T{
        val response  = call.invoke()

        if (response.isSuccessful){
            return response.body()!!
        }else{

            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try{
                    message.append(JSONObject(it).getString("message"))

                }catch (e:JSONException){

                }
                message.append("\n")
            }
            message.append("Error Code : ${response.code()}")
            throw ApiException(message.toString())
        }

    }
}