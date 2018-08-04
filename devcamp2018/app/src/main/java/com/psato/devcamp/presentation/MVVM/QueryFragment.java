package com.psato.devcamp.presentation.MVVM;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psato.devcamp.R;
import com.psato.devcamp.databinding.FragmentQueryMvvmBinding;
import com.psato.devcamp.presentation.base.BaseFragment;

public class QueryFragment extends BaseFragment {
    private FragmentQueryMvvmBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query_mvvm, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        QueryViewModelArc queryViewModelArc =
                ViewModelProviders.of(this, getViewModelFactory()).get(QueryViewModelArc.class);
        binding.setViewModel(queryViewModelArc);
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
