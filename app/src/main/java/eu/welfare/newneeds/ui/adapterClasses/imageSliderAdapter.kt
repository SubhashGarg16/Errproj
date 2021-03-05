package eu.welfare.newneeds.ui.adapterClasses
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import eu.welfare.newneeds.R

public class imageSliderAdapter(var list:List<Int>, var ctx: Context) : PagerAdapter() {
    lateinit var layoutInflater: LayoutInflater
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(ctx)
        var view = layoutInflater.inflate(R.layout.slider, container, false)
        val img = view.findViewById<ImageView>(R.id.sliderImg)
        img.setImageResource(list.get(position))
        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}