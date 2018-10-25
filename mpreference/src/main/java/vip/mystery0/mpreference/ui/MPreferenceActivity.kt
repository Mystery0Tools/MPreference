package vip.mystery0.mpreference.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vip.mystery0.mpreference.R

import kotlinx.android.synthetic.main.activity_mpreference.*

open class MPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mpreference)
        setSupportActionBar(toolbar)
        preference.addOnTitleChangeListener {
            title = it
            toolbar.title = it
        }
    }
}
