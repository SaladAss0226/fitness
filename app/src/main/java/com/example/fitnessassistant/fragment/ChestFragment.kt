package com.example.fitnessassistant.fragment


import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessassistant.*
import com.example.fitnessassistant.MovementAdapter.Companion.unAssignList
import com.example.fitnessassistant.activity.DetailActivity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.dialog_add_move.*
import kotlinx.android.synthetic.main.fragment_chest.*
import kotlinx.android.synthetic.main.fragment_chest.view.*

/**
 * A simple [Fragment] subclass.
 */
class ChestFragment : Fragment() {

    companion object{
        val adapter = MovementAdapter()
        lateinit var database: SQLiteDatabase
        var editKey = false

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_chest, container, false)   //這段inflate完後，btn_add_move才會被賦值!!!!!!!!!!

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        editKey = false
        database = DBHelper(context!!).writableDatabase


        btn_add_move.setOnClickListener {
            activity?.supportFragmentManager.let {
                val dialog = DialogAddMoves()
                dialog.show(it!!, "")
            }
        }

        unAssignList = chestMoveList
        rv_movement.layoutManager = LinearLayoutManager(context)
        rv_movement.adapter = adapter

        MovementAdapter.setToClick(object :MovementAdapter.ItemClickListener{
            override fun toClick(item: Movement) {
                //把點擊項目的資訊用bundle包過去
                val b= Bundle()
                //標記目前所在fragment並用bundle傳過去
                b.putInt("currentFragment",1)
                b.putStringArrayList("keyData", arrayListOf(item.moveName,item.muscleName,item.setNum.toString(),item.frequency.toString(),item.weight.toString(),item.lastSet.toString()))
                startActivity(Intent(context,DetailActivity::class.java).putExtras(b))
            }
        })
    }

    //把資料庫資料裝進list list有了項目才能讓recyclerview顯示
    override fun onResume() {
        super.onResume()
        chestMoveList.clear()
        val c = database.rawQuery("SELECT * FROM chestTable",null)
        c.moveToFirst()
        //有幾筆資料就跑幾次
        for(i in 0 until c.count){
            chestMoveList.add(
                Movement(
                    c.getString(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getInt(3),
                    c.getInt(4),
                    c.getInt(5)
                )
            )
            c.moveToNext()
        }
    }


}
