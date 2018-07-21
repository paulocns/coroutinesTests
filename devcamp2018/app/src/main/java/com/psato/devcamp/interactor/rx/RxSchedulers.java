package com.psato.devcamp.interactor.rx;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by athila on 15/03/16.
 */
public class RxSchedulers {
    /**
     * Execute the operation on a new thread (from thread pool) and listen on the main thread.
     * It can be used for I/O operations
     *
     * @return the transformer properly configured
     */
    public static <T> SingleTransformer<T, T> applyDefaultSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Execute and listen the operation on the current thread. It can be used for scenarios where the
     * parallelism is already provided (operations executed by IntentService, for example)
     *
     * @return the transformer properly configured
     */
    public static <T> SingleTransformer<T, T> applyImmediateSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline());
    }
}
