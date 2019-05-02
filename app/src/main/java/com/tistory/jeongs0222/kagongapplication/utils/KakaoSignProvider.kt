package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.*
import android.widget.*
import com.kakao.auth.AuthType
import com.kakao.auth.KakaoSDK
import com.kakao.auth.Session
import com.kakao.usermgmt.R
import com.tistory.jeongs0222.kagongapplication.ui.login.LoginActivity
import java.util.*


interface KakaoSignProvider {
    fun kakaoLogin()
}

class KakaoSignProviderImpl(private val activity: Activity): KakaoSignProvider {

    override fun kakaoLogin() {
        val authTypes = getAuthTypes()

        if (authTypes.size == 1) {
            Session.getCurrentSession().open(authTypes[0], activity)
        } else {
            val authItems = createAuthItemArray(authTypes)
            val adapter = createLoginAdapter(authItems)
            val dialog = createLoginDialog(authItems, adapter)

            dialog.show()
        }
    }

    private fun getAuthTypes(): List<AuthType> {
        val availableAuthTypes = ArrayList<AuthType>()

        if (Session.getCurrentSession().authCodeManager.isTalkLoginAvailable) {
            availableAuthTypes.add(AuthType.KAKAO_TALK)
        }

        availableAuthTypes.add(AuthType.KAKAO_ACCOUNT)

        var authTypes: Array<AuthType>? = KakaoSDK.getAdapter().sessionConfig.authTypes
        if (authTypes == null || authTypes.isEmpty() || authTypes.size == 1 && authTypes[0] == AuthType.KAKAO_LOGIN_ALL) {
            authTypes = AuthType.values()
        }
        availableAuthTypes.retainAll(Arrays.asList(*authTypes))

        if (availableAuthTypes.size == 0) {
            availableAuthTypes.add(AuthType.KAKAO_ACCOUNT)
        }

        return availableAuthTypes
    }

    private fun createAuthItemArray(authTypes: List<AuthType>): Array<Item> {
        val itemList = ArrayList<Item>()
        if (authTypes.contains(AuthType.KAKAO_TALK)) {
            itemList.add(
                Item(
                    R.string.com_kakao_kakaotalk_account,
                    R.drawable.talk,
                    R.string.com_kakao_kakaotalk_account_tts,
                    AuthType.KAKAO_TALK
                )
            )
        }
        if (authTypes.contains(AuthType.KAKAO_ACCOUNT)) {
            itemList.add(
                Item(
                    R.string.com_kakao_other_kakaoaccount,
                    R.drawable.account,
                    R.string.com_kakao_other_kakaoaccount_tts,
                    AuthType.KAKAO_ACCOUNT
                )
            )
        }

        return itemList.toTypedArray()
    }

    private fun createLoginAdapter(authItems: Array<Item>): ListAdapter {

        return object : ArrayAdapter<Item>(
            activity.applicationContext,
            android.R.layout.select_dialog_item,
            android.R.id.text1, authItems
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var convertView = convertView
                if (convertView == null) {
                    val inflater = context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    convertView = inflater.inflate(com.kakao.usermgmt.R.layout.layout_login_item, parent, false)
                }
                val imageView = convertView!!.findViewById<ImageView>(com.kakao.usermgmt.R.id.login_method_icon)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setImageDrawable(activity.resources.getDrawable(authItems[position].icon!!, context.theme))
                } else {
                    imageView.setImageDrawable(activity.resources.getDrawable(authItems[position].icon!!))
                }
                val textView = convertView.findViewById<TextView>(com.kakao.usermgmt.R.id.login_method_text)
                textView.setText(authItems[position].textId)
                return convertView
            }
        }
    }

    private fun createLoginDialog(authItems: Array<Item>, adapter: ListAdapter): Dialog {
        val dialog = Dialog(activity, R.style.LoginDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(com.kakao.usermgmt.R.layout.layout_login_dialog)
        if (dialog.window != null) {
            dialog.window!!.setGravity(Gravity.CENTER)
        }

        val listView = dialog.findViewById<ListView>(com.kakao.usermgmt.R.id.login_list_view)
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val authType = authItems[position].authType
            openSession(authType)
            dialog.dismiss()
        }

        val closeButton = dialog.findViewById<Button>(com.kakao.usermgmt.R.id.login_close_button)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        return dialog
    }

    private fun openSession(authType: AuthType) {
        Log.e("test", "test4")
        Session.getCurrentSession().open(authType, activity)
    }

    private class Item internal constructor(
        internal val textId: Int,
        val icon: Int?,
        internal val contentDescId: Int,
        internal val authType: AuthType
    )
}