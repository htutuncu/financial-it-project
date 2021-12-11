package com.financial.financialproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.financial.financialproject.databinding.ActivityMoviesBinding
import org.json.JSONObject

class MoviesActivity : AppCompatActivity() {

    lateinit var binding :ActivityMoviesBinding
    lateinit var mRequestQueue : RequestQueue
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: MyAdapter
    val list : MutableList<MovieItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mRequestQueue = Volley.newRequestQueue(this)
        mRecyclerView = binding.recyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)


        fetchData()
    }

    fun fetchData() {

        val url = getString(R.string.key)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    val jsonArray = response.getJSONArray("results")
                    for ( i in 0 until jsonArray.length()) {
                        var obje : JSONObject = jsonArray.getJSONObject(i)

                        var title = obje.getString("title")
                        list.add(MovieItem(obje.getString("title"),obje.getString("poster_path"),obje.getString("overview")))
                    }

                    mAdapter = MyAdapter(this,list)
                    mRecyclerView.adapter = mAdapter
                },
                { error ->
                    error.printStackTrace()
                }
        )

        mRequestQueue.add(jsonObjectRequest)
    }
}