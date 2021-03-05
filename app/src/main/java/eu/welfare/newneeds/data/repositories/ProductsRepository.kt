package eu.welfare.newneeds.data.repositories

import eu.welfare.newneeds.data.network.MyApi
import eu.welfare.newneeds.data.network.SafeApiRequest
import eu.welfare.newneeds.data.network.responses.CategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductsRepository(private val api: MyApi): SafeApiRequest() {
    suspend fun getCategory(): Response<CategoryResponse> {
        return withContext(Dispatchers.IO) {
            api.getCategory("Dairy")
        }
    }
}