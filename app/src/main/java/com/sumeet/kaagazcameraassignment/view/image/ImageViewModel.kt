package com.sumeet.kaagazcameraassignment.view.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.repository.AlbumRepository
import com.sumeet.kaagazcameraassignment.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: AlbumRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(){

    fun getListOfImages(albumName : String) : List<PictureEntity>{
        val list = arrayListOf<PictureEntity>()
        viewModelScope.launch(dispatcherProvider.io) {
            repository.getListOfPictures(albumName)
        }
        return list
    }

}