package com.thiha.ecommerceview.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.thiha.ecommerceview.activities.DetailsActivity
import com.thiha.ecommerceview.adapters.BrandRecyclerViewAdapter
import com.thiha.ecommerceview.adapters.ItemRecyclerViewAdapter
import com.thiha.ecommerceview.data.model.EcommerceViewModel
import com.thiha.ecommerceview.data.model.EcommerceViewModelImpl
import com.thiha.ecommerceview.data.vos.BrandsVO
import com.thiha.ecommerceview.data.vos.ItemVO
import com.thiha.ecommerceview.databinding.FragmentHomeBinding
import com.thiha.ecommerceview.delegates.BrandDelegate
import com.thiha.ecommerceview.delegates.ItemDelegate


class HomeFragment : Fragment(), ItemDelegate, BrandDelegate {
private var  _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mBrandRecyclerViewAdapter: BrandRecyclerViewAdapter
    private lateinit var mItemRecyclerViewAdapter: ItemRecyclerViewAdapter
    private lateinit var mBrandDelegate: BrandDelegate
    private lateinit var mItemDelegate: ItemDelegate
    private lateinit var viewModel: EcommerceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[EcommerceViewModelImpl::class.java]
        mItemDelegate = this
        mBrandDelegate = this
        setUpRecyclerView()

        viewModel.brands.observe(viewLifecycleOwner) { brands ->
            mBrandRecyclerViewAdapter.setNewData(brands)

        }

        viewModel.items.observe(viewLifecycleOwner) { items ->
            mItemRecyclerViewAdapter.setNewData(items)
            binding.rvItems.visibility = View.VISIBLE

        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Log.d("Error", errorMessage)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {

                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.getItemList("nike collection")
        viewModel.getBrandsList()
    }

    fun setUpRecyclerView() {
        mBrandRecyclerViewAdapter = BrandRecyclerViewAdapter(mBrandDelegate)
        binding.rvBrands.adapter = mBrandRecyclerViewAdapter
        mItemRecyclerViewAdapter = ItemRecyclerViewAdapter(mItemDelegate)
        binding.rvItems.adapter = mItemRecyclerViewAdapter
    }


    override fun onTapItem(item : ItemVO) {
        val intent = DetailsActivity.newIntent(requireContext(),item)
        startActivity(intent)
    }

    override fun onTapFav(isFav: Boolean) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTapBrand(brand: BrandsVO) {
      viewModel.getItemList(brand.name)
        binding.rvItems.visibility = View.GONE
    }

}