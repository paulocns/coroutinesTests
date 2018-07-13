package com.psato.devfest.presentation.base;


import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by psato on 29/10/16.
 */

public abstract class ViewModelLoader extends Loader<BaseViewModel> {
    BaseViewModel mViewModel;

    public ViewModelLoader(Context context) {
        super(context);
    }


    public BaseViewModel loadViewModel() {
        mViewModel = initializeInjector();
        mViewModel.start();
        return mViewModel;
    }

    protected abstract BaseViewModel initializeInjector();

    /**
     * Handles a request to start the Loader.
     */
    @Override
    protected void onStartLoading() {
        if (mViewModel != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(mViewModel);
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
        BaseViewModel viewmodel = loadViewModel();
        deliverResult(viewmodel);
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
        if (mViewModel != null) {
            onReleaseResources(mViewModel);
            mViewModel = null;
        }
    }

    /**
     * Helper function to take care of releasing resources associated
     * with an actively loaded data set.
     */
    protected void onReleaseResources(BaseViewModel viewmodel) {
        viewmodel.stop();
    }
}
