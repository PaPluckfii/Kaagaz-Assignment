package com.sumeet.kaagazcameraassignment.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.databinding.ActivityHomeBinding
import com.sumeet.kaagazcameraassignment.view.album.AlbumActivity
import com.sumeet.kaagazcameraassignment.view.camera.CameraActivity
import com.sumeet.kaagazcameraassignment.view.home.recyclerview.AlbumItemClickListener
import com.sumeet.kaagazcameraassignment.view.home.recyclerview.AlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() , NewAlbumDialog.AlbumDialogListener , AlbumItemClickListener {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var albumsAdapter : AlbumsAdapter
    private var albumList = arrayListOf<AlbumEntity>()
    private val viewModel : HomeViewModel by viewModels()
    private var thumbnailList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateNewAlbum.setOnClickListener {
            createNewAlbumDialog()
        }

        setRecyclerView()

        viewModel.getAllAlbums().observe(this, Observer {
            if(it != null) {
                resetRecyclerView(it)
            }
        })

    }

    private fun createNewAlbumDialog() {
        val newAlbumDialog = NewAlbumDialog()
        newAlbumDialog.show(supportFragmentManager, "newAlbumDialog")
    }

    private fun setRecyclerView() {
        albumsAdapter = AlbumsAdapter(albumList,this)
        binding.albumRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = albumsAdapter
        }
        albumsAdapter.notifyDataSetChanged()
    }

    private fun resetRecyclerView(list: List<AlbumEntity>) {
        if(list.isEmpty()){
            binding.llNoAlbums.visibility = View.VISIBLE
        }else{
            binding.llNoAlbums.visibility = View.GONE
            albumList.clear()
            albumList.addAll(list)

            thumbnailList.clear()

//            for(i in albumList){
//                thumbnailList.add(viewModel.getThumbnail(i))
//            }

            albumsAdapter.notifyDataSetChanged()
        }
    }

    override fun getAlbumNameFromEditText(name: String) {
        val intent = Intent(this@HomeActivity, CameraActivity::class.java)

        val entity = AlbumEntity(
            name = name,
            timestamp = System.currentTimeMillis()
        )

        viewModel.insertNewAlbum(entity)

        intent.putExtra(
            "currentAlbum",
            entity
        )

        startActivity(intent)
    }

    override fun onItemClicked(albumEntity: AlbumEntity) {
        val intent = Intent(this@HomeActivity , AlbumActivity::class.java)
        intent.putExtra("currentAlbum",albumEntity)
        startActivity(intent)
    }

}