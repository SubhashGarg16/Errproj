package eu.welfare.newneeds.ui.home.products

import androidx.lifecycle.ViewModel
import eu.welfare.newneeds.data.repositories.ProductsRepository
import eu.welfare.newneeds.util.lazyDeferred

class ProductsViewModel(
    repository: ProductsRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
    val category by lazyDeferred {
        repository.getCategory()
    }
}