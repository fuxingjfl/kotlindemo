package com.fs.kotlindemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * Created by Administrator on 2018/6/27.
 */
class RedPointView : View {

    private var paint:Paint?=null
    private var xy_Bj:Int?=60
    private var dy_Bj:Int?=100
    private var xw:Int?=150
    private var yh:Int?=150
    private var isyd:Boolean =false

    private var isxs:Boolean =false

    constructor(con: Context):super(con){
        init()
    }

    constructor(con: Context, attr: AttributeSet):super(con,attr){
        init()
    }

    constructor(con: Context, attr: AttributeSet, def:Int):super(con,attr,def){
        init()
    }

    fun init(){

        paint = Paint()
        paint!!.isAntiAlias=true
        paint!!.color=Color.RED
        paint!!.style=Paint.Style.FILL_AND_STROKE

        setBackgroundResource(R.drawable.bz)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(!isxs){
            canvas!!.drawCircle(150!!.toFloat(), 150!!.toFloat(), xy_Bj!!.toFloat(),paint)
            canvas!!.drawCircle(xw!!.toFloat(), yh!!.toFloat(), dy_Bj!!.toFloat(),paint)
            Irregular(canvas)
        }else{
            dy_Bj=0
            xy_Bj=0
//            var bg:AnimationDrawable?= background as AnimationDrawable?
//            bg!!.start()
        }
    }

    private fun blast(){



    }


    private fun Irregular(canvas: Canvas?){
        var dx:Float=xw!!.toFloat()-150f
        var dy:Float=yh!!.toFloat()-150f
        var a:Double=Math.atan((dy.toFloat()/dx.toFloat()).toDouble())

        //p0点
        var p0x:Float= (150+Math.sin(a)*xy_Bj!!.toFloat()).toFloat()
        var p0y:Float= (150-Math.cos(a)*xy_Bj!!.toFloat()).toFloat()

        //p1点
        var p1x:Float= (xw!!.toFloat()+Math.sin(a)*dy_Bj!!.toFloat()).toFloat()
        var p1y:Float= (yh!!.toFloat()-Math.cos(a)*dy_Bj!!.toFloat()).toFloat()

        //p3点
        var p3x:Float= (150-Math.sin(a)*xy_Bj!!.toFloat()).toFloat()
        var p3y:Float= (150+Math.cos(a)*xy_Bj!!.toFloat()).toFloat()

        //p2点
        var p2x:Float= (xw!!.toFloat()-Math.sin(a)*dy_Bj!!.toFloat()).toFloat()
        var p2y:Float= (yh!!.toFloat()+Math.cos(a)*dy_Bj!!.toFloat()).toFloat()

        //二阶贝瑟尔曲线的辅助坐标
        var fz_x:Float=(150+xw!!.toFloat())/2
        var fz_y:Float=(150+yh!!.toFloat())/2



        //开始画线
        var path:Path= Path()
        path.moveTo(p0x.toFloat(),p0y.toFloat())
        path.quadTo(fz_x,fz_y,p1x,p1y)
        path.lineTo(p2x,p2y)
        path.quadTo(fz_x,fz_y,p3x,p3y)
        path.lineTo(p0x,p0y)
        canvas!!.drawPath(path,paint)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event!!.action){
            MotionEvent.ACTION_DOWN ->{
                var dj_y:Int=event.getY().toInt()
                var dj_x:Int=event.getX().toInt()
                if((150!!.toInt()+ dy_Bj!!.toInt())>=dj_y&&(150!!.toInt()- dy_Bj!!.toInt())<=dj_y){
                    if((150!!.toInt()+ dy_Bj!!.toInt())>=dj_x&&(150!!.toInt()- dy_Bj!!.toInt())<=dj_x){
                        isyd=true
                    }else{
                        isyd=false
                    }
                }else{
                    isyd=false
                }
            }
            MotionEvent.ACTION_MOVE ->{

                if(isyd){

                    xw=event.getX().toInt()
                    yh=event.getY().toInt()

                }
                Log.e("TAG","移动：~"+xw+";;"+yh)
                var dx:Float=xw!!.toFloat()-150f
                var dy:Float=yh!!.toFloat()-150f
                var a:Double=Math.atan((dy.toFloat()/dx.toFloat()).toDouble())
                var xb:Float= (dy/Math.sin(a)).toFloat()
                if(xb>=700){
                    isxs=true
                }else{
                    isxs=false
                }
                postInvalidate()
            }
            MotionEvent.ACTION_UP ->{

                xw=150
                yh=150
                isyd=false
                postInvalidate()

            }
        }
        return true
    }
}