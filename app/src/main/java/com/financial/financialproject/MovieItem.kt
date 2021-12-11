package com.financial.financialproject

class MovieItem(name: String, imageUrl : String, desc : String) {

    var mName : String = ""
    var mImageUrl : String = ""
    var mDesc : String = ""

    init {
        mName = name
        mImageUrl = imageUrl
        mDesc = desc
    }

    fun getName() : String {
        return mName
    }
}
