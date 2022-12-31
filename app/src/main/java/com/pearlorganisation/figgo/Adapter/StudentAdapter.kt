package com.pearlorganisation.figgo.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Model.Student
import com.pearlorganisation.figgo.databinding.ListStudentItemBinding

class StudentAdapter(private val studentList : ArrayList<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(private val binding : ListStudentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student : Student){
            /*binding.student = student*/
            bind(student = Student())

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder =
        StudentViewHolder(ListStudentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = studentList.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position])
    }
}