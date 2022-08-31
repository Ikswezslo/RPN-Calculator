package com.example.rpn_calculator

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs


open class OnSwipeListener(context: Context?) : OnTouchListener {
    private val gestureDetector: GestureDetector
    private val swipeDistanceThreshold = 100
    private val swipeVelocityThreshold = 100

    open fun onSwipeLeft() {}
    open fun onSwipeRight() {}

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val distanceX = e2.x - e1.x
            val distanceY = e2.y - e1.y
            if (abs(distanceX) > abs(distanceY) && abs(distanceX) > swipeDistanceThreshold && abs(velocityX) > swipeVelocityThreshold) {
                if (distanceX > 0)
                    onSwipeRight()
                else
                    onSwipeLeft()
                return true
            }
            return false
        }
    }

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }
}