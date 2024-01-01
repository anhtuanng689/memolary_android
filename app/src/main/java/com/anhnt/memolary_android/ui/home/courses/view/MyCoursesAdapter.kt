package com.anhnt.memolary_android.ui.home.courses.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anhnt.memolary_android.R
import com.anhnt.memolary_android.data.course.model.MyCourse
import com.bumptech.glide.Glide

class MyCoursesAdapter(private val courses: List<MyCourse>) :
    RecyclerView.Adapter<MyCoursesAdapter.MyCoursesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCoursesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.my_course_card_view, parent, false)
        return MyCoursesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyCoursesViewHolder, position: Int) {
        val course = courses[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int {
        return courses.size
    }


    inner class MyCoursesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.my_course_image_view)
        private val titleTextView: TextView = itemView.findViewById(R.id.my_course_title_text_view)
        private val contentTextView: TextView =
            itemView.findViewById(R.id.my_course_content_text_view)

        fun bind(course: MyCourse) {
            Glide.with(itemView.context).load(course.image)
                .into(imageView)
            titleTextView.text = course.name
            contentTextView.text = course.number.toString()
        }
    }

}
