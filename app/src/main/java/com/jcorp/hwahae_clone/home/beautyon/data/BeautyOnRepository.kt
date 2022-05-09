package com.jcorp.hwahae_clone.home.beautyon.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class BeautyOnRepository {

    fun setHomeBeautyOnBannerItem(list : MutableLiveData<MutableList<HomeBeautyOnBannerItem>>) {
        var mList = mutableListOf<HomeBeautyOnBannerItem>()
        FirebaseFirestore.getInstance().collection("Banner_BeautyOn")
            .get().addOnCompleteListener {
                for(i in it.result) {
                    mList.add(i.toObject(HomeBeautyOnBannerItem::class.java))
                }
                list.value = mList
            }
    }

    fun setHomeBeautyOnContentItem(list : MutableLiveData<MutableList<HomeBeautyOnContentItem>>) {
        var mList = mutableListOf<HomeBeautyOnContentItem>()
        FirebaseFirestore.getInstance().collection("Content_BeautyOn")
            .get().addOnCompleteListener {
                for(i in it.result) {
                    mList.add(i.toObject(HomeBeautyOnContentItem::class.java))
                }
                list.value = mList
            }
    }
}