package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Product

class ListProductsAdapter(
    private val context: Context,
    products: List<Product>,
) : RecyclerView.Adapter<ListProductsAdapter.ViewHolder>() {

    private val products = products.toMutableList()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindFields(product: Product) {
            val name = itemView.findViewById<TextView>(R.id.form_product_name)
            name.text = product.name
            val description = itemView.findViewById<TextView>(R.id.form_product_description)
            description.text = product.description
            val value = itemView.findViewById<TextView>(R.id.form_product_value)
            value.text = product.value.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(
            R.layout.product_item,
            parent,
            false
        )
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bindFields(product)
    }

    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    fun clearList(){
        this.products.clear()
        notifyDataSetChanged()
    }

}