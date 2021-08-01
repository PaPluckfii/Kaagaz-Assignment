package com.sumeet.kaagazcameraassignment.view.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.repository.AlbumRepository
import com.sumeet.kaagazcameraassignment.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val repository: AlbumRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(){

//    fun getAllPictures(albumName: String) : LiveData<List<PictureEntity>>{
//
//    }

}