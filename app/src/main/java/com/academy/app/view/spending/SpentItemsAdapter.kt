package com.academy.app.view.spending

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.academy.app.R
import com.academy.app.R.layout
import com.academy.app.Utils
import com.academy.app.db.model.Expense
import com.academy.app.view.spending.SpentItemsAdapter.ItemsHolder

class SpentItemsAdapter(private var items: List<Expense>) :
    RecyclerView.Adapter<ItemsHolder>() {

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(newItems: List<Expense>){
        items = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemsHolder(
            LayoutInflater.from(parent.context).inflate(
                layout.list_item,
                parent,
                false
            )
        )

    class ItemsHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val category: TextView = itemView.findViewById(R.id.item_category_name)
        private val date: TextView = itemView.findViewById(R.id.item_data)
        private val sum: TextView = itemView.findViewById(R.id.item_sum)
        private val currency: TextView = itemView.findViewById(R.id.item_currency)
        private val description: TextView = itemView.findViewById(R.id.item_description)

        @SuppressLint("NewApi")
        fun bind(item: Expense) {
            category.text = item.category?.categoryName
            date.text = item.date?.let { Utils.convertLongToTime(it) }
            sum.text = item.expenseSum.toString()
            currency.text = item.currency?.name
            description.text = item.description
        }
    }
}