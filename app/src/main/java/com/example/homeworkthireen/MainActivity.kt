package com.example.homeworkthireen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkthireen.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userList: MutableList<Field>
    private lateinit var adapter: FieldRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gson = Gson()
        val jsonString = readJSONFromAsset(this, "data.json")
        userList = gson.fromJson(jsonString, Array<Field>::class.java).toMutableList()
        userList.add(Field(200, "reguster", "button", null, true, true, "va", "g"))
        setUp()
        adapter.apply {
            var lst = itemOnClick()
        }



    }

//    private fun listeners(){
//
//        for (i in 0 until adapter.itemCount) {
//            val itemData = adapter.getItem(i)
//            d(itemData.hint, i.to)
//
//        }
//    }

    private fun setUp() {

        setUpRowRecycler()
        adapter.setData(userList)
    }



    private fun setUpRowRecycler() {
        adapter = FieldRecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }


    fun readJSONFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}