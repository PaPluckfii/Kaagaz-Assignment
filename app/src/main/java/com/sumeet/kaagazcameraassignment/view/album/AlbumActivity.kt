package com.sumeet.kaagazcameraassignment.view.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.databinding.ActivityAlbumBinding
import com.sumeet.kaagazcameraassignment.view.album.recyclerview.PictureItemClickListener
import com.sumeet.kaagazcameraassignment.view.album.recyclerview.PicturesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumActivity : AppCompatActivity() , PictureItemClickListener{

    private lateinit var binding : ActivityAlbumBinding
    private val viewModel : AlbumViewModel by viewModels()
    private lateinit var picturesAdapter: PicturesAdapter
    private var pictureList = arrayListOf<PictureEntity>()
    private lateinit var currentAlbum : AlbumEntity

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
        pictureList.clear()
        pictureList.addAll(list)
        picturesAdapter.notifyDataSetChanged()
    }

    override fun onItemClicked(pictureEntity: PictureEntity) {
        TODO("Not yet implemented")
    }
}