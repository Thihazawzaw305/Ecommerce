package com.thiha.ecommerceview.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle.Delegate
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.thiha.ecommerceview.DESCRIPTION
import com.thiha.ecommerceview.EXTRA_ITEM_OBJECT
import com.thiha.ecommerceview.R
import com.thiha.ecommerceview.adapters.BrandRecyclerViewAdapter
import com.thiha.ecommerceview.adapters.ColorRecyclerViewAdapter
import com.thiha.ecommerceview.adapters.ItemRecyclerViewAdapter
import com.thiha.ecommerceview.adapters.SizesRecyclerViewAdapter
import com.thiha.ecommerceview.data.model.EcommerceViewModel
import com.thiha.ecommerceview.data.model.EcommerceViewModelImpl
import com.thiha.ecommerceview.data.vos.BrandsVO
import com.thiha.ecommerceview.data.vos.ItemVO
import com.thiha.ecommerceview.databinding.ActivityDetailsBinding

import com.thiha.ecommerceview.delegates.BrandDelegate
import com.thiha.ecommerceview.delegates.ColorDelegate
import com.thiha.ecommerceview.delegates.ItemDelegate
import com.thiha.ecommerceview.delegates.SizesDelegate

class DetailsActivity : AppCompatActivity(), SizesDelegate, ColorDelegate {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: EcommerceViewModel
    private lateinit var mSizesRecyclerViewAdapter: SizesRecyclerViewAdapter
    private lateinit var mColorRecyclerViewAdapter: ColorRecyclerViewAdapter
    private var isTextExpanded: Boolean = false
    private var count = 0

    companion object {
        fun newIntent(context: Context, item: ItemVO): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_ITEM_OBJECT, Gson().toJson(item))
            return intent
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize ViewModel here
        intent.getStringExtra(EXTRA_ITEM_OBJECT)?.let {
            val item = Gson().fromJson(it, ItemVO::class.java)
            viewModel = ViewModelProvider(this)[EcommerceViewModelImpl::class.java]
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.details)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            Glide.with(this)
                .load(item.imageUrl)
                .into(binding.ivItemDetail)
            binding.tvShoeName.text = item.name
            binding.tvShoePrice.text = item.price.toString()
            binding.tvRating.text = "(${item.rating})"
        //    binding.tvExpandableText.text = item.description
            binding.cbFavDetails.isChecked = item.isFav
            mSizesRecyclerViewAdapter = SizesRecyclerViewAdapter(this)
            binding.rvSizes.adapter = mSizesRecyclerViewAdapter

            mColorRecyclerViewAdapter = ColorRecyclerViewAdapter(this)
            binding.rvColor.adapter = mColorRecyclerViewAdapter

            binding.tvQuantityCount.text = count.toString()
            mSizesRecyclerViewAdapter.setNewData(splitSizes(item.sizes))
            mColorRecyclerViewAdapter.setNewData(splitSizes(item.colors))
            binding.ivExpandArrow.setOnClickListener {
                toggleTextExpansion(item.description,"description")
            }
            binding.ivExpandArrow2.setOnClickListener {
                toggleTextExpansion(item.description,"deli")

            }
            binding.btnIncrease.setOnClickListener {
                count ++
                binding.tvQuantityCount.text = count.toString()
            }
            binding.btnDecrease.setOnClickListener {
                if (count!=0){
                    count--
                    binding.tvQuantityCount.text = count.toString()
                }
            }


            binding.ivBack.setOnClickListener {
                onBackPressed()
            }
        }


    }

    private fun toggleTextExpansion(text: String, label: String) {
      if(label == "Description") {


          if (isTextExpanded) {
              // Collapse the text
              binding.tvExpandableText.text = "Description"

              rotateArrow(180f, 0f, 1)
          } else {
              // Expand the text
              binding.tvExpandableText.text = text
              binding.tvExpandableText.maxLines = Integer.MAX_VALUE
              rotateArrow(0f, 180f,1)
          }
          isTextExpanded = !isTextExpanded
      } else {
          if (isTextExpanded) {
              // Collapse the text
              binding.tvDeliExpandableText.text = "Free Delivery and Returns"

              rotateArrow(180f, 0f,2)
          } else {
              // Expand the text
              binding.tvDeliExpandableText.text = text
              binding.tvDeliExpandableText.maxLines = Integer.MAX_VALUE
              rotateArrow(0f, 180f,2)
          }
          isTextExpanded = !isTextExpanded
      }
    }

    private fun rotateArrow(fromDegrees: Float, toDegrees: Float, arrowNum : Int) {
        val rotate = RotateAnimation(
            fromDegrees,
            toDegrees,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 300
        rotate.fillAfter = true
        if (arrowNum == 1){
            binding.ivExpandArrow.startAnimation(rotate)

        } else if (arrowNum == 2){
            binding.ivExpandArrow2.startAnimation(rotate)
        }
    }

    fun splitSizes(sizeString: String): List<String> {
        return sizeString.split("/")
    }

    override fun onTapSize(size: String) {
        Toast.makeText(this, "Selected size: $size", Toast.LENGTH_SHORT).show()
    }

    override fun onTapColor(color: String) {
        val selectColor = Color.parseColor("#DADADA")
        Toast.makeText(this, "Selected Color: $selectColor", Toast.LENGTH_SHORT).show()

    }
}
