package com.ian.app.ui.activity.sliders

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.ian.app.R
import com.ian.app.helper.inflates
import com.ian.app.helper.loadUrl
import com.ian.app.model.NewsModel
import kotlinx.android.synthetic.main.item_slider.view.*

class SliderItemAdapter(private val data: List<NewsModel>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val views = container.inflates(R.layout.item_slider)
        views.ivSliderImage.loadUrl(data[position].imageUrl)
        views.ivSliderImage?.setOnClickListener {
        }
        container.addView(views)
        return views
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = data.size
}