package com.sumeet.kaagazcameraassignment.view.image.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.view.image.ImageActivity

class ImagesViewPagerAdapter(
    imageActivity: ImageActivity,
    private val list : List<PictureEntity>
) : FragmentStateAdapter(imageActivity) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return CurrentImageFragment.newInstance(list[position])
    }
}