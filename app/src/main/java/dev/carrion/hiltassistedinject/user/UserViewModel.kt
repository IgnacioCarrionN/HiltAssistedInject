package dev.carrion.hiltassistedinject.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Named

class UserViewModel @AssistedInject constructor(
    private val repository: UserRepository,
    @Named("UserDispatcher") private val dispatcher: CoroutineDispatcher,
    @Assisted private val name: String
) : ViewModel() {

    class Factory(
        private val assistedFactory: UserViewModelAssistedFactory,
        private val name: String,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return assistedFactory.create(name) as T
        }
    }

    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String>
        get() = _message

    init {
        viewModelScope.launch(dispatcher) {
            _message.emit(repository.getMessage(name))
        }
    }

}