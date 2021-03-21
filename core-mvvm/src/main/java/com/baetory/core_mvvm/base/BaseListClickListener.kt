package com.baetory.core_mvvm.base

interface BaseListClickListener<T> {

    fun onItemClick(clickedPosition: Int, clickedItem: T)

}