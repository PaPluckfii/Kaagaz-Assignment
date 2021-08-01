package com.sumeet.kaagazcameraassignment.view.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.databinding.ActivityImageBinding
import com.sumeet.kaagazcameraassignment.view.image.viewpager.ImagesViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityImageBinding
    private lateinit var viewPagerAdapter : ImagesViewPagerAdapter
    private val viewModel : ImageViewModel by viewModels()
    private lateinit var currentAlbum : AlbumEntity
    private lateinit var listOfImages : List<PictureEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentAlbum = intent.getSerializableExtra("currentAlbum") as AlbumEntity

        currentAlbum.name?.let {
            listOfImages = viewModel.getListOfImages(it)
        }

        viewPagerAdapter = ImagesViewPagerAdapter(this,listOfImages)

        binding.photoViewPager.adapter = viewPagerAdapter

    }
}