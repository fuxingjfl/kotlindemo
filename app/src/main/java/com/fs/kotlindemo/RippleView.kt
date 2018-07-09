package com.fs.kotlindemo

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.graphics.Shader
import android.graphics.RadialGradient
import android.animation.Animator
import android.view.animation.AccelerateInterpolator
import android.animation.ObjectAnimator
import android.R.string.cancel
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View


/**
 * Created by Administrator on 2018/6/27.
 */
class RippleView :Button{

    private var mX: Int = 0
    private var mY: Int = 0
    private var mAnimator: ObjectAnimator? = null
    private val DEFAULT_RADIUS = 50
    private var mCurRadius = 0
    private var mRadialGradient: RadialGradient? = null
    private var mPaint: Paint? = null
    constructor(con: Context):super(con){
        init()
    }
    constructor(con: Context, attr: AttributeSet):super(con,attr){
        init()
    }
    constructor(con: Context, attr: AttributeSet, def:Int):super(con,attr,def){
        init()
    }
    private fun init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        mPaint = Paint()
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (mX !== event.getX().toInt() || mY !== mY) {
            mX = event.x.toInt()
            mY = event.y.toInt()
            setRadius(DEFAULT_RADIUS)
        }

        if (event.action == MotionEvent.ACTION_DOWN) {

            return true
        } else if (event.action == MotionEvent.ACTION_UP) {

            if (mAnimator != null && mAnimator!!.isRunning()) {
                mAnimator!!.cancel()
            }

            if (mAnimator == null) {
                mAnimator = ObjectAnimator.ofInt(this, "radius", DEFAULT_RADIUS, width)
            }

            mAnimator!!.setInterpolator(AccelerateInterpolator())
            mAnimator!!.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    Log.e("TAG","执行结束=======")
                    setRadius(0)
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
            mAnimator!!.start()
        }

        return super.onTouchEvent(event)
    }

    fun setRadius(radius: Int) {
        mCurRadius = radius
        if (mCurRadius > 0) {
            mRadialGradient = RadialGradient(mX.toFloat(), mY.toFloat(), mCurRadius.toFloat(), 0x00FFFFFF, 0xFF58FAAC.toInt(), Shader.TileMode.CLAMP)
            mPaint!!.setShader(mRadialGradient)
        }
        postInvalidate()
    }
    override protected fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(mX.toFloat(), mY.toFloat(), mCurRadius.toFloat(), mPaint)
    }
}