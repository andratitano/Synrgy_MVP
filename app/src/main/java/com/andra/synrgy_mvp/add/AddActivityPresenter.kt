package com.andra.synrgy_mvp.add

import com.andra.synrgy_mvp.db.DatabaseItem
import com.andra.synrgy_mvp.db.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivityPresenter (val db: DatabaseItem, val listener: Listener){

    fun saveItem(item: Item){
        GlobalScope.launch {
            val totalSaved = db.itemDao().addItem(item)

            if (totalSaved > 0) {
                listener.showSaveSuccess()
            } else {
                listener.showSaveFailed()
            }
        }
    }

    interface Listener{
        fun showSaveSuccess()
        fun showSaveFailed()
    }
}