package com.pearlorganisation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pearlorganisation.figgo.BaseClass
import com.pearlorganisation.figgo.R

class Edit_Profile_Activity : BaseClass() {
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
        setContentView(R.layout.activity_edit_profile)
        shareimg()
        onBackPress()

    }
}