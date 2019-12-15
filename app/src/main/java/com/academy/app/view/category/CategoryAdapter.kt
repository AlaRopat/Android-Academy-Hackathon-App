package com.academy.app.view.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.academy.app.R
import com.academy.app.db.model.Category

class CategoryAdapter(var categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, null)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = categories.size

    private fun getItem(position: Int): Category = categories[position]

    class ViewHolder(
        itemView: View

    ) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.category_name)
        private val image: ImageView = itemView.findViewById(R.id.category_icon)


        fun bind(category: Category) {
            image.setImageResource(category.img)
            title.text = category.categoryName

        }
    }
}
