package com.masum.androiddevelopertask.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.masum.androiddevelopertask.R
import com.masum.androiddevelopertask.databinding.FragmentCartBinding
import com.masum.androiddevelopertask.databinding.FragmentHomeBinding
import com.masum.androiddevelopertask.presentation.adapter.CartAdapter
import com.masum.androiddevelopertask.presentation.adapter.HomeAdapter
import com.masum.androiddevelopertask.presentation.viewmodel.CartViewModel
import com.masum.androiddevelopertask.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CartFragment : Fragment() {
    @Inject
    lateinit var viewModel : CartViewModel

    @Inject
    lateinit var adapter : CartAdapter
    private lateinit var binding : FragmentCartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding =FragmentCartBinding.bind(view)
        binding.backIcon.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToHomeFragment())

        }
        viewModel.cartProducts.observe(viewLifecycleOwner){
            adapter.differ.submitList(it)
        }
        binding.cartRecyclerview.adapter=adapter
        adapter.setOnItemClickListener { cartItem, position ->
            viewModel.addToCart(cartItem)
        }



        //back listener

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
               findNavController().navigate(CartFragmentDirections.actionCartFragmentToHomeFragment())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

    }

}