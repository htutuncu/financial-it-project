package com.example.financialproject

class MovieItem(name: String, imageUrl : String) {

    var mName : String = ""
    var mImageUrl : String = ""

    init {
        mName = name
        mImageUrl = imageUrl
    }

    fun getName() : String {
        return mName
    }



}