package com.example.firebaseauthdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var signOutButton: Button
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signOutButton = findViewById(R.id.signOut)

        val uname =intent.getStringExtra("userName")
        val uid =intent.getStringExtra("userID")
        val uprofile =intent.getStringExtra("userProfile")
        val uemail =intent.getStringExtra("userEmail")

        val userName = findViewById<TextView>(R.id.uname)
        userName.setText(uname)

        val userEmail = findViewById<TextView>(R.id.uemail)
        userEmail.setText(uemail)

        val userID = findViewById<TextView>(R.id.uid)
        userID.setText(uid)

        val userProfile = findViewById<ImageView>(R.id.uprofile)
        Glide.with(this).load(uprofile).into(userProfile)

        signOutButton.setOnClickListener{
            auth = Firebase.auth
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build()
            googleSignInClient = GoogleSignIn.getClient(this, gso)
            signOut()
        }

    }

    private fun signOut() {
        auth.signOut()
        googleSignInClient.signOut().addOnCompleteListener {
            val SignInIntent= Intent(this, SignInActivity::class.java)
            startActivity(SignInIntent)
            finish()
            println("Happepning")
        }
    }
}
