package com.example.tdmpa_2p_pr03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var swtModo = findViewById<Switch>(R.id.swtModo)
        var imgModo = findViewById<ImageView>(R.id.imgModo)
        var btnAtras = findViewById<ImageButton>(R.id.btnAtrás)
        var btnDelante = findViewById<ImageButton>(R.id.btnDelante)
        var btnSearch = findViewById<ImageButton>(R.id.btnSearch)
        var txtBarraURL = findViewById<AutoCompleteTextView>(R.id.txtBarraURL)
        var btnRefresh = findViewById<ImageButton>(R.id.btnRefresh)

        var sitios : Array<String> = resources.getStringArray(R.array.sitios)
        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, sitios)
        txtBarraURL.setAdapter(adapter)
        val numeroTotalSitios = sitios.size

        txtBarraURL.setOnItemClickListener { _, _, position, _ ->
            contador = position
        }

        btnSearch.setOnClickListener(){
            modosDeSeguridadBarra()
        }

        btnAtras.setOnClickListener(){
            contador--
            if (contador < 0) {
                contador = numeroTotalSitios - 1
            }
            modosDeSeguridadNavegacion()
        }

        btnDelante.setOnClickListener(){
            contador++
            if (contador >= numeroTotalSitios) {
                contador = 0
            }
            modosDeSeguridadNavegacion()
        }

        btnRefresh.setOnClickListener(){
            refresh()
        }

        swtModo.setOnClickListener() {
            if (swtModo.isChecked) {
                imgModo.setImageResource(R.drawable.security)

            } else {
                imgModo.setImageResource(R.drawable.shield_off)
            }
        }


    }

    fun refresh(){
        var imgImagenPestana = findViewById<ImageView>(R.id.imgPestanaWeb)
        var txtPestanaWeb = findViewById<TextView>(R.id.txtPestanaWeb)
        var swtModo = findViewById<Switch>(R.id.swtModo)
        var txtBarraURL = findViewById<AutoCompleteTextView>(R.id.txtBarraURL)
        var imgModo = findViewById<ImageView>(R.id.imgModo)
        if(swtModo.isChecked){
            imgModo.setImageResource(R.drawable.security)
            buscarPorBarra(modoSeguro, txtBarraURL.text.toString())
            modificarPestana()


        }else{
            imgModo.setImageResource(R.drawable.shield_off)
            buscarPorBarra(modoNoSeguro, txtBarraURL.text.toString())
            imgImagenPestana.setImageResource(R.drawable.alert_circle)
            txtPestanaWeb.setText("No seguro")

        }
    }
    fun modosDeSeguridadBarra(){
        var imgImagenPestana = findViewById<ImageView>(R.id.imgPestanaWeb)
        var txtPestanaWeb = findViewById<TextView>(R.id.txtPestanaWeb)
        var swtModo = findViewById<Switch>(R.id.swtModo)
        var txtBarraURL = findViewById<AutoCompleteTextView>(R.id.txtBarraURL)
        var imgModo = findViewById<ImageView>(R.id.imgModo)
        if(swtModo.isChecked){
            imgModo.setImageResource(R.drawable.security)
            buscarPorBarra(modoSeguro, txtBarraURL.text.toString())
            modificarPestana()


        }else{
            imgModo.setImageResource(R.drawable.shield_off)
            buscarPorBarra(modoNoSeguro, txtBarraURL.text.toString())
            imgImagenPestana.setImageResource(R.drawable.alert_circle)
            txtPestanaWeb.setText("No seguro")

        }
    }

    fun modosDeSeguridadNavegacion(){
        var imgImagenPestana = findViewById<ImageView>(R.id.imgPestanaWeb)
        var txtPestanaWeb = findViewById<TextView>(R.id.txtPestanaWeb)
        var swtModo = findViewById<Switch>(R.id.swtModo)
        var imgModo = findViewById<ImageView>(R.id.imgModo)
        if(swtModo.isChecked){
            imgModo.setImageResource(R.drawable.security)
            navegacion(modoSeguro)
            modificarPestana()

        }else{
            imgModo.setImageResource(R.drawable.shield_off)
            navegacion(modoNoSeguro)
            imgImagenPestana.setImageResource(R.drawable.alert_circle)
            txtPestanaWeb.setText("No seguro")

        }
    }

    fun navegacion(modo: String){
        var txtBarraURL = findViewById<AutoCompleteTextView>(R.id.txtBarraURL)
        var sitios : Array<String> = resources.getStringArray(R.array.sitios)
        var webVista = findViewById<WebView>(R.id.webVista)
        var sitioWeb = sitios[contador]
        webVista.loadUrl("$modo$sitioWeb")
        txtBarraURL.setText(sitioWeb)
    }

    fun buscarPorBarra(modo: String, dominio: String){
        var webVista = findViewById<WebView>(R.id.webVista)
        webVista.loadUrl("$modo$dominio")
    }

    fun modificarPestana(){
        var imgImagenPestana = findViewById<ImageView>(R.id.imgPestanaWeb)
        var txtPestanaWeb = findViewById<TextView>(R.id.txtPestanaWeb)
        var txtBarraURL = findViewById<AutoCompleteTextView>(R.id.txtBarraURL)
        when (txtBarraURL.text.toString()){
            "www.apple.com" -> {
                imgImagenPestana.setImageResource(R.drawable.apple)
                txtPestanaWeb.setText("apple")
            }
            "aws.amazon.com/es/" -> {
                imgImagenPestana.setImageResource(R.drawable.aws)
                txtPestanaWeb.setText("aws")
            }
            "www.facebook.com" -> {
                imgImagenPestana.setImageResource(R.drawable.facebook)
                txtPestanaWeb.setText("facebook")
            }
            "github.com" -> {
                imgImagenPestana.setImageResource(R.drawable.github)
                txtPestanaWeb.setText("github")
            }
            "www.google.com.mx" -> {
                imgImagenPestana.setImageResource(R.drawable.google)
                txtPestanaWeb.setText("google")
            }
            "www.microsoft.com/es-mx/" -> {
                imgImagenPestana.setImageResource(R.drawable.microsoft)
                txtPestanaWeb.setText("microsoft")
            }
            "www.xbox.com/es-MX" -> {
                imgImagenPestana.setImageResource(R.drawable.microsoft_xbox)
                txtPestanaWeb.setText("xbox")
            }
            "www.playstation.com/es-mx/" -> {
                imgImagenPestana.setImageResource(R.drawable.sony_playstation)
                txtPestanaWeb.setText("play station")
            }
            "www.twitch.tv" -> {
                imgImagenPestana.setImageResource(R.drawable.twitch)
                txtPestanaWeb.setText("twitch")
            }
            "www.whatsapp.com/?lang=es_LA" -> {
                imgImagenPestana.setImageResource(R.drawable.whatsapp)
                txtPestanaWeb.setText("whatsapp")
            }
        }

    }
    val modoSeguro = "https://"
    val modoNoSeguro = "http://"
    var contador = 0
}