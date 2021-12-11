package com.financial.financialproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.Serializable

class DBHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION), Serializable {

    companion object{
        const val DATABASE_NAME : String = "Login.db"
        const val DATABASE_VERSION : Int = 1
    }


    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create Table users(username Text primary key,password Text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop Table if exists users")
    }

    fun insertData(username:String, password:String):Boolean {
        val myDB : SQLiteDatabase = this.writableDatabase
        val contentValues : ContentValues = ContentValues()
        contentValues.put("username",username)
        contentValues.put("password",password)
        val result : Int = myDB.insert("users",null,contentValues).toInt()
        return result != -1
    }

    fun checkUserName(username: String) : Boolean {
        val myDB : SQLiteDatabase = this.writableDatabase
        val cursor : Cursor = myDB.rawQuery("select * from users where username = ?", arrayOf(username))
        return cursor.count>0
    }

    fun checkUserNamePassword(username: String,password : String) : Boolean {
        val myDB : SQLiteDatabase = this.writableDatabase
        val cursor : Cursor = myDB.rawQuery("select * from users where username = ? and password = ?", arrayOf(username,password))
        return cursor.count>0
    }

}