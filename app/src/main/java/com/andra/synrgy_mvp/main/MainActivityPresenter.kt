package com.andra.synrgy_mvp.main

import com.andra.synrgy_mvp.db.DatabaseItem
import com.andra.synrgy_mvp.db.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityPresenter(val db: DatabaseItem, val listener: Listener) {

    fun fetchData(){
        GlobalScope.launch {
            val listItem = db.itemDao().getAllItem()
            listener.showStudentList(listItem)
        }
    }

    fun goToAddActivity(){
        listener.goToAddActivity()
    }

    fun goToEditActivity(item: Item){
        listener.goToEditActivity(item)
    }

    fun deleteItem(item: Item){
        GlobalScope.launch {
            val rowDeleted = db.itemDao().deleteItem(item)
            if (rowDeleted > 0){
                listener.showDeletedSuccess(item)
            } else {
                listener.showDeletedFailed(item)
            }
        }
    }

    interface Listener{
        fun showStudentList(listItem: List<Item>)
        fun goToAddActivity()
        fun goToEditActivity(item: Item)
        fun showDeletedSuccess(item: Item)
        fun showDeletedFailed(item: Item)
    }
}