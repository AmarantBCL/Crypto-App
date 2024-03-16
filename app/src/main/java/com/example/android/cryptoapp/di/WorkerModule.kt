package com.example.android.cryptoapp.di

import androidx.work.ListenableWorker
import com.example.android.cryptoapp.data.workers.ChildWorkerFactory
import com.example.android.cryptoapp.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorker(factory: RefreshDataWorker.Factory): ChildWorkerFactory
}