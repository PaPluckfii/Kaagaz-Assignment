package com.sumeet.kaagazcameraassignment.view.album

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.databinding.ActivityAlbumBinding
import com.sumeet.kaagazcameraassignment.view.album.recyclerview.PictureItemClickListener
import com.sumeet.kaagazcameraassignment.view.album.recyclerview.PicturesAdapter
import com.sumeet.kaagazcameraassignment.view.camera.CameraActivity
import com.sumeet.kaagazcameraassignment.view.image.ImageActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumActivity : AppCompatActivity() , PictureItemClickListener{

    private lateinit var binding : ActivityAlbumBinding
    private val viewModel : AlbumViewModel by viewModels()
    private lateinit var picturesAdapter: PicturesAdapter
    private var pictureList = arrayListOf<PictureEntity>()
    private lateinit var currentAlbum : AlbumEntity

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentAlbum = intent.getSerializableExtra("currentAlbum") as AlbumEntity

        setRecyclerView()

        currentAlbum.name?.let {
            viewModel.getAllPictures(it).observe(this, Observer { list ->
                if(list != null)
                    resetRecyclerView(list)
            })
        }

        handleClicks()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleClicks() {
        binding.btnAddPicsAlbum.setOnClickListener {
            val intent = Intent(this@AlbumActivity, CameraActivity::class.java)
            intent.putExtra("currentAlbum",currentAlbum)
            startActivity(intent)
        }
        binding.btnDeleteAlbum.setOnClickListener{
            viewModel.deleteAlbum(currentAlbum)
            finish()
        }
    }

    private fun setRecyclerView() {
        picturesAdapter = PicturesAdapter(pictureList,this)
        binding.picturesRecyclerView.apply {
            layoutManager = GridLayoutManager(this@AlbumActivity,2)
            adapter = picturesAdapter
        }
        picturesAdapter.notifyDataSetChanged()
    }

    private fun resetRecyclerView(list: List<PictureEntity>) {
        if(list.isEmpty()){
            binding.llNoAlbums.visibility = View.VISIBLE
        }else {
            binding.llNoAlbums.visibility = View.GONE
            pictureList.clear()
            pictureList.addAll(list)
            picturesAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClicked() {
        val intent = Intent(this@AlbumActivity, ImageActivity::class.java)
        intent.putExtra("currentAlbum", currentAlbum)
        startActivity(intent)
    }
}