package com.sumeet.kaagazcameraassignment.view.home

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.sumeet.kaagazcameraassignment.R
import com.sumeet.kaagazcameraassignment.databinding.LayoutNewAlbumDialogBinding

class NewAlbumDialog : AppCompatDialogFragment() {

    private var _binding : LayoutNewAlbumDialogBinding? = null
    private val  binding get() = _binding!!
    private lateinit var albumName : String
    private lateinit var listener : AlbumDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        _binding = LayoutNewAlbumDialogBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())

        builder.setView(binding.root)
            .setTitle("Create A New Album")
            .setNegativeButton("Cancel",object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            })
            .setPositiveButton("Create", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if(binding.etAlbumName.text.toString() == ""){
                        binding.etAlbumName.error = "Can't create an empty album"
                    }else{
                        albumName = binding.etAlbumName.text.toString()
                        listener.getAlbumNameFromEditText(albumName)
                    }
                }
            })
        return builder.create()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            listener = activity as AlbumDialogListener
        }catch (e : ClassCastException){
            throw e
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    interface AlbumDialogListener{
        fun getAlbumNameFromEditText(name : String)
    }

}