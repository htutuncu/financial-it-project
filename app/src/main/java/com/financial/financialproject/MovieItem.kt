package com.financial.financialproject

class MovieItem(name: String, imageUrl : String, desc : String) {

    var mName : String = "Default"
    var mImageUrl : String = "Default"
    var mDesc : String = "Default"

    init {
        mName = name
        mImageUrl = imageUrl
        mDesc = desc
    }

    fun getName() : String {
        return mName
    }
}
