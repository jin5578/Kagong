package com.tistory.jeongs0222.kagongapplication.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import com.tistory.jeongs0222.kagongapplication.ui.profile.fragment.ProfileModifyFragment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


interface ImageCropProvider {

    fun cropImage(temp: Uri)

    fun galleryAddPic(): File
}

class ImageCropProviderImpl(
    private val activity: Activity
) : ImageCropProvider {

    private val CROP_FROM_CAMERA = 222
    private lateinit var mCurrentPhotoPath: String


    @SuppressLint("ObsoleteSdkInt")
    override fun cropImage(temp: Uri) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(temp, "image/*")

        val list = activity.packageManager.queryIntentActivities(intent, 0)

        activity.applicationContext.grantUriPermission(
            list[0].activityInfo.packageName,
            temp,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    or Intent.FLAG_GRANT_READ_URI_PERMISSION
        )

        val size = list.size

        if(size == 0) {
            return
        } else {
            intent.putExtra("crop", "true")
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            intent.putExtra("scale", true)

            var croppedFileName: File? = null

            try {
                croppedFileName = createImageFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val folder = File(Environment.getExternalStorageDirectory().absolutePath + "/Picture/Serendipity")

            val tempFile = File(folder.toString(), croppedFileName!!.name)

            mCurrentPhotoPath = tempFile.absolutePath

            Log.e("tempFile", tempFile.path.toString())

            val convertedTemp: Uri

            //SDK에 따른 분류
            convertedTemp = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(
                    activity.applicationContext,
                    "com.tistory.jeongs0222.kagongapplication.provider",
                    tempFile
                )
            } else {
                Uri.fromFile(tempFile)
            }



            intent.putExtra("return-data", false)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, convertedTemp)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString())

            val i = Intent(intent)

            val res = list[0]

            activity.applicationContext.grantUriPermission(
                res.activityInfo.packageName,
                convertedTemp,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
                        or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )

            i.component = ComponentName(res.activityInfo.packageName,
                res.activityInfo.name)

            activity.startActivityForResult(i, CROP_FROM_CAMERA)
        }

    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("HHmmss").format(Date())
        val imageFileName = timeStamp + "_"
        val storageDir = File(Environment.getExternalStorageDirectory().absolutePath + "/Picture/Serendipity")
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }

        return File.createTempFile(imageFileName, ".png", storageDir)
    }

    override fun galleryAddPic(): File {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)

        val file = File(mCurrentPhotoPath)

        mediaScanIntent.data = Uri.fromFile(file)

        activity.sendBroadcast(mediaScanIntent)

        return file
    }

}