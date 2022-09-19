package com.picpay.desafio.android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.ViewModelInitializer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ContactsFragmentBinding
import com.picpay.desafio.android.utils.StateResult
import com.picpay.desafio.android.view.adapter.UserListAdapter
import com.picpay.desafio.android.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactsFragments : Fragment() {

    private var _binding: ContactsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactsViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContactsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        clickListeners()
        viewModel.loadUsers()
        collects()
    }

    private fun clickListeners() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.loadUsers()
        }
    }

    private fun collects() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateView.collect { stateResult ->
                    when (stateResult) {
                        is StateResult.InProgress -> {
                            binding.swipeToRefresh.isRefreshing = false
                            binding.userListProgressBar.visibility = View.VISIBLE
                        }
                        is StateResult.Sucess -> {
                            binding.swipeToRefresh.isRefreshing = false
                            binding.userListProgressBar.visibility = View.GONE
                        }
                        is StateResult.Error -> {
                            binding.swipeToRefresh.isRefreshing = false
                            binding.userListProgressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.error),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect { users ->
                    adapter.users = users
                }
            }
        }
    }

    private fun setupRecyclerView() {
        recyclerView = binding.recyclerView
        progressBar = binding.userListProgressBar

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}