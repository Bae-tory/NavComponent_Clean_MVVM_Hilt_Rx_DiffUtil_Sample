package com.baetory.presentation.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.baetory.presentation.component.recyclerview.RecyclerViewSpaceItemDecoration

@BindingAdapter("itemSpace", "includeEdge", requireAll = false)
fun RecyclerView.setItemSpace(space: Float = 0f, includeEdge: Boolean = false) {
    val loop = itemDecorationCount
    for (i in 0 until loop) {
        val itemDecoration = getItemDecorationAt(i)
        if (itemDecoration is RecyclerViewSpaceItemDecoration) {
            removeItemDecorationAt(i)
            break
        }
    }
    addItemDecoration(RecyclerViewSpaceItemDecoration(space.toInt(), includeEdge))
}

@BindingAdapter("decorateItem")
fun RecyclerView.setItemDecoration(bool: Boolean) {
    if (bool) {

    }
    val dividerHorizontalItemDecoration =
        DividerItemDecoration(context, RecyclerView.HORIZONTAL)
    addItemDecoration(dividerHorizontalItemDecoration)
}
