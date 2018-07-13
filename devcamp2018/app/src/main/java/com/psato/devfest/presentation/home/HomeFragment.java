package com.psato.devfest.presentation.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psato.devfest.R;
import com.psato.devfest.databinding.FragmentHomeBinding;
import com.psato.devfest.infrastructure.Constants;
import com.psato.devfest.infrastructure.DevFestApplication;
import com.psato.devfest.presentation.base.BaseFragment;

import java.lang.ref.WeakReference;

public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mBinding = DataBindingUtil.bind(view);
        getLoaderManager().initLoader(Constants.LoaderID.HOME_ID, new Bundle(), new LoaderCallBack(this));
        return view;
    }


    private void bindViewModel(HomeFragmentViewModel viewModel) {
        mBinding.setViewModel(viewModel);
        mBinding.executePendingBindings();
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        super.onDestroyView();
    }

    private static class LoaderCallBack implements LoaderManager.LoaderCallbacks {

        private WeakReference<HomeFragment> mFragmentReference;

        LoaderCallBack(HomeFragment fragment) {
            mFragmentReference = new WeakReference<>(fragment);
        }

        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            HomeFragment fragment = mFragmentReference.get();
            if (fragment != null) {
                DevFestApplication app = (DevFestApplication) fragment.getActivity().getApplication();
                return new HomeVMLoader(fragment.getContext(), app.getApplicationComponent());
            }
            return null;
        }

        @Override
        public void onLoadFinished(Loader loader, Object data) {
            HomeFragment fragment = mFragmentReference.get();
            if (fragment != null) {
                fragment.bindViewModel((HomeFragmentViewModel) data);
            }
        }

        @Override
        public void onLoaderReset(Loader loader) {
            loader.reset();
        }
    }
}
