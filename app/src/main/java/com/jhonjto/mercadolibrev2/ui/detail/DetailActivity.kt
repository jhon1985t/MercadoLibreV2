package com.jhonjto.mercadolibrev2.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jhonjto.mercadolibrev2.databinding.ActivityDetailBinding
import com.jhonjto.mercadolibrev2.ui.search.MainViewModel
import com.jhonjto.mercadolibrev2.ui.search.SearchUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val ID = "DetailActivity:id"
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String? = intent.getStringExtra(ID)

        id.let {
            viewModel.detailsProduct(it!!)
            viewModel.model.observe(this) { response ->
                binding.progress.visibility =
                    if (response == SearchUiModel.Loading) View.VISIBLE else View.GONE

                when (response) {
                    is SearchUiModel.Detail -> {
                        response.details.forEach { details ->
                            binding.tvId.text = details.body?.id
                            binding.tvTitle.text = details.body?.title
                            binding.tvCategoryId.text = details.body?.category_id
                            binding.tvPrice.text = details.body?.price.toString()
                        }
                    }
                    is SearchUiModel.GenericError -> {
                        response.message
                    }
                    else -> {}
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}