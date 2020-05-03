package com.lavi.sumcoupleequalzero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lavi.adapters.ListNumberIntAdapter
import com.lavi.models.MyNumber
import com.lavi.sumcoupleequalzero.databinding.ActivityListBinding
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    private lateinit var listActivityViewModel: ListActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)
        binding.activity = this
        binding.lifecycleOwner = this
        setViewModel()
        fetchData()
    }

    private fun fetchData() {
        listActivityViewModel.fetchData()
    }

    private fun setViewModel() {
        listActivityViewModel = ViewModelProviders.of(this).get(ListActivityViewModel::class.java)
        listActivityViewModel.getListOfIntMLD().observe(this, Observer {fetchDataSuccess(it)})
    }

    private fun fetchDataSuccess(it: MutableList<MyNumber>) {
        listRecycler?.adapter = ListNumberIntAdapter(it)
    }

    fun getProgressUi(): LiveData<Boolean> {
        return listActivityViewModel.getProgressDataMLD()
    }
}
