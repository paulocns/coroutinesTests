/**
 * (C) Copyright 2018 Paulo Vitor Sato for Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.psato.devcamp.interactor.usecase

import android.util.Log
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a coroutine
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<T> {

    protected var parentJob: Job = Job()


    protected abstract suspend fun executeOnBackground(): T

    fun execute(onComplete: (T) -> Unit, onError: (Throwable) -> Unit) {
        parentJob.cancel()
        parentJob = Job()
        launch(UI,parent = parentJob) {
            try {
                val result = background {
                    executeOnBackground()
                }
                onComplete.invoke(result.await())
            } catch(e: CancellationException) {
                Log.d("UseCase", "canceled by user")
            }catch (e: Exception) {
                    onError(e)
            }
        }
    }

    protected suspend fun <X> background(context: CoroutineContext = CommonPool, block: suspend () -> X): Deferred<X> {
        return async(context, parent = parentJob) {
            block.invoke()
        }
    }

    fun unsubscribe(){
        parentJob.cancel()
    }

}
