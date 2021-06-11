package com.liepu.fluttermix

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @Author: liepu
 * @CreateDate: 2021/5/24 5:22 PM.
 * @Description:  描述具体内容
 * @UpdateUser:  更新者
 * @UpdateDate: 2021/5/24 5:22 PM.
 * @UpdateRemark:  更新说明
 */
class CustomLayoutManager : RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
//        detach屏幕内的ViewHolder，加入到mAttachedScrap中
        detachAndScrapAttachedViews(recycler)
        // 遍历子view
        var offsetX = 0

        for(i in 0 until itemCount) {
            val child: View = recycler.getViewForPosition(i)
            addView(child)

            measureChildWithMargins(child, 0, 0)
            val width: Int = getDecoratedMeasuredWidth(child)
            val height: Int = getDecoratedMeasuredHeight(child)
            layoutDecorated(child, offsetX, 0, offsetX+width, height)
            offsetX += width
        }
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {

        while (true) {
            val lastView = getChildAt(childCount-1) ?: break
            if (lastView.right - dx >= width){
                break
            }
            val lastPosition = getPosition(lastView)
            val nextView: View = if (lastPosition == itemCount - 1) {
                recycler!!.getViewForPosition(0)
            } else{
                recycler!!.getViewForPosition(lastPosition + 1)
            }

            addView(nextView)

            measureChildWithMargins(nextView, 0, 0)
            val width = getDecoratedMeasuredWidth(nextView)
            val height = getDecoratedMeasuredHeight(nextView)
            layoutDecorated(nextView, lastView.right, 0, lastView.right+width, height)

        }
        offsetChildrenHorizontal(dx * -1)
        return dx
    }
}