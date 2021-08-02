package com.sumeet.kaagazcameraassignment.view.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.databinding.ActivityImageBinding
import com.sumeet.kaagazcameraassignment.view.image.viewpager.ImagesViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.nio.file.Files

@AndroidEntryPoint
class ImageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityImageBinding
    private lateinit var viewPagerAdapter : ImagesViewPagerAdapter
    private val viewModel : ImageViewModel by viewModels()
    private lateinit var currentAlbum : AlbumEntity
    private var listOfImages = arrayListOf<PictureEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentAlbum = intent.getSerializableExtra("currentAlbum") as AlbumEntity

        viewPagerAdapter = ImagesViewPagerAdapter(this,listOfImages)

        binding.photoViewPager.adapter = viewPagerAdapter

        currentAlbum.name?.let {
            viewModel.getListOfImages(it).observe(this, Observer {list ->
                if(list != null){
                    listOfImages.clear()
                    listOfImages.addAll(list)
                    listOfImages.reverse()
                    viewPagerAdapter.notifyDataSetChanged()
                }
            })
        }

        handleClicks()

    }

    private fun handleClicks() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnDelete.setOnClickListener {
            val currentImageEntity = listOfImages[binding.photoViewPager.currentItem]
            viewModel.deletePicture(currentImageEntity)

            listOfImages.remove(currentImageEntity)
            viewPagerAdapter.notifyDataSetChanged()
        }
    }
}