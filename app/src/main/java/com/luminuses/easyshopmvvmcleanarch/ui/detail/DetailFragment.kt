package com.luminuses.easyshopmvvmcleanarch.ui.detail

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.luminuses.easyshopmvvmcleanarch.R
import com.luminuses.easyshopmvvmcleanarch.common.ScreenState
import com.luminuses.easyshopmvvmcleanarch.databinding.FragmentDetailBinding
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartBadgeEntity
import com.luminuses.easyshopmvvmcleanarch.domain.entity.cart.UserCartEntity
import com.luminuses.easyshopmvvmcleanarch.utils.checkInternetConnection
import com.luminuses.easyshopmvvmcleanarch.utils.getUserIdFromSharedPref
import com.luminuses.easyshopmvvmcleanarch.utils.showBadgeVisibility
import com.luminuses.easyshopmvvmcleanarch.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var userCart: UserCartEntity


    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        detailViewModel.getProduct(args.productId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        setupProductDetail()
        setupAddToCartButton()

        binding.btnAddToFav.setOnClickListener {
            addToFavorite()
        }
    }



    private fun setupProductDetail() {
        detailViewModel.product.observe(viewLifecycleOwner) {productState ->
            when (productState) {
                is ScreenState.Error ->  {
//                    binding.detailProgressBar.gone()
                    requireView().showToast(productState.message)
                }
                is ScreenState.Loading -> {
//                    binding.detailProgressBar.visible()
                }
                is ScreenState.Success -> {
//                    binding.detailProgressBar.gone()
                    val product = productState.uiData
                    bindProductDetailToView(product)
                    viewPagerSetup(product)
                }
            }

        }

    }

    private fun viewPagerSetup(product: DetailProductUiData) {
        binding.viewPager.adapter = DetailImageViewPagerAdapter(product.imageUrl)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()
    }

    private fun bindProductDetailToView(product: DetailProductUiData) {
        binding.apply {
            detailProductTitle.text = product.title.capitalize()
            detailProductPrice.text = "${product.price} TL"
            detailProductDescription.text = product.description
            detailProductRatingTxt.text = product.rating
            detailProductRating.rating = product.rating.toFloat()

            val userId = getUserIdFromSharedPref(sharedPref)

            userCart = UserCartEntity(
                userId = userId,
                productId = product.id,
                quantity = 1,
                price = product.price.toDouble().toInt(),
                title = product.title,
                image = product.imageUrl[0],
            )
        }
    }

    private fun setupAddToCartButton() {
        binding.btnAddToCart.setOnClickListener {
            Log.d("TAG", "setupAddToCartButton: ")
            Log.d("TAG", "setupAddToCartButton: "+userCart)
            detailViewModel.addToCart(userCart)
            requireView().showToast(getString(R.string.added_to_cart))
            val badgeEntity = UserCartBadgeEntity(
                userUniqueInfo = userCart.userId,
                hasBadge = true,
            )
            detailViewModel.insertBadgeStatusToDb(badgeEntity)
            showBadgeVisibility(badgeEntity.hasBadge)
        }
    }

    private fun addToFavorite(){
        detailViewModel.addToFavorite(userCart)
        requireView().showToast(getString(R.string.added_to_favorite))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}