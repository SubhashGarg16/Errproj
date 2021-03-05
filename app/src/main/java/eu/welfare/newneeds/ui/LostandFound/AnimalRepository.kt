package eu.welfare.newneeds.ui.LostandFound

import eu.welfare.newneeds.data.network.SafeApiRequest
import eu.welfare.newneeds.data.network.responses.AuthResponse

class AnimalRepository(private val api: AppApi): SafeApiRequest() {
    suspend fun getallAnimals() : AuthResponse{
        return apiRequest { api.savelostandfound()}
    }

    fun getAnimals(){}
}