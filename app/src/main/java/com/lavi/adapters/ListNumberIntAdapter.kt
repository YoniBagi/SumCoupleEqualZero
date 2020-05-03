package com.lavi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lavi.enums.ItemHeightEnum
import com.lavi.models.MyNumber
import com.lavi.sumcoupleequalzero.R
import com.lavi.sumcoupleequalzero.databinding.BigItemAdapterBinding
import com.lavi.sumcoupleequalzero.databinding.SmallItemAdapterBinding

class ListNumberIntAdapter(private val listNumber: MutableList<MyNumber>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemHeightEnum.BIG.getInt() -> {
                createBigVH(parent)
            }
            else -> {
                createSmallVH(parent)
            }
        }
    }

    private fun createSmallVH(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<SmallItemAdapterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.small_item_adapter,
            parent,
            false
        )
        return SmallViewHolder(binding)
    }

    private fun createBigVH(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<BigItemAdapterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.big_item_adapter,
            parent,
            false
        )
        return BigViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return listNumber[position].type.getInt()
    }

    override fun getItemCount(): Int = listNumber.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (listNumber[position].type.getInt()) {
            ItemHeightEnum.BIG.getInt() -> {
                holder as BigViewHolder
                holder.onBind(listNumber[position].number)
            }
            else -> {
                holder as SmallViewHolder
                holder.onBind(listNumber[position].number)
            }
        }
    }
}

class BigViewHolder(private val view: BigItemAdapterBinding) : RecyclerView.ViewHolder(view.root) {
    fun onBind(item: Int) {
        view.numberBig.text = item.toString()
    }
}

class SmallViewHolder(private val view: SmallItemAdapterBinding) :
    RecyclerView.ViewHolder(view.root) {
    fun onBind(item: Int) {
        view.numberSmall.text = item.toString()
    }
}