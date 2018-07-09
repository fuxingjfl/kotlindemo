package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Shader
import android.graphics.LinearGradient



/**
 * Created by Administrator on 2018/7/6.
 */
class MyTextView : View {

    var paint:Paint ?=null
    var str:String ?="神罗天征"
    var textw:Int?=null
    var texty:Int?=null
    var lin:LinearGradient ?=null

    constructor(con:Context):super(con){
        init()
    }
    constructor(con:Context,attr:AttributeSet):super(con,attr){
        init()
    }
    constructor(con:Context,attr:AttributeSet,def:Int):super(con,attr,def){
        init()
    }

    fun init(){
        paint = Paint()
        paint!!.isAntiAlias=true
        paint!!.color=Color.GREEN
        paint!!.textSize=80.toFloat()
        var re:Rect = Rect()
        paint!!.getTextBounds(str,0.toInt(),str!!.length,re)
        textw=re.width()
        texty=re.height()
        lin = android.graphics.LinearGradient(
                0f,
                0f,
                textw!!.toFloat(),
                0f,
                intArrayOf(Color.BLUE, 0xffffff, Color.BLUE),
                null,
                Shader.TileMode.CLAMP)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        paint!!.setShader(lin)
        canvas!!.drawText("",100.toFloat(),100.toFloat(),paint)
        postInvalidateDelayed(100);
    }
}