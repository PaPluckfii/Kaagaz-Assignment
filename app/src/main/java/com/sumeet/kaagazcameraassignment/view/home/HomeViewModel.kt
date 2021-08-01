package com.sumeet.kaagazcameraassignment.view.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.repository.AlbumRepository
import com.sumeet.kaagazcameraassignment.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val repository: AlbumRepository,
    private val dispatchers : DispatcherProvider
) : ViewModel() {

    fun getAllAlbums() : List<AlbumEntity>{
        var list : List<AlbumEntity> = emptyList()
        viewModelScope.launch(dispatchers.io){
            list = repository.getAllAlbums()
            Log.d("inRepo","size = ${list.size}")
        }
        return list
    }

    fun createAlbum(albumEntity: AlbumEntity){
        viewModelScope.launch(dispatchers.io) {
            repository.insertAlbum(albumEntity)
        }
    }

    fun insertNewAlbum(albumEntity: AlbumEntity) {
        viewModelScope.launch(dispatchers.io){
            repository.insertAlbum(albumEntity)
        }
    }

}