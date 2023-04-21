package com.jhonjto.mercadolibrev2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jhonjto.domain.Result
import com.jhonjto.mercadolibrev2.databinding.JobLayoutAdapterBinding
import com.jhonjto.mercadolibrev2.ui.common.startActivity
import com.jhonjto.mercadolibrev2.ui.detail.DetailActivity
import org.xmlpull.v1.XmlPullParser.COMMENT

class RemoteJobAdapter : RecyclerView.Adapter<RemoteJobAdapter.RemoteJobViewHolder>() {

    private var binding: JobLayoutAdapterBinding? = null

    class RemoteJobViewHolder(itemBinding: JobLayoutAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object :
        DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteJobViewHolder {
        binding = JobLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RemoteJobViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: RemoteJobViewHolder, position: Int) {
        val currentJob = differ.currentList[position]
        var id: String

        currentJob.let {
            holder.itemView.apply {
                id = it.id!!

                Glide.with(this).load(it.thumbnail).into(binding?.ivProduct!!)

                binding?.tvCityName?.text = it.address?.city_name
                binding?.tvCondition?.text = it.condition
                binding?.tvPayMethod?.text = it.buying_mode
                binding?.tvPrice?.text = it.price.toString()
                binding?.tvName?.text = it.title

            }
        }

        holder.itemView.setOnClickListener { mView ->
            mView.context.startActivity<DetailActivity> {
                putExtra(DetailActivity.ID, id)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
