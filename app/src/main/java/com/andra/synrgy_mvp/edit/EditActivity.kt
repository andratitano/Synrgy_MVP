package com.andra.synrgy_mvp.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.andra.synrgy_mvp.R
import com.andra.synrgy_mvp.db.DatabaseItem
import com.andra.synrgy_mvp.db.Item
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity(), EditActivityPresenter.Listener {
    private lateinit var presenter: EditActivityPresenter

    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        DatabaseItem.getInstance(this)?.let {
            presenter = EditActivityPresenter(it, this)
        }

        intent.getParcelableExtra<Item>("item")?.let {
            item = it
        }

        etNameEdit.setText(item.name)

        etQuantityEdit.setText(item.quantity.toString())

        btnEdit.setOnClickListener {
            item.apply {
                name = etNameEdit.text.toString()
                quantity = etQuantityEdit.text.toString().toInt()
            }

            presenter.editItem(item)
        }

    }

    override fun showEditSuccess() {
        runOnUiThread {
            Toast.makeText(this@EditActivity,"Data Telah Terupdate", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun showEditFailed() {
        runOnUiThread {
            Toast.makeText(this@EditActivity,"Data Gagal diupdate", Toast.LENGTH_LONG).show()
        }
    }
}