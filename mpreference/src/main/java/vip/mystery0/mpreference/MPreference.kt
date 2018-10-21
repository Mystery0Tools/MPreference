package vip.mystery0.mpreference

import android.content.Context
import android.util.AttributeSet
import android.util.Xml
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.xmlpull.v1.XmlPullParser
import vip.mystery0.mpreference.adapter.MPreferenceAdapter
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.CheckBoxMPreference
import vip.mystery0.mpreference.impl.PageMPreference
import vip.mystery0.mpreference.impl.SwitchMPreference
import vip.mystery0.mpreference.impl.TextMPreference
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference
import java.io.InputStream

class MPreference : RecyclerView {
    private lateinit var rootMPreference: PageMPreference
    private lateinit var nowPageMPreference: PageMPreference
    private val showList = ArrayList<BaseMPreference>()
    private val config = MPreferenceConfig()
    private val adapter = MPreferenceAdapter(context, showList, config)

    private var tempPageMPreference: PageMPreference? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        add(CheckBoxMPreference::class.java)
        add(PageMPreference::class.java)
        add(SwitchMPreference::class.java)
        add(TextMPreference::class.java)
        init()
    }

    private fun init() {
        if (config.showDivider) addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        layoutManager = LinearLayoutManager(context)
        setAdapter(adapter)
        setEmptyMPreferenceClickListener()
    }

    fun parseAssertResource(fileName: String) {
        parseInputStream(context.assets.open(fileName))
    }

    fun parseInputStream(stream: InputStream) {
        val pullParser = Xml.newPullParser()
        pullParser.setInput(stream, "UTF-8")
        var eventType = pullParser.eventType
        var isEndDocument = false
        while (!isEndDocument) {
            when (eventType) {
                XmlPullParser.START_DOCUMENT -> isEndDocument = false
                XmlPullParser.START_TAG -> {
                    val tagName = pullParser.name
                    if (!::rootMPreference.isInitialized)//解析根标签
                        parseRootTag(pullParser)
                    else {
                        if (tagName == PageMPreference::class.java.simpleName) {
                            val preference = parseAttribute(PageMPreference(), pullParser)
                            preference.root = tempPageMPreference
                            tempPageMPreference!!.next = preference
                            tempPageMPreference!!.content.add(preference)
                            tempPageMPreference = preference
                        } else {
                            tempPageMPreference!!.content.add(getNode(pullParser))
                        }
                    }
                }
                XmlPullParser.END_TAG -> {
                    val tagName = pullParser.name
                    if (tagName == PageMPreference::class.java.simpleName) {
                        tempPageMPreference = tempPageMPreference?.root
                    }
                }
                XmlPullParser.END_DOCUMENT -> isEndDocument = true
            }
            eventType = pullParser.next()
        }
        nowPageMPreference = rootMPreference
        setList(rootMPreference.content)
    }

    private fun parseRootTag(pullParser: XmlPullParser) {
        val tagName = pullParser.name
        if (tagName != PageMPreference::class.java.simpleName) throw RuntimeException("root key must be PageMPreference")
        rootMPreference = PageMPreference()
        tempPageMPreference = rootMPreference
    }

    private fun getNode(pullParser: XmlPullParser): BaseMPreference {
        val name = pullParser.name
        config.mpreferenceList.forEach {
            if (name == it.simpleName) return parseAttribute(it.newInstance(), pullParser)
        }
        throw ClassNotFoundException("cannot resolve node which named $name")
    }

    private fun <T : BaseMPreference> add(clazz: Class<T>) {
        if (clazz.isAnnotationPresent(DeclareMPreference::class.java) && !config.mpreferenceList.contains(clazz)) config.mpreferenceList.add(clazz)
        else throw RuntimeException("the class not a annotation of DeclareMPreference")
    }

    private fun <T : BaseMPreference> parseAttribute(base: T, pullParser: XmlPullParser): T {
        for (i in 0 until pullParser.attributeCount) {
            base.parseAttribute(context, pullParser.getAttributeName(i), pullParser.getAttributeValue(i), config)
        }
        return base
    }

    fun back(): Boolean {
        if (!::nowPageMPreference.isInitialized) throw RuntimeException("nowPageMPreference is not initialized")
        if (nowPageMPreference.root == null) return false
        setList(nowPageMPreference.root!!.content)
        nowPageMPreference = nowPageMPreference.root!!
        return true
    }

    fun setList(array: Array<BaseMPreference>) = setList(array.asList())

    fun setList(list: List<BaseMPreference>) {
        showList.clear()
        showList.addAll(list)
        adapter.notifyDataSetChanged()
        setEmptyMPreferenceClickListener()
    }

    fun find(id: String): BaseMPreference = find(id, rootMPreference.content)
        ?: throw NullPointerException("cannot find preference called $id")

    private fun find(id: String, list: List<BaseMPreference>): BaseMPreference? {
        list.forEach {
            if (it.id == id) return it
            if (it is PageMPreference) {
                val get = find(id, it.content)
                if (get != null) return get
            }
        }
        return null
    }

    fun indexOf(baseMPreference: BaseMPreference): Int = showList.indexOf(baseMPreference)

    fun setOnItemClickListener(id: String, listener: (BaseMPreference) -> Unit) = find(id).setOnMPreferenceClickListener(listener)

    fun setOnItemValueChangeListener(id: String, listener: (BaseMPreference) -> Unit) = find(id).setOnMPreferenceChangeListener(listener)

    fun setOnClickListener(listener: (Int, BaseMPreference) -> Unit) {
        showList.forEachIndexed { index, base ->
            if (base is PageMPreference) setOnPageMPreferenceClickListener(base)
            else base.setOnMPreferenceClickListener { listener.invoke(index, it) }
        }
    }

    fun setOnValueChangeListener(listener: (Int, BaseMPreference) -> Unit) = showList.forEachIndexed { index, base -> base.setOnMPreferenceChangeListener { listener.invoke(index, base) } }

    private fun setEmptyMPreferenceClickListener() = setOnClickListener { _, _ -> }

    private fun setOnPageMPreferenceClickListener(baseMPreference: PageMPreference) {
        baseMPreference.setOnMPreferenceClickListener {
            setList(baseMPreference.content)
            nowPageMPreference = baseMPreference
        }
    }
}