package com.example.coffee.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.coffee.Adapter.SizeAdapter
import com.example.coffee.Helper.ManagmentCart
import com.example.coffee.Model.ItemsModel
import com.example.coffee.R
import com.example.coffee.databinding.ActivityDetailBinding
import com.example.coffee.databinding.ActivityIntroBinding

class DetailActivity : BaseActivity() {
    lateinit var binding:ActivityDetailBinding
    private lateinit var item:ItemsModel
    private lateinit var managmentCart: ManagmentCart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart=ManagmentCart(this)

        bundel()
        initSizeList()

    }

    private fun initSizeList() {
        val sizeList=ArrayList<String>()
        sizeList.add("1")
        sizeList.add("2")
        sizeList.add("3")
        sizeList.add("4")

        binding.sizeList.adapter=SizeAdapter(sizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val colorList = ArrayList<String>()
        for(imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }

        Glide.with(this)
            .load(colorList[0])
            .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
            .into(binding.picMain)
    }

    private fun bundel() {
        binding.apply {
            item = intent.getParcelableExtra("object")!!
            titleTxt.text=item.Title
            descriptionTxt.text=item.description
            priceTxt.text="Rs. "+item.price
            ratingBar2.rating=item.rating.toFloat()

            addToCartBtn.setOnClickListener{
                item.numberInCart=Integer.valueOf(
                    numberitemTxt.text.toString()
                )
                managmentCart.insertItems(item)
            }

            backbtn.setOnClickListener{
                startActivity(Intent(this@DetailActivity,MainActivity::class.java))
            }

            plusCart.setOnClickListener {
                numberitemTxt.text=(item.numberInCart+1).toString()
                item.numberInCart++
            }

            minusCart.setOnClickListener {
                if(item.numberInCart>0){
                    numberitemTxt.text=(item.numberInCart-1).toString()
                    item.numberInCart--
                }
            }


        }
    }
}