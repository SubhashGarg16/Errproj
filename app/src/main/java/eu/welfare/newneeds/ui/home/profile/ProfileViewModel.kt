package eu.welfare.newneeds.ui.home.profile

import androidx.lifecycle.ViewModel
import eu.welfare.newneeds.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()



}