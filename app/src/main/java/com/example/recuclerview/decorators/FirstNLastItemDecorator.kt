package com.example.recuclerview.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FirstNLastItemDecorator(private val custom: Int, private val normal: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (position == 0 || position == parent.adapter!!.itemCount - 1) {
            outRect.set(custom, custom, custom, custom)
        } else {
            outRect.set(normal, normal, normal, normal)
        }
    }
}