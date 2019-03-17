package com.tistory.jeongs0222.kagongapplication.ui.register.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentYearBinding
import com.tistory.jeongs0222.kagongapplication.ui.register.adapter.YearAdapter
import com.tistory.jeongs0222.kagongapplication.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class YearFragment: Fragment() {

    private lateinit var binding: FragmentYearBinding

    private val registerViewModel by sharedViewModel<RegisterViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentYearBinding.inflate(inflater, container, false).apply {
            rViewModel = registerViewModel
            lifecycleOwner = this@YearFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@YearFragment.context)
            adapter = YearAdapter(
                this@YearFragment,
                registerViewModel
            )
        }
    }


}