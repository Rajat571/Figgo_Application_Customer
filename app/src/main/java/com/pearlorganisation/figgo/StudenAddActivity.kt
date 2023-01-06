package com.pearlorganisation.figgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pearlorganisation.figgo.Adapter.StudentAdapter
import com.pearlorganisation.figgo.Model.Student
import com.pearlorganisation.figgo.databinding.ActivityStudenAddBinding

class StudenAddActivity : AppCompatActivity(), View.OnClickListener {

    private val studentList : ArrayList<Student> = ArrayList()
    private lateinit var studentAdapter : StudentAdapter
    private lateinit var binding : ActivityStudenAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_studen_add)

        setAdapter()
        setListener()
    }

    private fun setAdapter(){
        studentAdapter = StudentAdapter(studentList)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@StudenAddActivity)
            adapter = studentAdapter
        }
    }

    private fun setListener() {
        binding.tvAddItem.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.tvAddItem.id -> {
                studentList.add(Student())
                studentAdapter.notifyItemInserted(studentList.size-1)
            }
            binding.btnSubmit.id -> {
                studentList.forEachIndexed { index, student ->
                    Log.d("<RESULT>", "Name : ${student.name} - Score : ${student.score}")
                }
            }
        }
    }
}