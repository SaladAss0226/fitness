package com.example.fitnessassistant
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.fitnessassistant.activity.DetailActivity
import com.example.fitnessassistant.activity.DetailActivity.Companion.currentFragment
import com.example.fitnessassistant.activity.DetailActivity.Companion.moveNameIndex
import com.example.fitnessassistant.fragment.ChestFragment.Companion.adapter
import com.example.fitnessassistant.fragment.ChestFragment.Companion.database
import com.example.fitnessassistant.fragment.ChestFragment.Companion.editKey
import kotlinx.android.synthetic.main.dialog_add_move.*
import java.lang.Exception

class DialogAddMoves : DialogFragment() {

    var table = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar_Fullscreen)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.dialog_add_move, container, false)

    }

    //
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




        btn_accept.setOnClickListener {
            val moveName = et_move_name.text.toString()
            val muscleName = et_muscle_name.text.toString()
            val setNum = et_set_num.text.toString().toInt()
            val frequency = et_frequency.text.toString().toInt()
            val weight = et_weight.text.toString().toInt()
            val lastSet = et_last_set.text.toString().toInt()


            //判斷目前是位於哪個fragment，才知道等等編輯資料時要找資料庫的哪一張資料表
            when(currentFragment){
                1 -> table = "chestTable"
                2  -> table = "backTable"
                3 -> table = "shoulderTable"
                4 -> table = "tricepsTable"
                5 -> table = "bicepsTable"
                6 ->table = "legsTable"
                7 -> table = "coreTable"
            }

            if(editKey){
                //到資料庫編輯特定動作
                //修改資料
                try{
                    //根據動作名稱欄位來更新其他欄位的值
                    database.execSQL("UPDATE '${table}' SET moveName='${moveName}',muscleName ='${muscleName}',setNum='${setNum}',frequency='${frequency}',weight='${weight}',lastSet='${lastSet}' WHERE moveName LIKE '${moveNameIndex}'")
                    Toast.makeText(context,"更新成功",Toast.LENGTH_SHORT).show()
                    adapter.notifyDataSetChanged()
                    dismiss()
                }catch (e:Exception){
                    Toast.makeText(context,"更新失敗",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                //新增動作加入資料庫
                try{
                    database.execSQL("Insert into chestTable(moveName,muscleName,setNum,frequency ,weight ,lastSet) values(?,?,?,?,?,?)",
                        arrayOf<Any>(moveName,muscleName,setNum,frequency,weight,lastSet))
                }catch (e:Exception){
                    Toast.makeText(context,"新增失敗",Toast.LENGTH_SHORT).show()
                }

                //把剛剛新增的項目加入list
                chestMoveList.add(
                    Movement(
                        moveName,
                        muscleName,
                        setNum,
                        frequency,
                        weight,
                        lastSet
                    )
                )
                adapter.notifyDataSetChanged()
                dismiss()
            }

        }



    }

}