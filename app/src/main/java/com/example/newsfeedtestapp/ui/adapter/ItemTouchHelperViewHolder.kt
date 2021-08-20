package com.example.newsfeedtestapp.ui.adapter

import androidx.recyclerview.widget.ItemTouchHelper

/**
 * Code taken and adapted from: https://github.com/SG-K/RecyclerviewWithBenefits
 */
interface ItemTouchHelperViewHolder {
    /**
     * Called when the [ItemTouchHelper] first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    fun onItemSelected()


    /**
     * Called when the [ItemTouchHelper] has completed the move or swipe, and the active item
     * state should be cleared.
     */
    fun onItemClear()
}