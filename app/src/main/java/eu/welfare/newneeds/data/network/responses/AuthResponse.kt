package eu.welfare.newneeds.data.network.responses

import eu.welfare.newneeds.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)
