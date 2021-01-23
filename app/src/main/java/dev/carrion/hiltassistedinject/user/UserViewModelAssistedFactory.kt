package dev.carrion.hiltassistedinject.user

import dagger.assisted.AssistedFactory

@AssistedFactory
interface UserViewModelAssistedFactory {

    fun create(name: String): UserViewModel

}