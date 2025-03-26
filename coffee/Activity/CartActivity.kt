package com.example.coffee.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee.Adapter.CartAdapter
import com.example.coffee.Helper.ChangeNumberItemsListener
import com.example.coffee.Helper.ManagmentCart
import com.example.coffee.R
import com.example.coffee.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managment:ManagmentCart
    private var tax:Double=0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityCartBinding.inflate(layoutInflater)

        setContentView(binding.root)

        managment=ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartList()

    }

    private fun initCartList() {
        with(binding){
            cartView.layoutManager=LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)
            cartView.adapter=CartAdapter(managment.getListCart(),this@CartActivity,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    calculateCart()
                }

            })
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener{
            finish()
        }
    }

    private fun calculateCart() {
        val percentTax=0.02
        val delivery=50.0
        tax=Math.round((managment.getTotalFee()*percentTax)*100)/100.0
        val total=Math.round((managment.getTotalFee()+tax+delivery)*100)/100
        val itemTotal= Math.round(managment.getTotalFee()*100)/100

        with(binding){
            totalFeeTxt.text="Rs. $itemTotal"
            taxTxt.text="Rs. $tax"
            deliveryTxt.text="Rs. $delivery"
            totalTxt.text="Rs. $total"

        }
    }
}