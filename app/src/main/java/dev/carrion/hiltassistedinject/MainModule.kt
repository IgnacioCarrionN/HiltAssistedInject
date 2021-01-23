package dev.carrion.hiltassistedinject

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dev.carrion.hiltassistedinject.user.UserRepository
import dev.carrion.hiltassistedinject.user.UserRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
abstract class MainModule {

    companion object {
        @Provides
        @Named("UserDispatcher")
        fun provideUserDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }

    @Binds
    abstract fun provideUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository


}