package com.example.modelreaddata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.modelreaddata.Adapters.CakeCardAdapter
import com.example.modelreaddata.DataModel.Cake
import com.example.modelreaddata.DataModel.CakeModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),Observer {

    private var mCakeListAdapter: CakeCardAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CakeModel.addObserver(this)
        val dataList: ListView = findViewById(R.id.cake_list)
        val  data :ArrayList<Cake> = ArrayList()

        mCakeListAdapter =  CakeCardAdapter(this,R.layout.cake_card_item,data)
        dataList.adapter = mCakeListAdapter
    }

    override fun update(p0: Observable?, p1: Any?) {
        mCakeListAdapter?.clear()
        val data = CakeModel.getData()
        if(data!=null){
            mCakeListAdapter?.clear()
            mCakeListAdapter?.addAll(data)
            mCakeListAdapter?.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        CakeModel.addObserver(this)
    }

    override fun onPause() {
        super.onPause()
        CakeModel.addObserver(this)

    }
    override fun onStop() {
        super.onStop()
        CakeModel.addObserver(this)
    }
}
