package com.rajdeep.kotlinretriverealtimedatabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var fname : TextView
    private lateinit var mname : TextView
    private lateinit var lname : TextView
    private lateinit var search : EditText
    private lateinit var databasereference : DatabaseReference
    private lateinit var searchbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    fname = findViewById<TextView>(R.id.fname);
    mname = findViewById<TextView>(R.id.mname);
    lname = findViewById<TextView>(R.id.lname);
    search = findViewById<EditText>(R.id.search);
    searchbtn = findViewById<Button>(R.id.searchaBtn)

    searchbtn.setOnClickListener {
        if (search.text.toString().isNotEmpty()){
            readdata(search.text.toString())
        } else{
            Toast.makeText(this,"Please enter the contact number",Toast.LENGTH_SHORT).show()
        }
    }

    }

    private fun readdata(contact : String){
        databasereference = FirebaseDatabase.getInstance().getReference("Users")
        databasereference.child(contact).get().addOnSuccessListener {
            if(it.exists()){
                val firstname = it.child("fname").value
                val middlename = it.child("mname").value
                val lastname = it.child("lname").value

                Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()

                search.text.clear()
                fname.text = firstname.toString()
                mname.text = middlename.toString()
                lname.text = lastname.toString()

            } else{
                Toast.makeText(this,"User Doesn't Exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }

}