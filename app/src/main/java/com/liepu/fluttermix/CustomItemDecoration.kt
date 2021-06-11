package com.liepu.fluttermix

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @Author: liepu
 * @CreateDate: 2021/5/24 1:50 PM.
 * @Description:  描述具体内容
 * @UpdateUser:  更新者
 * @UpdateDate: 2021/5/24 1:50 PM.
 * @UpdateRemark:  更新说明
 */
class CustomItemDecoration : RecyclerView.ItemDecoration() {

    var paint: Paint = Paint()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(0, 0, 0, 50)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
//        val count: Int = parent.childCount
//        for (i in 0 until count - 1) {
//            drawLine(i, c, parent)
//        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val count: Int = parent.childCount
        for (i in 0 until count - 1) {
            drawLine(i, c, parent)
        }
    }

    private fun drawLine(index: Int, c: Canvas, parent: RecyclerView) {
//        获取当前itemView
        val child = parent.getChildAt(index)

        val left: Int = parent.paddingLeft + 50
        val right: Int = parent.width - parent.paddingRight - 5
        val top: Int = child.bottom
        val bottom: Int = top + 5;
        paint.color = Color.BLUE
        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
    }
}