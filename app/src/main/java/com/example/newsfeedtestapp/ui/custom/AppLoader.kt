package com.example.newsfeedtestapp.ui.custom

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.newsfeedtestapp.R
import com.example.newsfeedtestapp.utils.delayAction
import com.example.newsfeedtestapp.utils.makeInvisibleAlpha
import com.example.newsfeedtestapp.utils.makeVisibleAlpha

class AppLoader(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) : FrameLayout(context, attributeSet, defStyle) {

    init {
        inflate(context, R.layout.layout_loader, this)
        alpha = 0f
    }

    fun show(activity: Activity) {
        removeView()
        val view = (activity.window.decorView as ViewGroup)
        view.addView(this)
        makeVisibleAlpha(300)
    }

    private fun removeView() {
        if (parent != null)
            (parent as ViewGroup).removeView(this)
    }

    fun dismiss(activity: Activity, onDismiss: () -> Unit = {})  {
        val parentView = this.findViewById<ConstraintLayout>(R.id.parentView)
        parentView?.apply {
            isFocusable = false
            isClickable = false
        }
        makeInvisibleAlpha(300)
        delayAction(300) {
            (activity.window.decorView as ViewGroup).removeView(this@AppLoader)
            onDismiss.invoke()
        }
    }

}