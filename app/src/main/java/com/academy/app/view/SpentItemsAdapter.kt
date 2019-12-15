package com.academy.app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.academy.app.R
import com.academy.app.db.model.Expense

class SpentItemsAdapter (private val items: List<Expense>) :
    RecyclerView.Adapter<SpentItemsAdapter.ItemsHolder>() {
    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemsHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))


    class ItemsHolder (view: View) : RecyclerView.ViewHolder(view) {
        private val category: TextView = view.findViewById(R.id.category_name)
        private val date: TextView = view.findViewById(R.id.item_data)
        private val sum: TextView = view.findViewById(R.id.item_sum)
        private val currency: TextView = view.findViewById(R.id.item_currency)

        fun bind(item: Expense) {
            category.text = item.currency.toString()
            date.text = item.date.toString()
            sum.text = item.expenseSum.toString()
            currency.text = item.currency.toString()
        }

    }




}