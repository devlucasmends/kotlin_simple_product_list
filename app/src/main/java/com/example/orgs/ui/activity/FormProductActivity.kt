package com.example.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProductsDao
import com.example.orgs.model.Product
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(R.layout.activity_form_product) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureSaveButton()
    }

    private fun configureSaveButton() {
        val saveButton = findViewById<Button>(R.id.form_product_button)
        saveButton.setOnClickListener {
            val nameField = findViewById<EditText>(R.id.form_product_name)
            val name = nameField.text.toString()

            val descriptionField = findViewById<EditText>(R.id.form_product_description)
            val description = descriptionField.text.toString()

            val valueField = findViewById<EditText>(R.id.form_product_value)
            val value = valueField.text.toString()

            val dao = ProductsDao()

            if (name.isEmpty() || description.isEmpty() || value.isEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.alert_filling_of_fields),
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                dao.add(Product(
                    name = name,
                    description = description,
                    value = BigDecimal(value)
                ))
                Toast.makeText(
                    this,
                    getString(R.string.alert_product_created_success),
                    Toast.LENGTH_SHORT
                )
                    .show()
               finish()
            }
        }
    }
}