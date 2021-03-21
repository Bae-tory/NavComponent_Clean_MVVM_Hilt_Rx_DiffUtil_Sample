package com.baetory.domain.executor

import io.reactivex.rxjava3.core.Scheduler

interface ExecutorProvider {
    fun io(): Scheduler
    fun mainThread(): Scheduler
    fun computation(): Scheduler
    fun newThread(): Scheduler
}
