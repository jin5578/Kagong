package com.tistory.jeongs0222.kagongapplication.utils

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.tistory.jeongs0222.kagongapplication.ui.profile.fragment.ProfileModifyFragment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


interface ImageCropProvider {

    fun cropImage(temp: Uri)
}

class ImageCropProviderImpl(
    private val activity: ProfileModifyFragment
) : ImageCropProvider {

    private val CROP_FROM_CAMERA = 222

    override fun cropImage(temp: Uri) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(temp, "image/*")

        val list = activity.context!!.packageManager.queryIntentActivities(intent, 0)

        activity.context!!.applicationContext.grantUriPermission(
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

            val folder = File(Environment.getExternalStorageDirectory().toString() + "/Serendipity/")

            val tempFile = File(folder.toString(), croppedFileName!!.name)

            val convertedTemp = FileProvider.getUriForFile(
                activity.context!!.applicationContext,
                "com.tistory.jeongs0222.kagongapplication.provider",
                tempFile
            )

            intent.putExtra("return-data", false)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, convertedTemp)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())

            val i = Intent(intent)

            val res = list[0]

            activity.context!!.applicationContext.grantUriPermission(
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
        val storageDir = File(Environment.getExternalStorageDirectory().toString() + "/Serendipity/")
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }

        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

}