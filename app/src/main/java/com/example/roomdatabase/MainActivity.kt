package com.example.roomdatabase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.adapter.Contact_Adapter
import com.example.roomdatabase.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
var contactlist = listOf<ContactEntity>()
private lateinit var contactAdapter: Contact_Adapter
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnadd.setOnClickListener {

            var intent = Intent(this@MainActivity,ContactActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        var db = DBHelper.checkDB(this)
        contactlist = db.contactDao().getAllContact()

        val adapter = Contact_Adapter(contactlist as ArrayList<ContactEntity>)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
//        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.rvMain.layoutManager = layoutManager
        binding.rvMain.adapter = adapter
    }
}