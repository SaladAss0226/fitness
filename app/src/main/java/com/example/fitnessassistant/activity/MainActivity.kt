package com.example.fitnessassistant.activity

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.fitnessassistant.DBHelper
import com.example.fitnessassistant.R
import com.example.fitnessassistant.fragment.*
import com.example.fitnessassistant.fragment.ChestFragment.Companion.database
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        //這行一定要放在setcontentView前面!!!!!!!!!!!!!!!用來控制過場動畫
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupTrainsition()

        val adapter = VPagerAdapter(
            supportFragmentManager,
            7
        )
        val tablayout = t_layout
        val viewPager = view_pager
        viewPager.adapter = adapter
        tablayout.setupWithViewPager(viewPager)




    }


    private fun setupTrainsition() {
        //找到intent送過來的key是哪一個
        when(intent.getStringExtra("flag")){
            "explode" -> {
                val explodeTransition = Explode()
                explodeTransition.duration = 1000
                window.enterTransition = explodeTransition
                window.exitTransition = explodeTransition
            }
            "slide" -> {
                val slideTransition = Slide()
                slideTransition.duration = 1000
                window.enterTransition = slideTransition
                window.exitTransition = slideTransition
            }
            "fade" -> {
                val fadeTransition = Fade()
                fadeTransition.duration = 1000
                window.enterTransition = fadeTransition
                window.exitTransition = fadeTransition

            }
        }
    }
}





//建立一個類別VPA 繼承FragmentPagerAdapter
class VPagerAdapter(f: FragmentManager, bh:Int) : FragmentPagerAdapter(f,bh){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ChestFragment()   //第一頁要呈現的fragment(kt檔)
            1 -> BackFragment()   //第二頁要呈現的fragment(kt檔)
            2 -> ShoulderFragment()    //第三頁頁要呈現的fragment(kt檔)
            3 -> TricepsFragment()
            4 -> BicepsFragment()
            5 -> LegsFragment()
            else -> CoreFragment()
        }
    }
    override fun getPageTitle(position: Int): CharSequence {
        when(position){
            0 -> return "胸"
            1 -> return "背"
            2 -> return "肩膀"
            3 -> return "三頭"
            4 -> return "二頭"
            5 -> return "臀腿"
            else -> return "核心"

        }
    }
    override fun getCount(): Int = 7 //直接回傳fragment頁數

}
