package com.example.roomdatabase.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.ContactActivity
import com.example.roomdatabase.ContactEntity
import com.example.roomdatabase.DBHelper
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.ContactTileBinding

class Contact_Adapter(var contactList: ArrayList<ContactEntity>) :
    RecyclerView.Adapter<Contact_Adapter.ContectViewHolder>() {

    class ContectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ContactTileBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContectViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.contact_tile, parent, false)

        return ContectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ContectViewHolder, position: Int) {

        holder.binding.txtname.setText("Name : ${contactList[position].name}")
        holder.binding.txtcontact.setText("Phone : ${contactList[position].phone}")
        holder.binding.txtemail.setText("Email : ${contactList[position].email}")

//        val contact = contactList[position]
//        holder.binding.txtname.text = contact.name
//        holder.binding.txtcontact.text = contact.phone
//        holder.binding.txtemail.text = contact.email

        holder.binding.rvbtnDelete.setOnClickListener {
            var db = DBHelper.checkDB(holder.itemView.context)
            db.contactDao().deleteContact(contactList[position])
            contactList.removeAt(position)
            notifyDataSetChanged()
        }
        holder.binding.rvbtnEdit.setOnClickListener {
          var intent = Intent(holder.itemView.context,ContactActivity::class.java)
            intent.putExtra("id",contactList[position].id)
            intent.putExtra("name",contactList[position].name)
            intent.putExtra("phone",contactList[position].phone)
            intent.putExtra("email",contactList[position].email)
            holder.itemView.context.startActivity(intent)
        }

    }
}
