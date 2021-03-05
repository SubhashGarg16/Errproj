package eu.welfare.newneeds.data.network.responses

import eu.welfare.newneeds.data.db.entities.Quote
import eu.welfare.newneeds.data.models.Items
import java.util.*

data class CategoryResponse(
    var id: String,
    var categoryName: String,
    var listofItems:List<Items>
)