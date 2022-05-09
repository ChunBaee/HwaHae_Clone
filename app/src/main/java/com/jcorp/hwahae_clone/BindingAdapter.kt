package com.jcorp.hwahae_clone

import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.storage.FirebaseStorage
import com.jcorp.hwahae_clone.home.now.data.Options
import java.text.DecimalFormat

object BindingAdapter {

    @BindingAdapter("showImgFromStorage")
    @JvmStatic
    fun showImgFromStorage(view: ImageView, url: String?) {
        if (url != null) {
            FirebaseStorage.getInstance().reference.child(url).downloadUrl.addOnCompleteListener {
                Glide.with(view.context).load(it.result).into(view)
            }
        }
    }

    @BindingAdapter("setBackGroundFromStorage")
    @JvmStatic
    fun setBackgroundFromStorage(view: View, url: String) {
        FirebaseStorage.getInstance().reference.child(url).downloadUrl.addOnCompleteListener {
            Glide.with(view.context).load(it.result).into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    a_resource: Drawable,
                    a_transition: Transition<in Drawable>?
                ) {
                    view.background = a_resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
        }
    }

    @BindingAdapter("showImgFromDevice")
    @JvmStatic
    fun showImgFromDevice(view: ImageView, res: Int) {
        Glide.with(view.context).load(res).into(view)
    }

    @BindingAdapter("setBackGroundXML")
    @JvmStatic
    fun setBackGroundXML(view: View, res: Int) {
        view.setBackgroundResource(res)
    }

    @BindingAdapter("setTextWithStrikeThrough")
    @JvmStatic
    fun setTextWithStrikeThrough(view: TextView, res: String) {
        var mFormatter = DecimalFormat("###,###")
        view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        view.text = mFormatter.format(res.toInt())
    }

    @BindingAdapter("setTextWithFormatter")
    @JvmStatic
    fun setTextWithFormatter(view: TextView, res: String) {
        var mFormatter = DecimalFormat("###,###")
        view.text = mFormatter.format(res.toInt())
    }

    @BindingAdapter("checkOptions")
    @JvmStatic
    fun checkOptions(view: ImageView, option: String) {
        Log.d("----", "checkOptions: $option")
        if (option.contains("한정특가")) {
            /*Glide.with(view.context)
                .load(R.drawable.icon_home_now_shopping_limit_discount).into(view)*/
            view.setImageResource(R.drawable.icon_home_now_shopping_limit_discount)
            Log.d("----", "checkOptions: case 1")
        } else if (option.contains("무료배송")) {
            Glide.with(view.context)
                .load(R.drawable.icon_home_now_shopping_free_shipping_fee).into(view)
            Log.d("----", "checkOptions: case 2")
        } else if (option.contains("최저가")) {
            Glide.with(view.context)
                .load(R.drawable.icon_home_now_shopping_most_cheap).into(view)
            Log.d("----", "checkOptions: case 3")
        } else if (option.contains("증정")) {
            Glide.with(view.context)
                .load(R.drawable.icon_home_now_shopping_give_more).into(view)
            Log.d("----", "checkOptions: case 4")
        } else if (option.contains("ONLY화해")) {
            Glide.with(view.context)
                .load(R.drawable.icon_home_now_shopping_only_hwahae).into(view)
            Log.d("----", "checkOptions: case 5")
        } else {
            Log.d("----", "checkOptions: FUCK")
        }
    }
}