package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.drawable.ShapeDrawable
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.drawable.shapes.OvalShape
import android.util.Log
import android.view.animation.Animation
import android.view.animation.RotateAnimation


/**
 * Created by Administrator on 2018/6/20.
 */
class BitmapShaderView : View{

    private var mBitmap: Bitmap? = null
    private var sDrawable: ShapeDrawable? = null
    private var mPaint: Paint? = null
    private var bitW = 0
    private var bitH = 0     //Bitmap宽高
    private var mBitmapShader: Shader? = null   //Bitmap渲染
    private var mLinearGradient: Shader? = null //线性渐变渲染
    private var mComposeShader: Shader? = null //混合渲染
    private var mRadialGradient: Shader? = null //环形渐变渲染
    private var mSweepGradient: Shader? = null //梯度渲染




    constructor(con:Context , Attr:AttributeSet) : super(con,Attr){
        init()
    }

    constructor(con:Context , Attr:AttributeSet,def:Int) : super(con,Attr,def){


    }


    private fun init() {

//        mBitmap = BitmapFactory.decodeResource(resources, R.drawable.wz)
//        bitW = (mBitmap as Bitmap?)!!.width
//        bitH = (mBitmap as Bitmap?)!!.height
        mPaint = Paint()
//
//        //创建BitmapShader
//        mBitmapShader = BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR)

        //创建LinearGradient并设置渐变的颜色数组
        mLinearGradient = LinearGradient(0f, 0f, 100f, 100f,
                intArrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.WHITE), null, Shader.TileMode.REPEAT)

//        //混合渲染，这里使用了BitmapShader和LinearGradient进行混合，可以试试其他~
//        mComposeShader = ComposeShader(mBitmapShader, mLinearGradient, PorterDuff.Mode.DARKEN)

        //环形渐变渲染
        mRadialGradient = RadialGradient(50f, 200f, 50f,
                intArrayOf(Color.GREEN, Color.RED, Color.BLUE, Color.WHITE), null, Shader.TileMode.REPEAT)

        //梯度渲染
        mSweepGradient = SweepGradient(200f, 200f, intArrayOf(Color.GREEN,Color.WHITE), null)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var width : Int?=0
        var height:Int
        var ws=MeasureSpec.getSize(widthMeasureSpec);

        var wm=MeasureSpec.getMode(widthMeasureSpec)

        var hs = MeasureSpec.getSize(heightMeasureSpec)

        var hm=MeasureSpec.getMode(heightMeasureSpec)


        if(wm==MeasureSpec.EXACTLY){

            width=ws

        }

        if(hm==MeasureSpec.EXACTLY){

            height=hs

        }else{

            height=400

            height=MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY)
            Log.e("TAG","执行了@@@@@@@！！！！！！！！！！！！！！！！！！！！")
        }

        super.onMeasure(width!!, height)


        setMeasuredDimension(width,height)

    }

    override protected fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        //将图片裁剪为椭圆形
//        sDrawable = ShapeDrawable(OvalShape())
//        sDrawable!!.paint.shader = mBitmapShader
//        sDrawable!!.setBounds(0, 0, bitW, bitH)
//        sDrawable!!.draw(canvas)

//        //绘制线性渐变的矩形
//        mPaint!!.setShader(mLinearGradient)
//        canvas.drawRect(bitW.toFloat(), 0F, (bitW * 2).toFloat(), bitH.toFloat(), mPaint)

//        //绘制混合渲染效果
//        mPaint!!.setShader(mComposeShader)
//        canvas.drawRect(0F, bitH.toFloat(), bitW.toFloat(), (bitH * 2).toFloat(), mPaint)

//        //绘制环形渐变
//        mPaint!!.setShader(mRadialGradient)
//        canvas.drawCircle(bitW * 2.8f, (bitH / 2).toFloat(), (bitH / 2).toFloat(), mPaint)

       var r:RectF  = RectF()

        //绘制梯度渐变
        mPaint!!.setShader(mSweepGradient)
        canvas.drawCircle(200.toFloat(), 200.toFloat(), 200.toFloat(), mPaint)
    }

}