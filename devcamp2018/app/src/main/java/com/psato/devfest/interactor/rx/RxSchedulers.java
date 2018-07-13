package com.psato.devfest.interactor.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    public static <T> Observable.Transformer<T, T> applyDefaultSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * Execute and listen the operation on the current thread. It can be used for scenarios where the
     * parallelism is already provided (operations executed by IntentService, for example)
     *
     * @return the transformer properly configured
     */
    public static <T> Observable.Transformer<T, T> applyImmediateSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.immediate())
                        .observeOn(Schedulers.immediate());
            }
        };
    }
}
