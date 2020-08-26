package com.andra.synrgy_mvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andra.synrgy_mvp.db.Item
import com.andra.synrgy_mvp.main.MainActivityPresenter
import kotlinx.android.synthetic.main.stuff_item.view.*

class ItemAdapter(val listItem: List<Item>, val presenter: MainActivityPresenter): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stuff_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvName.text = listItem[position].name
        holder.itemView.tvQuantity.text = listItem[position].quantity.toString()

        holder.itemView.btnHapus.setOnClickListener{
            presenter.deleteItem(listItem[position])
        }

        holder.itemView.btnEdit.setOnClickListener{
            presenter.goToEditActivity(listItem[position])
        }
    }


}