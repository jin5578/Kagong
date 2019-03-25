package com.tistory.jeongs0222.kagongapplication.ui.notice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentNoticeListBinding
import com.tistory.jeongs0222.kagongapplication.ui.notice.NoticeViewModel
import com.tistory.jeongs0222.kagongapplication.ui.notice.adapter.NoticeListAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class NoticeListFragment: Fragment() {

    private lateinit var binding: FragmentNoticeListBinding

    private val noticeViewModel by sharedViewModel<NoticeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNoticeListBinding.inflate(inflater, container, false).apply {
            nViewModel = noticeViewModel
            lifecycleOwner = this@NoticeListFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@NoticeListFragment.context)
            adapter = NoticeListAdapter(this@NoticeListFragment, noticeViewModel)
        }
    }

}