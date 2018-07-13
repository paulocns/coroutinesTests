package com.psato.devfest.presentation.MVP;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psato.devfest.R;
import com.psato.devfest.databinding.FragmentQueryMvpBinding;
import com.psato.devfest.infrastructure.Constants;
import com.psato.devfest.infrastructure.DevFestApplication;
import com.psato.devfest.presentation.base.BaseFragment;

import java.lang.ref.WeakReference;

public class QueryFragment extends BaseFragment implements QueryContract.View {
    private FragmentQueryMvpBinding mBinding;
    private QueryContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query_mvp, container, false);
        mBinding = DataBindingUtil.bind(view);
        getLoaderManager().initLoader(Constants.LoaderID.MVP_ID, new Bundle(), new LoaderCallBack(this));
        mBinding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPresenter != null && mBinding != null){
                    mPresenter.requestData(mBinding.editQuery.getText().toString());
                }
            }
        });
        return view;
    }


    private void initializePresenter(QueryContract.Presenter presenter) {
        presenter.setView(this);
        mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        if(mPresenter != null) {
            mPresenter.setView(null);
        }
        mPresenter =  null;
        super.onDestroyView();
    }

    @Override
    public void showLoading() {
        if(mBinding != null){
            mBinding.loadingLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if(mBinding != null){
            mBinding.loadingLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showResult(String name) {
        if(mBinding != null){
            mBinding.showResponse.setText(name);
        }
    }

    @Override
    public void showError() {
        if(mBinding != null){
            mBinding.showResponse.setText(getText(R.string.error));
        }
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
                return new QueryPresenterLoader(fragment.getContext(), app.getApplicationComponent());
            }
            return null;
        }

        @Override
        public void onLoadFinished(Loader loader, Object data) {
            QueryFragment fragment = mFragmentReference.get();
            if (fragment != null) {
                fragment.initializePresenter((QueryContract.Presenter) data);
            }
        }

        @Override
        public void onLoaderReset(Loader loader) {
            loader.reset();
        }
    }
}
