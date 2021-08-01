package com.sumeet.kaagazcameraassignment.view.home

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumeet.kaagazcameraassignment.R
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.databinding.ActivityHomeBinding
import com.sumeet.kaagazcameraassignment.view.camera.CameraActivity
import com.sumeet.kaagazcameraassignment.view.home.recyclerview.AlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() , NewAlbumDialog.AlbumDialogListener {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var adapter : AlbumsAdapter
    private val viewModel : HomeViewModel by viewModels()
    private lateinit var listOfAlbums : List<AlbumEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getListForRecyclerView()
        setRecyclerView()
        binding.btnCreateNewAlbum.setOnClickListener {
            createNewAlbumDialog()
        }

    }

    private fun createNewAlbumDialog() {
        val newAlbumDialog = NewAlbumDialog()
        newAlbumDialog.show(supportFragmentManager, "newAlbumDialog")
    }

    private fun getListForRecyclerView() {
        viewModel.getAllAlbums().observe(this, Observer {
            listOfAlbums = it
            adapter.notifyDataSetChanged()
        })
    }

    private fun setRecyclerView() {
        adapter = AlbumsAdapter(listOfAlbums)
        binding.albumRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = adapter
        }
        adapter.notifyDataSetChanged()
    }

    override fun getAlbumNameFromEditText(name: String) {
        val intent = Intent(this@HomeActivity, CameraActivity::class.java)

        val entity = AlbumEntity(
            name = name,
            timestamp = "for now"
        )
        viewModel.insertNewAlbum(entity)

        intent.putExtra(
            "currentAlbum",
            entity
        )

        startActivity(intent)
    }
}