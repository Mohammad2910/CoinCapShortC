package com.example.coincapshortc.binding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("app:isVisible")
fun View.setVisible(visible: Boolean) {
    this.isVisible = visible
}