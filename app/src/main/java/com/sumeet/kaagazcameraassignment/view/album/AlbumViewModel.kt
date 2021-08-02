package com.sumeet.kaagazcameraassignment.view.album

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
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
class AlbumViewModel @Inject constructor(
    private val repository: AlbumRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel(){

    fun getAllPictures(albumName: String) : LiveData<List<PictureEntity>>{
        return repository.getAllPictures(albumName)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun deleteAlbum(currentAlbum: AlbumEntity) {
        viewModelScope.launch(dispatcherProvider.io) {
            repository.deleteAlbum(currentAlbum)

            val list = repository.getListOfPics(currentAlbum.name)

            for(i in list){
                try {
                    Files.delete(i.uri?.toUri()?.path as Path)
                }catch (e : Exception){}
            }

            repository.deleteImages(currentAlbum.name)
        }
    }

}