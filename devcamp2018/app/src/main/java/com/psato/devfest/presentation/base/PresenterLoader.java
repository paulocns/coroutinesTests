package com.psato.devfest.presentation.base;


import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by psato on 29/10/16.
 */

public abstract class PresenterLoader extends Loader<BasePresenter> {
    BasePresenter mPresenter;

    public PresenterLoader(Context context) {
        super(context);
    }


    public BasePresenter loadPresenter() {
        mPresenter = initializeInjector();
        mPresenter.start();
        return mPresenter;
    }

    protected abstract BasePresenter initializeInjector();

    /**
     * Handles a request to start the Loader.
     */
    @Override
    protected void onStartLoading() {
        if (mPresenter != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(mPresenter);
        } else {
            forceLoad();
        }
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
        BasePresenter presenter = loadPresenter();
        deliverResult(presenter);
    }

    /**
     * Handles a request to completely reset the Loader.
     */
    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (mPresenter != null) {
            onReleaseResources(mPresenter);
            mPresenter = null;
        }
    }

    /**
     * Helper function to take care of releasing resources associated
     * with an actively loaded data set.
     */
    protected void onReleaseResources(BasePresenter presenter) {
        presenter.stop();
    }
}
