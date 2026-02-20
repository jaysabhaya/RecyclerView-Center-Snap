package com.jaysabhaya.centersnap

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaysabhaya.centersnap.horizontalcenterselected.ItemListener
import com.jaysabhaya.centersnap.customview.RecyclerViewHorizontalCustom

class MainActivity : AppCompatActivity(), ItemListener {

    var recyclerViewHorizontalCustom: RecyclerViewHorizontalCustom? = null
    var layoutManager: LinearLayoutManager? = null
    var amountList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountList = ArrayList()
        fillList()
        recyclerViewHorizontalCustom =
            findViewById<View>(R.id.rv_Custom) as RecyclerViewHorizontalCustom
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewHorizontalCustom?.layoutManager = layoutManager
        recyclerViewHorizontalCustom?.setHasFixedSize(true)
        recyclerViewHorizontalCustom?.setAmountList(amountList)
        recyclerViewHorizontalCustom?.init(
            this@MainActivity,
            this@MainActivity,
            R.layout.item_amount,
            resources.getDimension(R.dimen.width_item_amount_with_padding),
            R.drawable.rectangle_round_full_gray,
            R.drawable.rectangle_round_red
        )

        Handler().postDelayed({
            recyclerViewHorizontalCustom?.scrollToX(4)
                              }, 500)

    }

    private fun fillList() {
        for (i in 1..8) {
            amountList.add("Item $i")
        }
    }

    override fun getValue(amount: String?, pos: Int) {
        Toast.makeText(this, amount, Toast.LENGTH_SHORT).show()
    }

}