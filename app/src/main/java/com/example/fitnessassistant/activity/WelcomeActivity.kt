package com.example.fitnessassistant.activity

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.example.fitnessassistant.R
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    //先定義一個方法 設定intent會夾帶一個key過去mainActivity
    fun startTransition(flag:String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("flag", flag)

        startActivity(intent, ActivityOptionsCompat
            .makeSceneTransitionAnimation(this)
            .toBundle())

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Glide.with(this)
            .load(R.drawable.man_upper)
            .transform(ToonFilterTransformation(0.6F, 10F))
            .into(imgv_upper)
        Glide.with(this)
            .load(R.drawable.girl_down)
            .transform(ToonFilterTransformation(0.6F, 10F))
            .into(imgv_down)

        ObjectAnimator.ofFloat(layout_welcome, "alpha", 0f, 1f, 1f)
            .apply {
                duration = 2000
                interpolator = LinearInterpolator()
                start()
            }
        btn_welcome_title.setOnClickListener {
            startTransition("explode")
        }

    }
}
