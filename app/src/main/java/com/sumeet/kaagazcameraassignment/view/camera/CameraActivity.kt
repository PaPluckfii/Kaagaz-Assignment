package com.sumeet.kaagazcameraassignment.view.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import com.sumeet.kaagazcameraassignment.databinding.ActivityCameraBinding
import com.sumeet.kaagazcameraassignment.view.album.AlbumActivity
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

/**
 * Constants
 */
private const val REQUEST_CODE_PERMISSIONS = 0
private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
private const val TIMER_FLAG = 500L

/**
 * This is the camera activity to capture pictures.
 */
@AndroidEntryPoint
class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private lateinit var camera: Camera
    private lateinit var preview: Preview
    private lateinit var imageCapture: ImageCapture
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private lateinit var albumEntity: AlbumEntity
    private lateinit var listOFImages : List<PictureEntity>
    private val viewModel : CameraViewModel by viewModels()
    private lateinit var path : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.postDelayed(
            Runnable { hideUI() },
            TIMER_FLAG
        )

        albumEntity = intent.getSerializableExtra("currentAlbum") as AlbumEntity
        //listOFImages = viewModel.getImageList(albumEntity.id)

        checkCameraPermission()

        handleClicks()

    }

    /**
     * Function to take picture
     */
    private fun takePhoto() {
        val photoFile = File(
            externalMediaDirs.firstOrNull(),
            "KaagazAssignment_${albumEntity.name}_${System.currentTimeMillis()}.jpg"
        )
        val output = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            output,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val intent = Intent(this@CameraActivity, AlbumActivity::class.java)
                    intent.putExtra("currentAlbum",albumEntity)

                    viewModel.insertCurrentImage(
                        PictureEntity(
                            albumName = albumEntity.name,
                            picName = photoFile.name,
                            uri = outputFileResults.toString()
                        )
                    )

                    startActivity(intent)
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(baseContext, "Sorry, Please try again", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        )
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            Toast.makeText(this, "Camera Permission Needed", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Function to hide SystemUI on regular interval of time.
     */
    private fun hideUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    /**
     * Function to start the camera
     */
    private fun startCamera() {
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

    /**
     * Function to handle clicks
     */
    private fun handleClicks() {
        binding.btnCapture.setOnClickListener {
            takePhoto()
        }
        binding.btnFlipCam.setOnClickListener {
            cameraSelector = if (CameraSelector.DEFAULT_BACK_CAMERA == cameraSelector) {
                CameraSelector.DEFAULT_FRONT_CAMERA
            } else {
                CameraSelector.DEFAULT_BACK_CAMERA
            }
            startCamera()
        }
        binding.btnGallery.setOnClickListener {
            val intent = Intent(this, AlbumActivity::class.java)
            startActivity(intent)
        }
    }

}