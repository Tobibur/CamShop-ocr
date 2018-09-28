package com.tobibur.camshop.ui.main

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import com.tobibur.camshop.R
import com.tobibur.camshop.ResultActivity
import kotlinx.android.synthetic.main.main_fragment.*
import org.jetbrains.anko.toast

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var imageBitmap : Bitmap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        camBtn.setOnClickListener { dispatchTakePictureIntent() }

        detectBtn.setOnClickListener { detectText() }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity?.packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            imageBitmap = data.extras.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }

    private fun detectText() {
        FirebaseApp.initializeApp(context)
        val image : FirebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap)
        val detector : FirebaseVisionTextRecognizer = FirebaseVision.getInstance().onDeviceTextRecognizer
        detector.processImage(image)
                .addOnSuccessListener { text -> processText(text) }
                .addOnFailureListener { d("Failed", "Dectection Failed") }
    }

    private fun processText(text: FirebaseVisionText?) {
        val blocks : List<FirebaseVisionText.TextBlock> = text!!.textBlocks
        if(blocks.isEmpty()){
          activity!!.toast("No text :(")
            return
        }

        val txt : String = text.textBlocks[0].text
        val intent = Intent(activity, ResultActivity::class.java)
        intent.putExtra("Text", txt)
        activity!!.startActivity(intent)
    }
}
