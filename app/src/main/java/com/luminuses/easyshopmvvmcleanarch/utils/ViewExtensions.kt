package com.luminuses.easyshopmvvmcleanarch.utils

import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.luminuses.easyshopmvvmcleanarch.R

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showToast(text: String) = Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()

fun BottomNavigationView.updateCartBadgeVisibility(showBadge: Boolean) {
    val badge = getOrCreateBadge(R.id.cartFragment)
    badge.isVisible = showBadge
}
