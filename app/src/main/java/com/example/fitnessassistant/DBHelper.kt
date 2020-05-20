package com.example.fitnessassistant

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context,name:String = database,factory: SQLiteDatabase.CursorFactory?=null,
               version:Int = v):SQLiteOpenHelper(context,name,factory,version) {

    companion object{
        private const val database = "mdatabase.db"    //資料庫名稱
        private const val v = 1                        //資料庫版本
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table chestTable(moveName text PRIMARY KEY,muscleName text,setNum integer,frequency integer,weight integer,lastSet integer)")
        db?.execSQL("create table backTable(moveName text PRIMARY KEY,muscleName text,setNum integer,frequency integer,weight integer,lastSet integer)")
        db?.execSQL("create table shoulderTable(moveName text PRIMARY KEY,muscleName text,setNum integer,frequency integer,weight integer,lastSet integer)")
        db?.execSQL("create table tricepsTable(moveName text PRIMARY KEY,muscleName text,setNum integer,frequency integer,weight integer,lastSet integer)")
        db?.execSQL("create table bicepsTable(moveName text PRIMARY KEY,muscleName text,setNum integer,frequency integer,weight integer,lastSet integer)")
        db?.execSQL("create table legsTable(moveName text PRIMARY KEY,muscleName text,setNum integer,frequency integer,weight integer,lastSet integer)")
        db?.execSQL("create table coreTable(moveName text PRIMARY KEY,muscleName text,setNum integer,frequency integer,weight integer,lastSet integer)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS chestTable")
        onCreate(db)
    }
}