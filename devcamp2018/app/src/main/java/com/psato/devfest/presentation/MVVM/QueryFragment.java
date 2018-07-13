package com.psato.devfest.presentation.MVVM;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psato.devfest.R;
import com.psato.devfest.databinding.FragmentQueryMvvmBinding;
import com.psato.devfest.infrastructure.Constants;
import com.psato.devfest.infrastructure.DevFestApplication;
import com.psato.devfest.presentation.base.BaseFragment;

import java.lang.ref.WeakReference;

public class QueryFragment extends BaseFragment{
    private FragmentQueryMvvmBinding mBinding;
    private QueryViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query_mvvm, container, false);
        mBinding = DataBindingUtil.bind(view);
        getLoaderManager().initLoader(Constants.LoaderID.MVVM_ID, new Bundle(), new LoaderCallBack(this));
        return view;
    }


    private void initializePresenter(QueryViewModel viewModel) {
        mViewModel = viewModel;
        if(mBinding != null){
            mBinding.setViewModel(mViewModel);
        }
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        super.onDestroyView();
    }

    private static class LoaderCallBack implements LoaderManager.LoaderCallbacks {

        private WeakReference<QueryFragment> mFragmentReference;

        LoaderCallBack(QueryFragment fragment) {
            mFragmentReference = new WeakReference<>(fragment);
        }

        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            QueryFragment fragment = mFragmentReference.get();
            if (fragment != null) {
                DevFestApplication app = (DevFestApplication) fragment.getActivity().getApplication();
                return new QueryVMLoader(fragment.getContext(), app.getApplicationComponent());
            }
            return null;
        }

        @Override
        public void onLoadFinished(Loader loader, Object data) {
            QueryFragment fragment = mFragmentReference.get();
            if (fragment != null) {
                fragment.initializePresenter((QueryViewModel) data);
            }
        }

        @Override
        public void onLoaderReset(Loader loader) {
            loader.reset();
        }
    }
}
