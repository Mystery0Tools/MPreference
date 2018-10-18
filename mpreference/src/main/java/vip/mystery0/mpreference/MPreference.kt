package vip.mystery0.mpreference

import android.content.Context
import android.util.AttributeSet
import android.util.Xml
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.xmlpull.v1.XmlPullParser
import vip.mystery0.mpreference.adapter.MPreferenceAdapter
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.PageMPreference
import vip.mystery0.mpreference.impl.SwitchMPreference
import vip.mystery0.mpreference.impl.TextMPreference
import java.io.InputStream

class MPreference : RecyclerView {
    private val originList = ArrayList<BaseMPreference>()
    private val showList = ArrayList<BaseMPreference>()
    private val adapter = MPreferenceAdapter(context, showList)
    private val config = MPreferenceConfig()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        if (config.showDivider)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        setAdapter(adapter)
    }

    fun parseResource(fileName: String) {
        parseInputStream(context.assets.open(fileName))
    }

    fun parseInputStream(stream: InputStream) {
        var list: ArrayList<BaseMPreference>? = null
        val pullParser = Xml.newPullParser()
        pullParser.setInput(stream, "UTF-8")
        var eventType = pullParser.eventType
        var isEndDocument = false
        while (!isEndDocument) {
            when (eventType) {
                XmlPullParser.START_DOCUMENT -> isEndDocument = false
                XmlPullParser.START_TAG -> {
                    if (list == null) {
                        val tagName = pullParser.name
                        if (tagName != PageMPreference::class.java.simpleName)
                            throw RuntimeException("root key must be PageMPreference")
                        list = ArrayList()
                    } else {
                        list.add(getNode(pullParser))
                    }
                }
                XmlPullParser.END_DOCUMENT -> isEndDocument = true
            }
            eventType = pullParser.next()
        }
        setList(list!!)
    }

    private fun getNode(pullParser: XmlPullParser): BaseMPreference =
        when (pullParser.name) {
            PageMPreference::class.java.simpleName -> {
                val pageMPreference = PageMPreference()
                for (i in 0 until pullParser.attributeCount) {
                    pageMPreference.parseAttribute(pullParser.getAttributeName(i), pullParser.getAttributeValue(i))
                }
                pageMPreference
            }
            SwitchMPreference::class.java.simpleName -> {
                val switchMPreference = SwitchMPreference()
                for (i in 0 until pullParser.attributeCount) {
                    switchMPreference.parseAttribute(pullParser.getAttributeName(i), pullParser.getAttributeValue(i))
                }
                switchMPreference
            }
            TextMPreference::class.java.simpleName->{
                val textMPreference = TextMPreference()
                for (i in 0 until pullParser.attributeCount) {
                    textMPreference.parseAttribute(pullParser.getAttributeName(i), pullParser.getAttributeValue(i))
                }
                textMPreference
            }
            else -> throw ClassNotFoundException("cannot resolve node which named ${pullParser.name}")
        }

    fun setList(array: Array<BaseMPreference>) = setList(array.asList())

    fun setList(list: List<BaseMPreference>) {
        originList.clear()
        originList.addAll(list)
        adapter.notifyDataSetChanged()
    }
}