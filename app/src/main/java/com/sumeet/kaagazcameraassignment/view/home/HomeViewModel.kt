package com.sumeet.kaagazcameraassignment.view.home

import android.content.Context
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

    fun getAllAlbums() : LiveData<List<AlbumEntity>>{
        var list = MutableLiveData<List<AlbumEntity>>()
        viewModelScope.launch(dispatchers.io){
            list.postValue(repository.getAllAlbums().value)
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