package com.masum.androiddevelopertask.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.masum.androiddevelopertask.R
import com.masum.androiddevelopertask.data.util.Resource
import com.masum.androiddevelopertask.databinding.FragmentHomeBinding
import com.masum.androiddevelopertask.presentation.adapter.HomeAdapter
import com.masum.androiddevelopertask.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {
    var previousPosition =-1

    @Inject
    lateinit var viewModel : HomeViewModel

    @Inject
    lateinit var adapter : HomeAdapter
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewModel.products.observe(viewLifecycleOwner){response ->
            when (response){
                is Resource.Success -> {
                    adapter.differ.submitList(response.data)
                    binding.progressBar.isVisible = false

                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                    response.data?.let {    adapter.differ.submitList(it)}
                }
                is Resource.Error -> {
                    binding.progressBar.isVisible = false

                    Snackbar.make(binding.homeRoot,"Somethings wants wrong!\n${response.message}",Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry"){
                            viewModel.getAllProducts()
                        }
                        .show()
                }
        }
        }

        binding.homeRecyclerview.adapter =adapter
        adapter.setOnItemClickListener { cartItem, position ->
            //remembering position for reset it on scroll
            previousPosition=position
        }
        binding.homeRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
               adapter.notifyItemChanged(previousPosition)

                //reseting position
                previousPosition=-1
            }


        })

    }
}