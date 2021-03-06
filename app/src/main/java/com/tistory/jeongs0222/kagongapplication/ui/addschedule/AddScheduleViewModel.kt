package com.tistory.jeongs0222.kagongapplication.ui.addschedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.repository.AddScheduleRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*


class AddScheduleViewModel(private val addScheduleRepository: AddScheduleRepository) : DisposableViewModel(),
    AddScheduleEventListener {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _calendarList = MutableLiveData<MutableList<Any>>()
    val calendarList: LiveData<MutableList<Any>>
        get() = _calendarList

    private val _startPosition = MutableLiveData<Int>()
    val startPosition: LiveData<Int>
        get() = _startPosition

    private val _endPosition = MutableLiveData<Int>()
    val endPosition: LiveData<Int>
        get() = _endPosition

    private val _positionChange = MutableLiveData<Int>()
    val positionChange: LiveData<Int>
        get() = _positionChange

    private val _startDay = MutableLiveData<String>()

    private val _endDay = MutableLiveData<String>()

    private val _bothSelected = MutableLiveData<Boolean>()
    val bothSelected: LiveData<Boolean>
        get() = _bothSelected

    val tempStartDay: LiveData<String>
        get() = _startDay

    val tempEndDay: LiveData<String>
        get() = _endDay

    private lateinit var messageProvider: MessageProvider
    private lateinit var intentProvider: IntentProvider

    private lateinit var area: String

    private lateinit var userKey: String

    private val TAG = "AddScheduleViewModel"

    private val dateFormatter = DateFormatter()

    var mCenterPosition: Int = 0

    var lastStartPosition = 0
    var lastEndPosition = 0


    init {
        setCalendarList()

        _startPosition.value = 0
        _endPosition.value = 0
    }

    fun bind(messageProvider: MessageProvider, intentProvider: IntentProvider, dbHelperProvider: DBHelperProvider, area: String) {
        this.messageProvider = messageProvider
        this.intentProvider = intentProvider
        this.area = area

        userKey = dbHelperProvider.getDBHelper().getUserKey()
    }

    private fun setCalendarList() {
        val cal = GregorianCalendar()

        val calendarList = ArrayList<Any>()

        for (i in 0..12) {
            try {
                val calendar = GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0)

                if (i == 0) {
                    mCenterPosition = calendarList.size
                }

                calendarList.add(calendar.timeInMillis)

                val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
                val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                for (j in 0 until dayOfWeek) {
                    calendarList.add("empty")
                }

                for (j in 1..max) {
                    calendarList.add(GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        _calendarList.value = calendarList
    }

    private fun addSchedule() {
        addScheduleRepository.addSchedule(
            userKey,
            area,
            _startDay.value + " ~ " + _endDay.value
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 0) {
                    messageProvider.toastMessage(it.message)

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

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun selectDayClickEvent() {
        addSchedule()
    }

    override fun dayClickEvent(day: String, position: Int, gregorianCalendar: GregorianCalendar) {
        when {
            _startPosition.value == 0 -> {//시작 값이 없는 경우
                _startPosition.value = position
                _startDay.value =
                        dateFormatter.getDate(gregorianCalendar.timeInMillis, dateFormatter.CALENDAR_FULL_FORMAT)

                _bothSelected.value = false
            }

            _endPosition.value == 0 -> //시작 값은 있지만 끝 값이 없는 경우
                when {
                    position < _startPosition.value!! -> {     //시작 값이 있고 끝 값에 넣어야 하는 경우 중에 선택된 값이 시작 값 보다 작은 경우
                        _endPosition.value = _startPosition.value
                        _endDay.value = _startDay.value!!

                        _startPosition.value = position
                        _startDay.value = dateFormatter.getDate(
                            gregorianCalendar.timeInMillis,
                            dateFormatter.CALENDAR_FULL_FORMAT
                        )

                        _bothSelected.value = true
                    }

                    position == _startPosition.value!! -> //시작 값이 있고 끝 값에 넣어야 하는 경우 중에 선택된 값이 시작 값과 같은 경우
                        _bothSelected.value = false

                    else -> {//시작 값이 있고 끝 값에 넣어야 하는 경우 중에 시작 값 보다 작거나 같지 않은 경우
                        _endPosition.value = position
                        _endDay.value =
                                dateFormatter.getDate(
                                    gregorianCalendar.timeInMillis,
                                    dateFormatter.CALENDAR_FULL_FORMAT
                                )
                        _bothSelected.value = true
                    }
                }

            else -> {
                lastEndPosition = _endPosition.value!!
                _positionChange.value = 1

                lastStartPosition = _startPosition.value!!
                _positionChange.value = 0

                _startPosition.value = position
                _startDay.value =
                        dateFormatter.getDate(gregorianCalendar.timeInMillis, dateFormatter.CALENDAR_FULL_FORMAT)

                _endPosition.value = 0

                _bothSelected.value = false
            }
        }
    }
}

interface AddScheduleEventListener {
    fun dayClickEvent(day: String, position: Int, gregorianCalendar: GregorianCalendar)
}