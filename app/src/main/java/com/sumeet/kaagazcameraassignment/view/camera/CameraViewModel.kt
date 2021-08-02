package com.sumeet.kaagazcameraassignment.view.camera

import androidx.camera.core.ImageCapture
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.repository.AlbumRepository
import com.sumeet.kaagazcameraassignment.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val repository: AlbumRepository,
    private val dispatchers : DispatcherProvider
) : ViewModel() {

    fun getImageList(albumName : String) : LiveData<List<PictureEntity>>{
        return repository.getAllPictures(albumName)
    }

    fun insertCurrentImage(pictureEntity: PictureEntity) {
        viewModelScope.launch(dispatchers.io) {
            repository.insertPictures(pictureEntity)
        }
    }

}