package com.example.music_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var musicAdapter: MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        //
        val retrofitData = retrofitBuilder.getData("eminem")

        //
        retrofitData.enqueue(object : Callback<MusicData?> {
            override fun onResponse(call: Call<MusicData?>, response: Response<MusicData?>) {
                // if the api call is success then this method is executed
                val dataList = response.body()?.data!!
//                welcome.text = dataList.toString()

                musicAdapter = MusicAdapter(this@MainActivity, dataList)
                recyclerView.adapter = musicAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

//                // on item clicking
//                musicAdapter.setOnItemClickListener(object : MusicAdapter.onItemClickLister{
//                    override fun onItemClicking(position: Int) {
//                        val intentMusic = Intent(this@MainActivity, MusicActivity::class.java)
//                        intentMusic.putExtra("image", dataList[position].album.cover)
//                        intentMusic.putExtra("music", dataList[position].preview)
//                        startActivity(intentMusic)
//                    }
//
//                })


                // show log on log cat msg
                Log.d("Tag : onResponse", "onResponse : " + response.body())
            }

            override fun onFailure(call: Call<MusicData?>, t: Throwable) {
                // if the api call is success then this method is executed
                Log.d("TAG : onFailure", "onFailure : " + t.message)
            }
        })


    }
}