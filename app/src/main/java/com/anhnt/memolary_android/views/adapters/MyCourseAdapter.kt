package com.anhnt.memolary_android.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anhnt.memolary_android.data.courses.MyCourse
import com.anhnt.memolary_android.databinding.ListItemMyCourseBinding
import com.bumptech.glide.Glide

class MyCoursesAdapter(
    private val myCourses: List<MyCourse>,
    private val listener: (MyCourse) -> Unit
) : ListAdapter<MyCourse, RecyclerView.ViewHolder>(MyCourseCallback()) {


    //ViewHolder
    class ViewHolder private constructor(
        binding: ListItemMyCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val title = binding.myCourseTitleTextView
        private val content = binding.myCourseContentTextView
        private val image = binding.myCourseImageView

        fun bind(item: MyCourse, listener: (MyCourse) -> Unit) {
            title.text = item.name
            content.text = item.description

            Glide.with(itemView.context).load(item.image).into(image)

            itemView.setOnClickListener {
                listener(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ListItemMyCourseBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }


    // Create new views (invoked by the layout manager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(myCourses[position], listener)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myCourses.size


}

class MyCourseCallback() : DiffUtil.ItemCallback<MyCourse>() {
    override fun areItemsTheSame(oldItem: MyCourse, newItem: MyCourse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MyCourse, newItem: MyCourse): Boolean {
        return oldItem == newItem
    }
}


