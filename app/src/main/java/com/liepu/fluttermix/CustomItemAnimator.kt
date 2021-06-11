package com.liepu.fluttermix

import android.animation.ObjectAnimator
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @Author: liepu
 * @CreateDate: 2021/5/24 2:49 PM.
 * @Description:  描述具体内容
 * @UpdateUser:  更新者
 * @UpdateDate: 2021/5/24 2:49 PM.
 * @UpdateRemark:  更新说明
 */
class CustomItemAnimator : RecyclerView.ItemAnimator() {
    override fun animateDisappearance(viewHolder: RecyclerView.ViewHolder, preLayoutInfo: ItemHolderInfo, postLayoutInfo: ItemHolderInfo?): Boolean {
        TODO("Not yet implemented")
    }

    override fun animateAppearance(viewHolder: RecyclerView.ViewHolder, preLayoutInfo: ItemHolderInfo?, postLayoutInfo: ItemHolderInfo): Boolean {
        ObjectAnimator.ofInt(viewHolder.itemView, "left", preLayoutInfo?.left ?:0, postLayoutInfo.left)
        return true
    }

    override fun animatePersistence(viewHolder: RecyclerView.ViewHolder, preLayoutInfo: ItemHolderInfo, postLayoutInfo: ItemHolderInfo): Boolean {
        TODO("Not yet implemented")
    }

    override fun animateChange(oldHolder: RecyclerView.ViewHolder, newHolder: RecyclerView.ViewHolder, preLayoutInfo: ItemHolderInfo, postLayoutInfo: ItemHolderInfo): Boolean {
        TODO("Not yet implemented")
    }

    override fun runPendingAnimations() {
        TODO("Not yet implemented")
    }

    override fun endAnimation(item: RecyclerView.ViewHolder) {
        TODO("Not yet implemented")
    }

    override fun endAnimations() {
        TODO("Not yet implemented")
    }

    override fun isRunning(): Boolean {
        return true
    }
}