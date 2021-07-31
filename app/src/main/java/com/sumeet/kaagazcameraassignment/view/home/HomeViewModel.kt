package com.sumeet.kaagazcameraassignment.view.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    //private val repository = AlbumRepository(context)

    fun getAllAlbums() : List<AlbumEntity>{
        var list = emptyList<AlbumEntity>()
        viewModelScope.launch(Dispatchers.IO){
            //list = repository.getAllAlbums()
        }
        return list
    }

}