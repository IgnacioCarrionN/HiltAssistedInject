package dev.carrion.hiltassistedinject.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.carrion.hiltassistedinject.databinding.FragmentUserBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : Fragment() {

    private val navArgs: UserFragmentArgs by navArgs()

    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding!!

    @Inject
    lateinit var assistedFactory: UserViewModelAssistedFactory

    private val userViewModel: UserViewModel by viewModels {
        UserViewModel.Factory(
            assistedFactory,
            navArgs.name
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            userViewModel.message.collect {
                binding.name.text = it
            }
        }
    }
}