package com.luminuses.easyshopmvvmcleanarch.ui.cart

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.luminuses.easyshopmvvmcleanarch.R
import com.luminuses.easyshopmvvmcleanarch.common.ScreenState
import com.luminuses.easyshopmvvmcleanarch.databinding.FragmentCartBinding
import com.luminuses.easyshopmvvmcleanarch.utils.checkInternetConnection
import com.luminuses.easyshopmvvmcleanarch.utils.getUserIdFromSharedPref
import com.luminuses.easyshopmvvmcleanarch.utils.showConfirmationDialog
import com.luminuses.easyshopmvvmcleanarch.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CartViewModel by viewModels()

    private lateinit var adapter: CartListAdapter
    private lateinit var totalPrice: String


    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val userId = getUserIdFromSharedPref(sharedPref)
        viewModel.getCartsByUserId(userId)

        adapter = CartListAdapter(
            ::onItemLongClicked,
            ::updateTotalPriceInAdapter,
            ::updateCartItemQuantity,
            ::onItemShortClicked
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        setupObserver()
    }

    private fun setupObserver() {

        viewModel.userCarts.observe(viewLifecycleOwner) { userCartState ->
            when (userCartState) {
                is ScreenState.Error -> {
                    binding.btnBuyNow.isEnabled = false
//                    binding.detailProgressBar.gone()
                    requireView().showToast(userCartState.message)
                }

                is ScreenState.Loading -> {
//                    binding.detailProgressBar.visible()
                }

                is ScreenState.Success -> {
//                    binding.cartProgressBar.gone()
                    adapter.submitList(userCartState.uiData)
                    binding.rvCartProducts.adapter = adapter
                    binding.btnBuyNow.isEnabled = userCartState.uiData.isNotEmpty()
                }
            }

        }
    }

    private fun updateTotalPriceInAdapter() {
        val cartList = adapter.currentList
        val totalPrice = viewModel.calculateTotalPrice(cartList)
        viewModel.updateTotalPrice(totalPrice)
    }

    private fun updateCartItemQuantity(userCartUiData: UserCartUiData) {
        viewModel.updateUserCartItem(userCartUiData)
    }


    private fun onItemLongClicked(userCartUiData: UserCartUiData) {
        this.showConfirmationDialog(getString(R.string.shopping_list_delete_warn)) {
            deleteUserCartItemAndUpdateUI(userCartUiData)
        }
    }

    private fun deleteUserCartItemAndUpdateUI(userCartUiData: UserCartUiData) {
        viewModel.deleteUserCartItem(userCartUiData)
        updateAdapterAfterDeletion(userCartUiData)
    }

    private fun updateAdapterAfterDeletion(userCartUiData: UserCartUiData) {
        val newList = adapter.currentList.filter { it.productId != userCartUiData.productId }
        adapter.submitList(newList)
        updateTotalPriceAndUI(newList)
        if (newList.isEmpty()) {
            hideCartBadge()
            binding.btnBuyNow.isEnabled = false
        }
    }

    private fun updateTotalPriceAndUI(cartList: List<UserCartUiData>) {
        val totalPrice = viewModel.calculateTotalPrice(cartList)
        viewModel.updateTotalPrice(totalPrice)
        binding.tvTotalAmount.text = totalPrice.toString()
        requireView().showToast(getString(R.string.shopping_list_item_deleted_txt))
    }

    private fun hideCartBadge() {
//        viewModel.setBadgeState(
//            UserCartBadgeEntity(
//                getUserIdFromSharedPref(sharedPref),
//                false
//            )
//        )
//        showBadgeVisibility(false)
    }

    private fun onItemShortClicked(userCartUiData: UserCartUiData) {
//        val action =
//            CartFragmentDirections.actionCartFragmentToDetailFragment(userCartUiData.productId)
//        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}