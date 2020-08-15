package com.ezyschooling.contactUs

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import com.ezyschooling.R
import kotlinx.android.synthetic.main.contact_us.*
import android.text.SpannableStringBuilder as SpannableStringBuilder

class contactUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_us)
        setSpanOnText()

    }

    //Setting span on text
    private fun setSpanOnText(){

        val tvName: TextView = tvName
        val tvEmail: TextView = tvEmail
        val tvMessage: TextView = tvMessage
        val tvReachUsEmail : TextView = tvReachUsEmail
        val tvReachUsPhone: TextView = tvReachUsPhone
        val tvReachUsAddress:TextView = tvReachUsAddress


        //span on Name
        val spanName = SpannableStringBuilder(tvName.text)
        spanName.setSpan(
            ForegroundColorSpan(Color.RED),
            4,5,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvName.text = spanName

        //span on Email
        val spanEmail = SpannableStringBuilder(tvEmail.text)
        spanEmail.setSpan(
            ForegroundColorSpan(Color.RED),
            5,6,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvEmail.text = spanEmail

        //span on Message
        val spanMessage = SpannableStringBuilder(tvMessage.text)
        spanMessage.setSpan(
            ForegroundColorSpan(Color.RED),
            7,8,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvMessage.text = spanMessage

        //span on Reach Us Email
        val spanReachUsEmail = SpannableStringBuilder(tvReachUsEmail.text)
        spanReachUsEmail.setSpan(
            StyleSpan(Typeface.BOLD),
            0,6 ,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvReachUsEmail.text = spanReachUsEmail

        //span on Reach Us Phone
        val spanReachUsPhone = SpannableStringBuilder(tvReachUsPhone.text)
        spanReachUsPhone.setSpan(
            StyleSpan(Typeface.BOLD),
            0,6 ,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvReachUsPhone.text = spanReachUsPhone

        //span on Reach Us Address
        val spanReachUsAddress = SpannableStringBuilder(tvReachUsAddress.text)
        spanReachUsAddress.setSpan(
            StyleSpan(Typeface.BOLD),
            0,8 ,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvReachUsAddress.text = spanReachUsAddress

    }
}
