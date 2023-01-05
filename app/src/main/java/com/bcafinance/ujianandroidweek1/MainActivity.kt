package com.bcafinance.ujianandroidweek1

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.app.DatePickerDialog
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_check_in.*
import kotlinx.android.synthetic.main.fragment_izin.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val REQUEST_CODE_PERMISIONS = 999
        private val CAMERA_REQUEST = 998
        private val CAMERA_REQUEST_1 = 995
        private val CAMERA_REQUEST_2 = 997
        private val CAMERA_REQUEST_3 = 996
    }


    var isAddImage: Boolean = false
    var isAddLampiran1: Boolean = false
    var isAddLampiran2: Boolean = false
    var isAddLampiran3: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(LoginFragment.newInstance("", ""))

    }


    fun loadFragment(fragment: Fragment) {
        val fragManager = supportFragmentManager.beginTransaction()
        fragManager.replace(R.id.vFragment, fragment)
        fragManager.addToBackStack("")
        fragManager.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_PERMISIONS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    captureCamera();
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_LONG).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun captureCamera(): Boolean {
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takeCamera, CAMERA_REQUEST);
        return isAddImage;
    }

    fun captureCamera1(): Boolean {
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takeCamera, CAMERA_REQUEST_1);
        return isAddLampiran1;
    }

    fun captureCamera2(): Boolean {
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takeCamera, CAMERA_REQUEST_2);
        return isAddLampiran2;
    }

    fun captureCamera3(): Boolean {
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takeCamera, CAMERA_REQUEST_3);
        return isAddLampiran3;
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            val bitmapImage = data?.extras?.get("data") as Bitmap;
            imageView2.setImageBitmap(bitmapImage)
            isAddImage = true;
            saveImage(bitmapImage);
        } else if (requestCode == CAMERA_REQUEST_1 && resultCode == AppCompatActivity.RESULT_OK) {
            val bitmapImage = data?.extras?.get("data") as Bitmap;
            btnlampiran1.setImageBitmap(bitmapImage)
        } else if (requestCode == CAMERA_REQUEST_2 && resultCode == AppCompatActivity.RESULT_OK) {
            val bitmapImage = data?.extras?.get("data") as Bitmap;
            btnlampiran2.setImageBitmap(bitmapImage)
        } else if (requestCode == CAMERA_REQUEST_3 && resultCode == AppCompatActivity.RESULT_OK) {
            val bitmapImage = data?.extras?.get("data") as Bitmap;
            btnlampiran3.setImageBitmap(bitmapImage)
        }

    }


    fun saveImage(bitMap: Bitmap) {
        val tanggal = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date());
        val extStorage = getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString();
        val namaFile = extStorage + "/BCAF_" + tanggal + ".png";

        var file: File? = null;
        file = File(namaFile);
        file.createNewFile();

        val bos = ByteArrayOutputStream();
//        bitMap.compress(Bitmap.CompressFormat.PNG,0,bos);
        val bitmapData = bos.toByteArray();

        val fos = FileOutputStream(file);

        fos.write((bitmapData));
        fos.flush();
        fos.close();
    }

    fun datePicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker,
                year: Int,
                monthOfYear: Int,
                dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MMM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
                editTextDate!!.setText(sdf.format(c.getTime()))
            }
        }

        DatePickerDialog(
            this, datSetListener,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        ).show()

    }
    fun datePicker2(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker,
                year: Int,
                monthOfYear: Int,
                dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MMM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
                editTextDate2!!.setText(sdf.format(c.getTime()))
            }
        }

        DatePickerDialog(
            this, datSetListener,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        ).show()

    }
}

