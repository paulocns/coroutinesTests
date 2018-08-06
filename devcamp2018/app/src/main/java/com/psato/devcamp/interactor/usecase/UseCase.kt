package com.psato.devcamp.interactor.usecase

import android.util.Log
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

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

    protected suspend fun <X> background(block: suspend () -> X): Deferred<X> {
        return async(CommonPool, parent = parentJob) {
            block.invoke()
        }
    }

    fun unsubscribe(){
        parentJob.cancel()
    }

}
