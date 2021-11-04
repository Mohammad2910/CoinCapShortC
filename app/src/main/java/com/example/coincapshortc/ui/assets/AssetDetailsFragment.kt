package com.example.coincapshortc.ui.assets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coincapshortc.databinding.AssetDetailsScreenBinding
import com.example.coincapshortc.di.ServiceLocator.assetDetailsViewModel

class AssetDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AssetDetailsFragment()
    }

    private val viewModel: AssetDetailsViewModel by lazy { assetDetailsViewModel }
    private lateinit var binding: AssetDetailsScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        AssetDetailsScreenBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@AssetDetailsFragment.viewLifecycleOwner
                viewModel = this@AssetDetailsFragment.viewModel
                binding = this
            }
            .root
}