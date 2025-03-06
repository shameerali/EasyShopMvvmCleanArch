package com.luminuses.easyshopmvvmcleanarch.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.luminuses.easyshopmvvmcleanarch.R
import com.luminuses.easyshopmvvmcleanarch.common.ScreenState
import com.luminuses.easyshopmvvmcleanarch.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter


    @Inject
    lateinit var sharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        productAdapter = ProductAdapter(::navigateToProductDetail)
        binding.homeProductRv.adapter = productAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()

    }

    private fun setObservers() {

        homeViewModel.categories.observe(viewLifecycleOwner) { homepageState ->
            when (homepageState) {
                is ScreenState.Error -> {
                    Log.d("TAG", "setObservers: Error")
//                    binding.homeProgressBar.gone()
//                    requireView().showToast(homepageState.message)
                }

                is ScreenState.Loading -> {
                    Log.d("TAG", "setObservers: Loading")
//                    binding.homeProgressBar.visible()
                }

                is ScreenState.Success -> {
                    Log.d("TAG", "setObservers: Success "+homepageState.uiData)
//                    binding.homeCategoryRv.adapter =
//                        CategoryAdapter(homepageState.uiData) { categoryName ->
//                            getProductsByCategoryName(categoryName)
//                        }
//                    binding.homeProgressBar.gone()
                }
            }
        }


        homeViewModel.products.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Error -> {
//                    binding.homeProgressBar.gone()
//                    requireView().showToast(it.message)
                }

                ScreenState.Loading -> {
//                    binding.homeProgressBar.visible()
                }

                is ScreenState.Success -> {
                    productAdapter.submitList(it.uiData)
//                    binding.homeProgressBar.gone()
                }
            }
        }

    }


    private fun navigateToProductDetail(productId: Int) {
//        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(productId)
//        findNavController().navigate(action)
    }


}