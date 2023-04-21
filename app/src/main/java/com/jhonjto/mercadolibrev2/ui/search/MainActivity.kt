package com.jhonjto.mercadolibrev2.ui.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhonjto.mercadolibrev2.adapters.RemoteJobAdapter
import com.jhonjto.mercadolibrev2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private lateinit var jobAdapter: RemoteJobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchJob()
    }

    private fun searchJob() {
        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchProducts(editable.toString())
                    }
                }
            }
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        jobAdapter = RemoteJobAdapter()
        binding.rvSearchItems.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = jobAdapter
        }

        viewModel.model.observe(this) { response ->
            binding.progress.visibility =
                if (response == SearchUiModel.Loading) View.VISIBLE else View.GONE

            when (response) {
                is SearchUiModel.Content -> jobAdapter.differ.submitList(response.products)
                is SearchUiModel.GenericError -> response.message
                else -> {}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}