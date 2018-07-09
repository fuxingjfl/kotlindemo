package com.fs.kotlindemo

import android.app.Service
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewSwitcher
import javax.xml.validation.Validator
import android.R.string.cancel
import android.os.Vibrator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.R.attr.animation
import android.R.attr.onClick
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.LinearInterpolator
import android.widget.EditText
import java.util.*


class MainActivity : AppCompatActivity() {

    private val m1:Int =5;

    private val m2:Int =5;

    private var m3:Int=0;

    private var tv_content:TextView ?=null;


    private val xz=10;
    private var et_sr:EditText?=null

    private var zd:Vibrator ?=null
    private var bsv:BitmapShaderView?=null
    private var rot: RotateAnimation?=null
    private var riv:RectangleImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_content=findViewById(R.id.tv_content) as TextView
        et_sr=findViewById(R.id.et_sr) as EditText
        bsv= findViewById(R.id.bsv) as BitmapShaderView?
        riv=findViewById(R.id.riv) as RectangleImageView
        zd= getSystemService(Service.VIBRATOR_SERVICE) as Vibrator?
        zd!!.vibrate(longArrayOf(100.toLong(), 200.toLong(), 100.toLong(), 200.toLong()), 0.toInt())
        zd!!.cancel()
        tv_content?.setText("好纠结")
        m3=m1+m2
        print("数据为"+m3);
        tv_content?.setText("结果为"+m3)

        add(5,8)

        Toast.makeText(this@MainActivity,sum(5.6f,8).toString()+"ss",Toast.LENGTH_LONG).show();

        var  p:person=obj()

        p.age="哈哈哈"
        p.introduction="擦111111111111111111111111111111"
        p.list = ArrayList<Int>()
        for(i in 0..5){
            p.list!!.add(i)
        }
        guang();
        sw()

//        var viewdj : (v:View) -> Unit={ v-> viewClicked(v)}
        // 声明并初始化View对象

        tv_content!!.setOnClickListener ( {v-> viewClicked(v)});

        rot = RotateAnimation(0F, 360F, 200F, 200F)
        //不停顿
        rot!!.setInterpolator(LinearInterpolator())
        //重复次数
        rot!!.setRepeatCount(-1)
        rot!!.setFillAfter(true)
        rot!!.setDuration(2000)
        bsv!!.startAnimation(rot)
        riv!!.setOnMoveListener({v-> Move(v)})
    }

    private fun Move(i:Float){

        Log.e("TAG","移动了多少**********"+i)

    }

    private fun viewClicked(v:View?){
        Log.e("TAG","点击了文本啦啦啦啦啦啦啦");
        val intent = Intent(this@MainActivity, TestActivity::class.java)
        startActivity(intent)
    }


    //kotlin返回无参数的方法
    private fun  add(a1:Int,a2 :Int) :Unit{
        Log.e("TAG","输出结果："+(a1*a2))
    }
    //kotlin返回有参数的方法
    private fun sum(a1:Float,a2:Int):Float{
        return a1*a2
    }
    private fun obj():person{
        var p = person();
        return  p;
    }
    private fun guang(){
        Log.e("TAG","数字大小：：："+Int.MAX_VALUE)
//            for (i in 0..xz){
//                Log.e("TAG","值：：："+i)
//                for (j in xz..0){
//                    var m=i+j
//                    Log.e("TAG","算数结果：："+m)
//                }
//            }
        var i=0

        while(i<=xz){

            if(i!=5){

                var si= i+(xz-i);
                Log.e("TAG","数据格式：：："+si)

            }else {
                break;
            }
            i++
        }
    }
    private fun sw():Unit{
        var a:Double=Math.random()*50
        when(a){
            in 0..20 ->Log.e("TAG","在0到20区间内")
            35.0,45.0 ->Log.e("TAG","是35 or 45")
            else -> {
                Log.e("TAG","是个鸡吧#################")
            }
        }
    }
}
