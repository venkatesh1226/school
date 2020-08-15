package com.ezyschooling.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.ezyschooling.LogIn.models.LoginAcitivity
import com.ezyschooling.R
import com.ezyschooling.api.RetrofitClient
import com.ezyschooling.api.models.DefaultResponse
import kotlinx.android.synthetic.main.fragment_parent_signup.*
import kotlinx.android.synthetic.main.fragment_parent_signup.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    var gender =""
    var parentType ="father"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_parent_signup)
        signup()
        val backbutton=findViewById<ImageButton>(R.id.article_back)
        backbutton.setOnClickListener {
            finish()
        }
     /*   val loginbutton=findViewById<Button>(R.id.login_button)
        loginbutton.setOnClickListener {
            //  Toast.makeText(context,"hello",Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginAcitivity::class.java)
            ContextCompat.startActivity(this, intent, Bundle())
        }*/
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("Father", "Mother","Gaurdian","Student"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        findViewById<Spinner>(R.id.positionSpinner).adapter = adapter

        findViewById<Spinner>(R.id.positionSpinner).onItemSelectedListener = object: AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // either one will work as well
                // val item = parent.getItemAtPosition(position) as String

                val item = adapter.getItem(position)

                if(item=="Father"){

                    findViewById<RadioGroup>(R.id.radio_group).visibility=View.GONE
                    gender="Male"

                }else if(item=="Mother"){

                    findViewById<RadioGroup>(R.id.radio_group).visibility=View.GONE
                    gender="Female"


                }else if(item=="Gaurdian"){

                    findViewById<RadioGroup>(R.id.radio_group).visibility=View.VISIBLE
                    gender=""

                }else if(item=="Student"){

                    findViewById<RadioGroup>(R.id.radio_group).visibility=View.VISIBLE
                    gender=""

                }

                Toast.makeText(this@SignUpActivity,
                    "you clicked $item",Toast.LENGTH_LONG).show()

            }
        }

        findViewById<RadioGroup>(R.id.radio_group).setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," On checked change : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
                gender=radio.text.toString()

            })
    }
    fun signup()
    {        buttonSignUp.setOnClickListener {

    val username = editTextUserName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        fun isEmailValid(email:String) : Boolean{
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        if(username.isEmpty()){
            editTextUserName.error = "Username required"
            editTextUserName.requestFocus()
            return@setOnClickListener
        }

        if(email.isEmpty()){
            editTextEmail.error = "Email required"
            editTextEmail.requestFocus()
            return@setOnClickListener
        }
        if(!isEmailValid(email)){
            editTextEmail.error = "Invalid Email"
            editTextEmail.requestFocus()

            return@setOnClickListener
        }



        if(password.isEmpty()){
            editTextPassword.error = "Password required"
            editTextPassword.requestFocus()
            return@setOnClickListener
        }

        if(gender.isEmpty()){

            findViewById<RadioButton>(R.id.female).error="Set Gender"
            return@setOnClickListener
        }



        RetrofitClient.instance.createUser("", email, username, gender, password, parentType)
            .enqueue(object: Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {

                    if (response.code() == 201) {
                        Toast.makeText(this@SignUpActivity, "Registration success!", Toast.LENGTH_SHORT)
                            .show()

                    }
                    else if(response.code() == 400)
                    {
                        Toast.makeText(this@SignUpActivity, "Already Registered, Please Login", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else
                    {
                        Toast.makeText(this@SignUpActivity, "Please Try Again", Toast.LENGTH_SHORT)
                            .show()
                    }
                //    Toast.makeText(applicationContext,"" + response.body()?.key+response.code(), Toast.LENGTH_SHORT).show()
                }

            })

    }
    }
}
