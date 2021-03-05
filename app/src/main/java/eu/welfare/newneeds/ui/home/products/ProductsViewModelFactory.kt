package eu.welfare.newneeds.ui.home.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.welfare.newneeds.data.repositories.ProductsRepository
import eu.welfare.newneeds.ui.home.profile.ProfileViewModel

class ProductsViewModelFactory (
    private val repository: ProductsRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewModel(repository) as T
    }
}