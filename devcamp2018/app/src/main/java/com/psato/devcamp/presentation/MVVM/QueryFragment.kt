package com.psato.devcamp.presentation.MVVM

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.psato.devcamp.R
import com.psato.devcamp.databinding.FragmentQueryMvvmBinding
import com.psato.devcamp.presentation.base.BaseFragment

class QueryFragment : BaseFragment() {
    private var mBinding: FragmentQueryMvvmBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_query_mvvm, container, false)
        mBinding = DataBindingUtil.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val queryViewModelArc = ViewModelProviders.of(this, viewModelFactory).get(QueryViewModelArc::class.java)
        mBinding?.let{
            it.viewModel = queryViewModelArc
            it.setLifecycleOwner(this)
            it.executePendingBindings()
        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}
