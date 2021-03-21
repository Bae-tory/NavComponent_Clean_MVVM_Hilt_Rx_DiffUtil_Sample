package com.baetory.presentation.component.recyclerview

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import com.dino.library.dinorecyclerview.DinoViewHolder
import com.dino.library.dinorecyclerview.ItemViewType

open class DiffListAdapter<T : Any>(
    @LayoutRes private val layoutResId: Int?,
    diffCallback: DiffUtil.ItemCallback<T>
) : androidx.recyclerview.widget.ListAdapter<T, DinoViewHolder>(diffCallback) {
    var eventHolder: Any? = null

    private var headerSize: Int = 0

    @LayoutRes
    var headerLayoutResId: Int? = null
        set(value) {
            if (value == field) {
                return
            }
            field = value
            headerSize = if (value == null) 0 else 1
        }
    var headerItem: Any? = null

    private var footerSize: Int = 0

    @LayoutRes
    var footerLayoutResId: Int? = null
        set(value) {
            if (value == field) {
                return
            }
            field = value
            footerSize = if (value == null) 0 else 1
        }
    var footerItem: Any? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DinoViewHolder(layoutResId = viewType, parent = parent)

    override fun onBindViewHolder(holder: DinoViewHolder, position: Int) {
        val item = when {
            isHeaderPosition(position) -> headerItem
            isFooterPosition(position) -> footerItem
            else -> getItem(position - headerSize)
        }
        holder.onBindViewHolder(item, eventHolder)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + headerSize + footerSize
    }

    override fun onViewAttachedToWindow(holder: DinoViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttach()
    }

    override fun onViewDetachedFromWindow(holder: DinoViewHolder) {
        holder.onDetach()
        super.onViewDetachedFromWindow(holder)
    }

    override fun getItemViewType(position: Int): Int {
        if (isHeaderPosition(position)) {
            return headerLayoutResId!!
        }
        if (isFooterPosition(position)) {
            return footerLayoutResId!!
        }
        return when (val item = getItem(position - headerSize)) {
            is ItemViewType -> item.itemLayoutResId
            else -> layoutResId ?: error(ADAPTER_CANNOT_CREATED_ERROR_MSG)
        }
    }

    private fun isHeaderPosition(position: Int) = headerSize != 0 && position == 0

    private fun isFooterPosition(position: Int) = footerSize != 0 && position == itemCount - 1

}

internal const val ADAPTER_CANNOT_CREATED_ERROR_MSG =
    "RecyclerView.Adapter cannot be created because there is no ViewHolder item layout.\n You must add \"dino_itemLayout\" attribute in RecyclerView or implement ItemViewType interface to your item class."

internal const val NEED_ON_LOAD_EVENT_HANDLING =
    "If you want to pagination, you must add \"dino_onLoad\" attribute"

internal const val NEED_RECYCLER_VIEW_LAYOUT_MANAGER_ERROR_MSG =
    "If you want to use \"dino_onLoad\" attr, you must add RecyclerView.LayoutManager like LinearLayoutManager or GridLayoutManager or StaggeredGridLayoutManager"

internal const val DO_NOT_SUPPORT_LAYOUT_MANAGER_ERROR_MSG =
    "This LayoutManager is not supported. Please use LinearLayoutManager or GridLayoutManager or StaggeredGridLayoutManager"