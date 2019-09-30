package com.example.modelreaddata.DataModel

import android.util.Log
import com.google.firebase.database.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

object CakeModel:Observable() {
    private var mValueDataListener : ValueEventListener? = null
    private var mCakeList : ArrayList<Cake>?= ArrayList()

    private fun getDataBaseRef():DatabaseReference?{
        return FirebaseDatabase.getInstance().reference.child("cake")
    }
    init {
        if (mValueDataListener!=null){
            getDataBaseRef()?.removeEventListener(mValueDataListener!!)
        }
        mValueDataListener = null
        Log.i("CakeModel","Data init 26")
        mValueDataListener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

                if (p0!=null){
                    Log.i("CakeModel","line  sl data update canceled, err = ${p0.message}")

                }

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    Log.i("CakeModel","data  update line 29")
                    val data : ArrayList<Cake> = ArrayList()
                    if (dataSnapshot!=null){

                        for (snapshot:DataSnapshot in dataSnapshot.children){

                            try {
                                data.add(Cake(snapshot))
                            }catch (e:Exception){
                                e.printStackTrace()
                            }
                        }
                        mCakeList  = data
                        Log.i("CakeModel","dataUpdataed , there are " + mCakeList!!.size+ "Entrees in the cache")
                        setChanged()
                        notifyObservers()
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }


        }

        getDataBaseRef()?.addValueEventListener(mValueDataListener as ValueEventListener)
    }

    fun getData(): ArrayList<Cake>? {
        return mCakeList
    }
}