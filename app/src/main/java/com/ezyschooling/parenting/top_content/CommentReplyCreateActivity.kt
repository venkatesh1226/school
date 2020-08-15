package com.ezyschooling.parenting.top_content

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ezyschooling.LogIn.models.LoginAcitivity
import com.ezyschooling.R
import com.ezyschooling.SharedprefManager
import org.json.JSONObject

class CommentReplyCreateActivity : AppCompatActivity() {
    lateinit var comment_id: String
    lateinit var article_id: String
lateinit var discussion_id: String

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_reply_create)

        val commentReplyBox = findViewById<EditText>(R.id.reply_create_box)

        commentReplyBox.setOnTouchListener(View.OnTouchListener { v, event ->
            if (commentReplyBox.hasFocus()) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_SCROLL -> {
                        v.parent.requestDisallowInterceptTouchEvent(false)
                        return@OnTouchListener true
                    }
                }
            }
            false
        })

        val intent: Intent
        intent = getIntent()
        val extras = intent.extras

        if (extras != null) {

            comment_id = extras.getString("comment_id").toString()
            article_id = extras.getString("article_id").toString()
            discussion_id = extras.get("discussion_id").toString()
            Log.i("comment id", comment_id)
            Log.i("article", article_id)
            Log.i("discussion id",discussion_id)
        }

        var comment = commentReplyBox.text
        val postreply = findViewById<Button>(R.id.postreply)
        var url2=" "
        if (article_id!="0") { url2 = "https://api.parenting.ezyschooling.com/api/v1/articles/"+article_id+"/comments/"+comment_id+"/thread/create/"
        Log.i("aricleurl",url2)}
       else { url2 =
                "https://api.parenting.ezyschooling.com/api/v1/discussions/"+discussion_id+"/comments/"+comment_id+"/thread/create/"
            Log.i("disc url,",url2)
        }
        postreply.setOnClickListener {

           if(!SharedprefManager.getInstance(this)?.isLoggedin!!){
               alert()
               return@setOnClickListener
           }
            if(comment.isEmpty()){
                Toast.makeText(
                    this@CommentReplyCreateActivity,
                    "comment to post",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }
            AndroidNetworking.post(url2)
                .addBodyParameter("comment", comment.toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {


                        Toast.makeText(
                            this@CommentReplyCreateActivity,
                            "comment posted",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                    override fun onError(error: ANError) {
                        Toast.makeText(this@CommentReplyCreateActivity,"error",Toast.LENGTH_LONG).show()
                        // handle error
                    }
                }
                )
        }
    }
    fun alert() {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Login required")
        //set message for alert dialog
        builder.setMessage("you must log in to post comments")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Go to Login"){dialogInterface, which ->
            val intent=Intent(this, LoginAcitivity::class.java)
            startActivity(intent)
        }

        //performing negative action
        builder.setNegativeButton("No"){dialogInterface, which ->
            Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

}
