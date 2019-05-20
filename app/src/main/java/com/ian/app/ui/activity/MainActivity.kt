package com.ian.app.ui.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.R
import com.ian.app.helper.Constant.delayMillis
import com.ian.app.helper.fullScreenAnimation
import com.ian.app.helper.gone
import com.ian.app.helper.loadUrl
import com.ian.app.helper.setUp
import com.ian.app.model.GeneralData
import com.ian.app.ui.activity.sliders.SliderItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainActivity : AppCompatActivity(), MainView {
    private var mHandler: Handler? = null
    private var pageSize: Int? = 0
    private var currentPage = 0
    private lateinit var presenter: MainPresenter
    private var slideRunnable: Runnable = object : Runnable {
        override fun run() {
            if (currentPage == pageSize) {
                currentPage = 0
            }
            vpHeadlineNews.setCurrentItem(currentPage++, true)
            mHandler?.postDelayed(this, delayMillis)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_main)
        presenter = MainPresenter().apply {
            attachView(this@MainActivity, this@MainActivity)
            onCreate()
        }
        mHandler = Handler()


    }

    override fun onSuccessGetHeadlineData(data: GeneralData?) {
        data?.data?.news?.let { berita ->
            pageSize = berita.size
            vpHeadlineNews?.adapter = SliderItemAdapter(berita)
            indicator?.setViewPager(vpHeadlineNews)
            if (mHandler != null) {
                mHandler?.removeCallbacks(slideRunnable)
            }
            mHandler?.postDelayed(slideRunnable, delayMillis)
        }
    }

    override fun onSuccessGetData(data: GeneralData?) {
        data?.data?.news?.let { berita ->
            shimmer_home?.stopShimmer()
            shimmer_home?.gone()
            rvNews.setUp(berita, R.layout.item_main, {
                with(this) {
                    ivMainNews.loadUrl(it.imageUrl)
                    tvMainTittle.text = it.title
                }
            })
        }

    }


    override fun initView() {
    }

    override fun onPause() {
        super.onPause()
        shimmer_home?.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        shimmer_home?.startShimmer()
    }

    override fun onStart() {
        super.onStart()
        mHandler?.postDelayed(slideRunnable, delayMillis)
    }

    override fun onStop() {
        super.onStop()
        mHandler?.removeCallbacks(slideRunnable)
    }
}
