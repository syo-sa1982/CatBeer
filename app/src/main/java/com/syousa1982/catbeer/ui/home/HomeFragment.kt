package com.syousa1982.catbeer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.syousa1982.catbeer.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        val progress =  root.findViewById<ProgressBar>(R.id.progress)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        homeViewModel.viewState.observe(this, Observer {
            when (it) {
                HomeViewModel.ViewState.Progress -> progress.visibility = View.VISIBLE
                HomeViewModel.ViewState.Completed,
                HomeViewModel.ViewState.Error -> progress.visibility = View.GONE
            }
        })
        homeViewModel.start()
        return root
    }
}