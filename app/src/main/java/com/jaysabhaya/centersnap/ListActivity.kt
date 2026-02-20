package com.jaysabhaya.centersnap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jaysabhaya.centersnap.customview.DiscreteScrollView
import com.jaysabhaya.centersnap.customview.transform.ScaleTransformer
import com.jaysabhaya.centersnap.horizontalcenterselected.AdapterAmount

class ListActivity : AppCompatActivity(),
    DiscreteScrollView.ScrollStateChangeListener<AdapterAmount.viewHolder>,
    DiscreteScrollView.OnItemChangedListener<AdapterAmount.viewHolder> {


    private var cityPicker: DiscreteScrollView? = null
    var amountList: ArrayList<String> = ArrayList()


    private fun fillList() {
        for (i in 1..8) {
            amountList.add("Item $i")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        cityPicker = findViewById(R.id.forecast_city_picker)
        cityPicker?.setSlideOnFling(true)
        cityPicker?.setAdapter(AdapterAmount(this,amountList))
        cityPicker?.addOnItemChangedListener(this)
        cityPicker?.addScrollStateChangeListener(this)
        cityPicker?.scrollToPosition(1)
        cityPicker?.setItemTransitionTimeMillis(10)
        cityPicker?.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build()
        )
        fillList()
    }

    override fun onScrollStart(currentItemHolder: AdapterAmount.viewHolder, adapterPosition: Int) {

    }

    override fun onScrollEnd(currentItemHolder: AdapterAmount.viewHolder, adapterPosition: Int) {
    }

    override fun onScroll(
        scrollPosition: Float,
        currentPosition: Int,
        newPosition: Int,
        currentHolder: AdapterAmount.viewHolder?,
        newCurrent: AdapterAmount.viewHolder?
    ) {
        val current: String = amountList.get(currentPosition)
        val adapter = cityPicker?.adapter
        val itemCount = adapter?.itemCount ?: 0
        if (newPosition >= 0 && newPosition < itemCount) {
            val next: String = amountList.get(newPosition)
//            forecastView.onScroll(1f - Math.abs(scrollPosition), current, next)
        }
    }

    override fun onCurrentItemChanged(holder: AdapterAmount.viewHolder?, position: Int) {

        //viewHolder will never be null, beca
        // use we never remove items from adapter's list
        if (holder != null) {
//            forecastView.setForecast(forecasts.get(position))
//            holder.showText()
        }
    }
}