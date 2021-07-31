package com.sumeet.kaagazcameraassignment.view.camera

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sumeet.kaagazcameraassignment.databinding.ActivityCameraBinding
import java.io.File

class CameraActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityCameraBinding
    private lateinit var camera : Camera
    private lateinit var preview : Preview
    private lateinit var imageCapture: ImageCapture
    private var cameraSelector : CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkCameraPermission()

        binding.btnCapture.setOnClickListener {
            takePhoto()
        }

    }

    private fun takePhoto() {
        val photoFile = File(externalMediaDirs.firstOrNull(),"KaagazAssignment_${System.currentTimeMillis()}.jpg")
        val output = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            output,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Toast.makeText(baseContext,"Image Saved",Toast.LENGTH_SHORT).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(baseContext,"Sorry, Please try again",Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    private fun checkCameraPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED){
            startCamera()
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED){
            startCamera()
        }else{
            Toast.makeText(this,"Camera Permission Needed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCamera() {
        //listener to check is camera bind
        val cameraProviderListener = ProcessCameraProvider.getInstance(this)
        cameraProviderListener.addListener(
            Runnable {
                val cameraProvider = cameraProviderListener.get()
                preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
                }
                imageCapture = ImageCapture.Builder().build()

                cameraProvider.unbindAll()

                camera = cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            },
            ContextCompat.getMainExecutor(this)
        )
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btnCapture ->{
                //TODO CLICK
            }
        }
    }

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 0
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

}