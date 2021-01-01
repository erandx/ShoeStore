package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentListingBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding

import com.udacity.shoestore.viewmodels.ListingViewModel


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class ListingFragment : Fragment() {

    //View Model
    private lateinit var ViewModel: ListingViewModel

    //data binding
    private lateinit var binding: FragmentListingBinding


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)

        ViewModel = ViewModelProvider(requireActivity()).get(ListingViewModel::class.java)

        // Binding ViewModel to the xml variable "listingViewModel"
        binding.listingViewModel = ViewModel

        ViewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->

            for (shoe in shoeList) {

                val listItemBinding = ShoeListItemBinding.inflate(layoutInflater, null, false)
                listItemBinding.shoe = shoe

                binding.shoesListLayout.addView(listItemBinding.root)
            }

        })

        //Floating Action Button to navigate
        binding.FabShoeAdd.setOnClickListener { view: View ->
            //Different Way to Navigate Using Navigation Class
            findNavController().navigate(ListingFragmentDirections.actionListingFragmentToShoeDetailsFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireView().findNavController().navigate(ListingFragmentDirections.actionListingFragmentToLoginFragment())
        return super.onOptionsItemSelected(item)

    }

}