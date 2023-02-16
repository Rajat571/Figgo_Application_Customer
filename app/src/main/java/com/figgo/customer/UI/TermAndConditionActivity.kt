package com.figgo.customer.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.figgo.customer.R
import com.figgo.customer.pearlLib.BaseClass

class TermAndConditionActivity : BaseClass() {
    override fun setLayoutXml() {
        TODO("Not yet implemented")
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeClickListners() {
        TODO("Not yet implemented")
    }

    override fun initializeInputs() {
        TODO("Not yet implemented")
    }

    override fun initializeLabels() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_and_condition)

        shareimg()
        onBackPress()
    }
}