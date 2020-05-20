package com.example.fitnessassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_example.view.*

class MovementAdapter:RecyclerView.Adapter<MovementAdapter.mViewHolder>(){

     companion object{
         var unAssignList = arrayListOf<Movement>()

         private var clickListener : ItemClickListener? = null
         fun setToClick(listener: ItemClickListener){
             clickListener = listener
         }
     }

    interface ItemClickListener{
        fun toClick(item: Movement)
    }

    inner class mViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        //把layout元件指派給當地變數
        val moveName = itemView.tv_item_title
        val muscleName = itemView.tv_item_title2
        val itemLayout = itemView.item_layout
//        val setNum = itemView.
//        val frequency =
//        val weight =
//        val lastSet =

        //把unAssignList的項目指派給當地變數
        fun bind(item:Movement){
            moveName.text = item.moveName
            muscleName.text = item.muscleName
//            setNum.text = item.setNum
//            frequency.text = item.frequency
//            weight.text = item.weight
//            lastSet.text = item.lastSet

            itemLayout.setOnClickListener {
                clickListener?.toClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val example = inflater.inflate(R.layout.rv_example, parent, false)
        return  mViewHolder(example)
    }

    override fun getItemCount() = unAssignList.size

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bind(unAssignList[position])

    }
}