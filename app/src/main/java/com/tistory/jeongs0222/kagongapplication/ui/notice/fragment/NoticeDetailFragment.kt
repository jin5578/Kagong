package com.tistory.jeongs0222.kagongapplication.ui.notice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentNoticeDetailBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringNotice.BringNoticeResult
import com.tistory.jeongs0222.kagongapplication.ui.notice.NoticeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class NoticeDetailFragment: Fragment() {

    private lateinit var binding: FragmentNoticeDetailBinding

    private val noticeViewModel by sharedViewModel<NoticeViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNoticeDetailBinding.inflate(inflater, container, false).apply {
            nViewModel = noticeViewModel
            lifecycleOwner = this@NoticeDetailFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noticeViewModel.selectedPosition.observe(this@NoticeDetailFragment, Observer {
            val item = noticeViewModel.noticeList.value!![it-1]

            binding.apply {
                title.text = item.title
                date.text = item.date
                content.text = item.content
            }
        })


    }
}