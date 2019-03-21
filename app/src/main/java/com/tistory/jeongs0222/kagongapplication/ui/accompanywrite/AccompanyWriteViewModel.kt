package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.dump.category.CategoryItem
import com.tistory.jeongs0222.kagongapplication.model.repository.AccompanyWriteRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.tistory.jeongs0222.kagongapplication.utils.uid
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AccompanyWriteViewModel(private val accompanyWriteRepository: AccompanyWriteRepository): DisposableViewModel(), AccompanyWriteEventListener {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _confirmClick = SingleLiveEvent<Any>()
    val confirmClick: LiveData<Any>
        get() = _confirmClick

    private val _userNickName = MutableLiveData<String>()
    val userNickname: LiveData<String>
        get() = _userNickName

    private val _area = MutableLiveData<String>()
    val area: LiveData<String>
        get() = _area

    private val _categoryList = MutableLiveData<MutableList<CategoryItem>>()
    val categoryList : LiveData<MutableList<CategoryItem>>
        get() = _categoryList

    private val _recyclerVisibility = MutableLiveData<Int>()
    val recyclerVisibility: LiveData<Int>
        get() = _recyclerVisibility

    private val _calendarVisibility = MutableLiveData<Int>()
    val calendarVisibility: LiveData<Int>
        get() = _calendarVisibility

    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String>
        get() = _selectedCategory

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String>
        get() = _selectedDate


    private lateinit var intentProvider: IntentProvider

    init {
        _recyclerVisibility.value = 1
        _calendarVisibility.value = 1

        bringNickname()
        categoryPreprocessor()
    }

    private fun categoryPreprocessor() {
        val list = arrayListOf("종일동행", "반일동행", "식사", "술", "기타")
        val categoryItem: MutableList<CategoryItem> = arrayListOf()

        for(i in 0 until list.size) {
            categoryItem.add(CategoryItem(list[i]))
        }

        _categoryList.value = categoryItem
    }

    fun bind(area: String, intentProvider: IntentProvider) {
        _area.value = "#$area"
        this.intentProvider = intentProvider
    }

    fun bringNickname() {
        accompanyWriteRepository.bringNickname(uid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _userNickName.value = it.nickname
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun calendarSelected(date: String) {
        _selectedDate.value = date

        _calendarVisibility.value = 1
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun confirmClickEvent() {
        _confirmClick.call()
    }

    fun categoryClickEvent() {
        _recyclerVisibility.value = 0
    }

    fun dateClickEvent() {
        _calendarVisibility.value = 0

    }

    override fun categoryItemClickEvent(category: String) {
        _selectedCategory.value = category

        _recyclerVisibility.value = 1
    }

}

interface AccompanyWriteEventListener {
    fun categoryItemClickEvent(category: String)
}