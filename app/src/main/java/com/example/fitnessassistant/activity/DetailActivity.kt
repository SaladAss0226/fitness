package com.example.fitnessassistant.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fitnessassistant.DialogAddMoves
import com.example.fitnessassistant.R
import com.example.fitnessassistant.fragment.ChestFragment.Companion.editKey
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object{
        var moveNameIndex = ""
        var currentFragment = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        editKey = true

        intent!!.extras!!.let{
            val data = it.getStringArrayList("keyData")
            moveNameIndex = data!![0]
            tv_move_name.text = moveNameIndex
            tv_muscle_name.text = "目標肌群:\t${data!![1]}"
            tv_set_num.text = "組數:\t${data!![2]}"
            tv_frequency.text = "次數:\t${data!![3]}"
            tv_weight.text = "重量:\t${data!![4]}"
            tv_last_set.text = "最後一組直到RPE:\t${data!![5]}"

            currentFragment = it.getInt("currentFragment")

        }

        btn_edit_move.setOnClickListener {
            supportFragmentManager.let {
                val dialog = DialogAddMoves()
                dialog.show(it!!, "")
            }
        }
    }
}
