package com.baetory.local

import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class DataSourceTest {

    @Before
    open fun setup(){
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    open fun tearDown(){
        /* explicitly empty */
    }
}