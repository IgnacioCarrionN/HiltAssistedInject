package dev.carrion.hiltassistedinject.user

interface UserRepository {
    fun getMessage(name: String): String
}