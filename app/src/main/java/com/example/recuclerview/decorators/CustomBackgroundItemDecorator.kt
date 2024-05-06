package com.example.recuclerview.decorators

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomBackgroundItemDecorator (
    private val icon: Drawable,
    private val iconWidth: Int,
    private val iconHeight: Int
) :
    RecyclerView.ItemDecoration() {

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        val iconLeft = parent.paddingLeft
        val iconRight = iconLeft + iconWidth

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.top + params.topMargin
            val bottom = child.bottom - params.bottomMargin
            val iconTop = (top + bottom - iconHeight) / 2
            val iconBottom = iconTop + iconHeight

            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            icon.draw(canvas)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = iconWidth
    }
}