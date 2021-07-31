package com.sumeet.kaagazcameraassignment.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.databinding.ActivityHomeBinding
import com.sumeet.kaagazcameraassignment.view.home.recyclerview.AlbumsAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var adapter : AlbumsAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var list: List<AlbumEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        getListForRecyclerView()
        setRecyclerView()

    }

    private fun getListForRecyclerView() {
        list = listOf(
            AlbumEntity(
                1,
                "DemoAlbum",
                "now",
                "somewhere"
            ),
            AlbumEntity(
                2,
                "DemoAlbum2",
                "later",
                "somewhere else"
            )
        )
    }

    private fun setRecyclerView() {
        adapter = AlbumsAdapter(list)
        binding.albumRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = adapter
        }
        adapter.notifyDataSetChanged()
    }
}