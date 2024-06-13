package com.example.roomdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roomdatabase.databinding.ActivityContactBinding

lateinit var contactBinding: ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        contactBinding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(contactBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = DBHelper.checkDB(this)


        contactBinding.btnSave.setOnClickListener {

            var name = contactBinding.edtname.text.toString()
            var phone = contactBinding.edtphone.text.toString()
            var mail = contactBinding.edtmail.text.toString()

            var modal = ContactEntity(name = name, phone = phone, email = mail)
            db.contactDao().insartContact(modal)
            finish()
        }
    }

}