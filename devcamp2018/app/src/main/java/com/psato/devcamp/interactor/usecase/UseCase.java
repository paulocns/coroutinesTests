package com.psato.devcamp.interactor.usecase;

import android.support.annotation.NonNull;

import com.psato.devcamp.interactor.rx.RxSchedulers;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;

public abstract class UseCase {

    private Disposable subscription = Disposables.empty();


    protected abstract Single buildUseCaseObservable();


    @SuppressWarnings("unchecked")
    public void execute(@NonNull Consumer onSuccess, @NonNull Consumer<? super Throwable> onError) {
        execute(onSuccess, onError, RxSchedulers.applyDefaultSchedulers());
    }


    @SuppressWarnings("unchecked")
    public void execute(@NonNull Consumer onSuccess, @NonNull Consumer<? super Throwable> onError, SingleTransformer transformer) {
        // Need to use the calling chain. It does not work if we break the chain like:
        if (transformer != null) {
            subscription = buildUseCaseObservable()
                    .compose(transformer)
                    .subscribe(onSuccess, onError);
        } else {
            subscription = buildUseCaseObservable()
                    .subscribe(onSuccess, onError);
        }
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }
}
