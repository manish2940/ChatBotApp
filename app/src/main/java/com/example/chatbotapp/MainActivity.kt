package com.example.chatbotapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var arrayformessage : ArrayList<Module>
    lateinit var myRecyclerView: RecyclerView
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recylerview)
        val btn = findViewById<Button>(R.id.btnforsend)
        val text3 = getString(R.string.txt3)
        val tp = Module(text3,1)
        arrayformessage = ArrayList<Module>()
        arrayformessage.add(tp)


        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.adapter = MyAdapter(this,arrayformessage)
        btn.setOnClickListener {
            val textPad = findViewById<EditText>(R.id.textpad)
            val mytext = textPad.text.toString()
            val element = Module(mytext,2)
            arrayformessage.add(element)
            textPad.setText(" ")
            getResponse(mytext)
            myRecyclerView.adapter?.notifyDataSetChanged()


        }




    }
    fun getResponse(question : String){
        val urlapi = "https://api.brainshop.ai/get?bid=177443&key=rGzDzLOOIvi2lSgW&uid=[uid]&msg="+question
        val base_url = "http://api.brainshop.ai/"
        val retrofit = Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitapi = retrofit.create(MyInterface::class.java)
        val call = retrofitapi.getMessage(urlapi)
        call.enqueue(object :retrofit2.Callback<Module1>{
            override fun onResponse(
                call: retrofit2.Call<Module1>,
                response: retrofit2.Response<Module1>
            ) {
                if(response.isSuccessful()){
                    val text = response.body()
                    val element2 = Module( text!!.getcnt(),1)
                    arrayformessage.add(element2)

                }

            }

            override fun onFailure(call: retrofit2.Call<Module1>, t: Throwable) {
                Log.d("Main Activity","onFailure:"+t.message)
                val element = Module("please revert your question",1)
                arrayformessage.add(element)
            }

        })


    }

}
