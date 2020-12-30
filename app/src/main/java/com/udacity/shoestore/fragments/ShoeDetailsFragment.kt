package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ListingViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailsFragment : Fragment() {

    //View Model
    private lateinit var ViewModel: ListingViewModel

    private lateinit var binding: FragmentShoeDetailsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)

        //We use requireActivity() in order to create an Activity level View Model
        ViewModel = ViewModelProvider(requireActivity()).get(ListingViewModel::class.java)

        binding.listingViewModel = ViewModel

        binding.lifecycleOwner = this

        binding.shoe = Shoe("", 0.0, "", "")

        ViewModel.eventShoeList.observe(viewLifecycleOwner, Observer { orderCompleted ->
            if (orderCompleted) {

                findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToListingFragment())
                ViewModel.listCompleted()

                Toast.makeText(activity, "Shoe Added", Toast.LENGTH_SHORT).show()
            }
        })

        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToListingFragment())
        }

        return binding.root
    }

}
