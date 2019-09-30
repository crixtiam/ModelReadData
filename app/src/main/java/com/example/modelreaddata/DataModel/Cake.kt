package com.example.modelreaddata.DataModel

import com.google.firebase.database.DataSnapshot

class Cake (snapshot: DataSnapshot){

    lateinit var id: String
    lateinit var name: String
    lateinit var dateBaked: String
     lateinit var expiryDate: String


    init {
        try {
            val data: HashMap<String,Any> = snapshot.value as HashMap<String, Any>
            id =  snapshot.key ?:""
            name  = data["name"] as String
            dateBaked = data["dateBaked"] as String
            expiryDate = data ["expiryDate"] as String

        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}