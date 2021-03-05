package eu.welfare.newneeds.data.network.responses

import eu.welfare.newneeds.data.db.entities.Quote

data class QuotesResponse(

    val isSuccessful : Boolean,
    val quotes : List<Quote>
)