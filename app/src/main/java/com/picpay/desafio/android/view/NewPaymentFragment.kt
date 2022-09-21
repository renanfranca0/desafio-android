package com.picpay.desafio.android.view

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.FragmentNewPaymentBinding
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.utils.StateResult
import com.picpay.desafio.android.viewmodel.NewPaymentViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewPaymentFragment : Fragment() {

    private var _binding: FragmentNewPaymentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewPaymentViewModel by viewModels()

    private val args: NewPaymentFragmentArgs by navArgs()
    private var userId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId = args.userId
        viewModel.loadUser(userId)

        collects()
    }

    private fun collects() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateView.collect { stateResult ->
                    when(stateResult) {
                        is StateResult.InProgress -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is StateResult.Sucess -> {
                            binding.progressBar.visibility = View.GONE
                        }
                        is StateResult.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect { user ->
                    setupFields(user)
                }
            }
        }
    }

    private fun setupFields(user: User) {
        binding.nameUser.text = user.name
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.picture)

        binding.accountBalance.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.accountBalance.text = getString(R.string.value)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}