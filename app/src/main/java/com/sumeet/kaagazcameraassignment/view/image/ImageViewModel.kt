package com.sumeet.kaagazcameraassignment.view.image

import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.repository.AlbumRepository
import com.sumeet.kaagazcameraassignment.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Path
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: AlbumRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(){

    fun getListOfImages(albumName : String) : LiveData<List<PictureEntity>>{
        return repository.getAllPictures(albumName)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun deletePicture(pictureEntity: PictureEntity) {
        viewModelScope.launch(dispatcherProvider.io) {
            repository.deleteImage(pictureEntity)

            try {
                Files.delete(pictureEntity.uri?.toUri()?.path as Path)
            }catch (e : Exception){}
        }
    }

}