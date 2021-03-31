package com.baetory.mock

import com.baetory.domain.executor.ExecutorProvider
import io.reactivex.rxjava3.schedulers.Schedulers

class TestExecutors : ExecutorProvider {
    override fun io() = Schedulers.trampoline()
    override fun mainThread() = Schedulers.trampoline()
    override fun computation() = Schedulers.trampoline()
    override fun newThread() = Schedulers.trampoline()
}