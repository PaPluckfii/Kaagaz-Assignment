package com.sumeet.kaagazcameraassignment.view.image.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.sumeet.kaagazcameraassignment.R
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.databinding.FragmentCurrentImageBinding

class CurrentImageFragment(
    private val pictureEntity: PictureEntity
) : Fragment() {

    private lateinit var binding : FragmentCurrentImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentImageBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(view).load(pictureEntity.uri?.toUri()).into(binding.currentImageView)
    }

companion object{
    fun newInstance(pictureEntity: PictureEntity) = CurrentImageFragment(pictureEntity)
}

}