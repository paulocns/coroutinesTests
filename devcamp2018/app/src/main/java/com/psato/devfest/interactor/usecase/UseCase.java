/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.psato.devfest.interactor.usecase;

import android.support.annotation.NonNull;

import com.psato.devfest.interactor.rx.RxSchedulers;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase {

    private Subscription mSubscription = Subscriptions.empty();

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link UseCase}.
     * Any business rules should be placed here by manipulating the Observable built before returning it to the
     * calling client
     */
    protected abstract Observable buildUseCaseObservable();

    /**
     * Executes the current use case using default transformer as provided by {@link RxSchedulers}
     *
     * @param useCaseSubscriber subscriber which will listen for results delivered by the Obsevable built
     *                          with {@link #buildUseCaseObservable()}.
     */
    @SuppressWarnings("unchecked")
    public void execute(@NonNull Subscriber useCaseSubscriber) {
        execute(useCaseSubscriber, RxSchedulers.applyDefaultSchedulers());
    }

    /**
     * Executes the current use case using the provided Transformer.
     *
     * @param useCaseSubscriber subscriber which will listen for results delivered by the Obsevable built
     *                          with {@link #buildUseCaseObservable()}.
     * @param transformer       the transformer to be applied on built observable. It can be to select
     *                          execution / delivery thread
     */
    @SuppressWarnings("unchecked")
    public void execute(@NonNull Subscriber useCaseSubscriber, Observable.Transformer transformer) {
        // Need to use the calling chain. It does not work if we break the chain like:
        if (transformer != null) {
            mSubscription = buildUseCaseObservable()
                    .compose(transformer)
                    .subscribe(useCaseSubscriber);
        } else {
            mSubscription = buildUseCaseObservable()
                    .subscribe(useCaseSubscriber);
        }
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
