package com.pearlorganisation.figgo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.pearlorganisation.figgo.Model.SliderData
import com.pearlorganisation.figgo.R

class SliderAdapter(val context: Context, val sliderList: ArrayList<SliderData>) : PagerAdapter() {

    override fun getCount(): Int {

        return sliderList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater



        val view: View = layoutInflater.inflate(R.layout.slider_item, container, false)

        // on below line we are initializing our image view,
        // heading text view and description text view with their ids.
        val imageView: ImageView = view.findViewById(R.id.idIVSlider)
        val sliderHeadingTV: TextView = view.findViewById(R.id.idTVSliderTitle)
        val sliderDescTV: TextView = view.findViewById(R.id.idTVSliderDescription)

        // on below line we are setting data to our text view
        // and image view on below line.
        val sliderData: SliderData = sliderList.get(position)
        sliderHeadingTV.text = sliderData.slideTitle
        sliderDescTV.text = sliderData.slideDescription
        imageView.setImageResource(sliderData.slideImage)

        // on below line we are adding our view to container.
        container.addView(view)

        // on below line we are returning our view.
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // this is a destroy view method
        // which is use to remove a view.
        // this is a destroy view method
        // which is use to remove a view.
        container.removeView(`object` as RelativeLayout)
    }

}