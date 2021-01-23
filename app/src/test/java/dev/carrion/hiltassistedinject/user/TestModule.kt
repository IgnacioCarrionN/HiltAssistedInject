package dev.carrion.hiltassistedinject.user

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class TestModule {

    companion object {
        @ExperimentalCoroutinesApi
        @Provides
        @Named("UserDispatcher")
        fun provideUserDispatcher(): CoroutineDispatcher = TestCoroutineDispatcher()
    }

    @Binds
    abstract fun provideUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository


}