package com.example.qrscanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnScan: Button =findViewById(R.id.btnScan)
        btnScan.setOnClickListener{
            val qrScan= IntentIntegrator(this)

            qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            qrScan.setBeepEnabled(false)
            qrScan.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data)

        if(result!=null){
            val tvResult:TextView=findViewById(R.id.tvResult)
            try {
                val obj: JSONObject = JSONObject(result.contents)

                tvResult.setText(obj.getString("name"))

            }
            catch(e:JSONException){
                tvResult.setText(result.contents)
            }

        }
        else{
            Toast.makeText(this,"Empty",Toast.LENGTH_LONG).show()
        }
    }
}