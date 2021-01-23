package dev.carrion.hiltassistedinject.user

import dev.carrion.hiltassistedinject.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override fun getMessage(name: String): String {
        return "Hi $name"
    }
}