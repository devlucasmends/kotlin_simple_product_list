package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProductsDao
import com.example.orgs.ui.recyclerview.adapter.ListProductsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListProductsActivity : AppCompatActivity() {
    private val dao = ProductsDao()
    private val adapter = ListProductsAdapter(
        this,
        dao.searchAll()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_products);
        configureRecyclerView()
        configureFab()
        configureClearFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.searchAll())
    }

    private fun configureFab() {
        val fab = findViewById<FloatingActionButton>(R.id.list_product_fab)
        fab.setOnClickListener {
            goToForm()
        }
    }

    private fun configureClearFab() {
        val fab = findViewById<FloatingActionButton>(R.id.list_product_clear_fab)
        fab.setOnClickListener {
            clearProductsList()
        }
    }

    private fun clearProductsList() {
        adapter.clearList()
        dao.clearList()
    }

    private fun goToForm() {
        val intent = Intent(
            this,
            FormProductActivity::class.java
        )
        startActivity(intent)
    }

    private fun configureRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.list_product_recyclerView)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        recyclerView.layoutManager = layoutManager

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                layoutManager.orientation
            )
        )
    }
}