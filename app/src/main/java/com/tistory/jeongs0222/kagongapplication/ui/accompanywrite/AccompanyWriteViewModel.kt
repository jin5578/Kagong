package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.dump.category.CategoryItem
import com.tistory.jeongs0222.kagongapplication.model.repository.AccompanyWriteRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat


class AccompanyWriteViewModel(private val accompanyWriteRepository: AccompanyWriteRepository) : DisposableViewModel(),
    AccompanyWriteEventListener {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _confirmClick = SingleLiveEvent<Any>()
    val confirmClick: LiveData<Any>
        get() = _confirmClick

    private val _howToUseClick = SingleLiveEvent<Any>()
    val howToUseClick: LiveData<Any>
        get() = _howToUseClick

    private val _userNickName = MutableLiveData<String>()
    val userNickname: LiveData<String>
        get() = _userNickName

    private val _area = MutableLiveData<String>()
    val area: LiveData<String>
        get() = _area

    private val _categoryList = MutableLiveData<MutableList<CategoryItem>>()
    val categoryList: LiveData<MutableList<CategoryItem>>
        get() = _categoryList

    private val _recyclerVisibility = MutableLiveData<Int>()
    val recyclerVisibility: LiveData<Int>
        get() = _recyclerVisibility

    private val _calendarVisibility = MutableLiveData<Int>()
    val calendarVisibility: LiveData<Int>
        get() = _calendarVisibility

    private val _linkVisibility = MutableLiveData<Int>()
    val linkVisibility: LiveData<Int>
        get() = _linkVisibility

    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String>
        get() = _selectedCategory

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String>
        get() = _selectedDate

    private val _selectedLink = MutableLiveData<String>()
    val selectedLink: LiveData<String>
        get() = _selectedLink


    private lateinit var intentProvider: IntentProvider
    private lateinit var messageProvider: MessageProvider

    private lateinit var userKey: String

    init {
        _recyclerVisibility.value = 1
        _calendarVisibility.value = 1
        _linkVisibility.value = 1

        categoryPreprocessor()
    }

    private fun bringNickname() {
        accompanyWriteRepository.bringNickname(userKey)
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

    private fun categoryPreprocessor() {
        val list = arrayListOf("종일동행", "반일동행", "식사", "술", "기타")
        val categoryItem: MutableList<CategoryItem> = arrayListOf()

        for (i in 0 until list.size) {
            categoryItem.add(CategoryItem(list[i]))
        }

        _categoryList.value = categoryItem
    }

    private fun sortPreprocessor(category: String): Int {
        return when (category) {
            "종일동행" -> 0

            "반일동행" -> 1

            "식사" -> 2

            "술" -> 3

            else -> 4
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun writtenTime(): String {
        val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm")

        return format1.format(System.currentTimeMillis())
    }

    fun bind(area: String, intentProvider: IntentProvider, messageProvider: MessageProvider, dbHelperProvider: DBHelperProvider) {
        _area.value = "#$area"
        this.intentProvider = intentProvider
        this.messageProvider = messageProvider

        userKey = dbHelperProvider.getDBHelper().getUserKey()

        bringNickname()
    }

    fun accompanyWrite(content: String) {
        accompanyWriteRepository
            .accompanyWrite(
                _area.value!!.substring(1),
                sortPreprocessor(_selectedCategory.value!!),
                userKey,
                content,
                writtenTime(),
                _selectedDate.value!!,
                _selectedLink.value!!
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.value == 0) {
                    intentProvider.intentFinish()
                } else {
                    messageProvider.toastMessage(it.message)
                }
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

    fun linkSelected(link: String) {
        _selectedLink.value = link

        _linkVisibility.value = 1
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun confirmClickEvent() {
        _confirmClick.call()
    }

    fun howToUseClickEvent() {
        _howToUseClick.call()
    }

    fun categoryClickEvent() {
        _recyclerVisibility.value = 0
    }

    fun dateClickEvent() {
        _calendarVisibility.value = 0
    }

    fun linkClickEvent() {
        _linkVisibility.value = 0
    }

    override fun categoryItemClickEvent(category: String) {
        _selectedCategory.value = category

        _recyclerVisibility.value = 1
    }

}

interface AccompanyWriteEventListener {
    fun categoryItemClickEvent(category: String)
}