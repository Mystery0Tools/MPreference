package vip.mystery0.mpreference

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.AttributeSet
import android.util.Xml
import android.widget.LinearLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.xmlpull.v1.XmlPullParser
import vip.mystery0.mpreference.adapter.MPreferenceAdapter
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.*
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference
import java.io.InputStream

class MPreference : RecyclerView {
    private lateinit var rootMPreference: PageMPreference
    private lateinit var nowPageMPreference: PageMPreference
    private val showList = ArrayList<BaseMPreference>()
    private val config = MPreferenceConfig().init(context)
    private val adapter = MPreferenceAdapter(context, showList, config)

    private var tempPageMPreference: PageMPreference? = null

    private lateinit var handlerThread: HandlerThread

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        if (config.showDivider) addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        layoutManager = LinearLayoutManager(context)
        setAdapter(adapter)
        setEmptyMPreferenceClickListener()
        setBackgroundColor(config.backgroundColor)
    }

    fun parseAssertResource(fileName: String, addOneByOne: Boolean = true, parseInThread: Boolean = true) {
        parseInputStream(context.assets.open(fileName), addOneByOne, parseInThread)
    }

    fun parseInputStream(stream: InputStream, addOneByOne: Boolean = true, parseInThread: Boolean = true) {
        if (addOneByOne) itemAnimator = DefaultItemAnimator()
        if (parseInThread) parseInThread(stream)
        else {
            if (::handlerThread.isInitialized) handlerThread.quit()
            parse(stream)
            setList(rootMPreference.content, addOneByOne)
        }
    }

    private fun parseInThread(stream: InputStream, addOneByOne: Boolean = true) {
        if (!::handlerThread.isInitialized) handlerThread = HandlerThread("MPreferenceHandlerThread")
        handlerThread.start()
        val handler = object : Handler(handlerThread.looper) {
            override fun handleMessage(msg: Message?) {
                setList(rootMPreference.content, addOneByOne)
                handlerThread.quit()
            }
        }
        Thread {
            parse(stream)
            handler.sendEmptyMessage(0)
        }.start()
    }

    private fun parse(stream: InputStream) {
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
                        when (tagName) {
                            PageMPreference::class.java.simpleName -> {
                                val preference = parseAttribute(PageMPreference(), pullParser)
                                preference.root = tempPageMPreference
                                tempPageMPreference!!.next = preference
                                tempPageMPreference!!.content.add(preference)
                                tempPageMPreference = preference
                            }
                            CategoryMPreference::class.java.simpleName -> {
                                val preference = parseAttribute(CategoryMPreference(), pullParser)
                                preference.root = tempPageMPreference
                                tempPageMPreference!!.next = preference
                                tempPageMPreference!!.content.add(preference)
                                tempPageMPreference = preference
                            }
                            PageNextMPreference::class.java.simpleName -> {
                                val preference = parseAttribute(PageNextMPreference(), pullParser)
                                preference.root = tempPageMPreference
                                tempPageMPreference!!.next = preference
                                tempPageMPreference!!.content.add(preference)
                                tempPageMPreference = preference
                            }
                            else -> tempPageMPreference!!.content.add(getNode(pullParser))
                        }
                    }
                }
                XmlPullParser.END_TAG -> {
                    val tagName = pullParser.name
                    if (tagName == PageMPreference::class.java.simpleName || tagName == CategoryMPreference::class.java.simpleName) {
                        tempPageMPreference = tempPageMPreference?.root
                    }
                }
                XmlPullParser.END_DOCUMENT -> isEndDocument = true
            }
            eventType = pullParser.next()
        }
        nowPageMPreference = rootMPreference
    }

    private fun parseRootTag(pullParser: XmlPullParser) {
        val tagName = pullParser.name
        if (tagName != PageMPreference::class.java.simpleName && tagName != PageNextMPreference::class.java.simpleName) throw RuntimeException("root key must be ${PageMPreference::class.java.simpleName}!")
        rootMPreference = PageMPreference()
        tempPageMPreference = rootMPreference
    }

    private fun getNode(pullParser: XmlPullParser): BaseMPreference {
        val name = pullParser.name
        config.preferenceList.forEach {
            if (name == it.simpleName) return parseAttribute(it.newInstance(), pullParser)
        }
        throw ClassNotFoundException("cannot resolve node which named $name.")
    }

    private fun <T : BaseMPreference> add(clazz: Class<T>) {
        if (clazz.isAnnotationPresent(DeclareMPreference::class.java) && !config.preferenceList.contains(clazz)) config.preferenceList.add(clazz)
        else throw RuntimeException("the class not a annotation of ${DeclareMPreference::class.java.simpleName}.")
    }

    private fun <T : BaseMPreference> parseAttribute(base: T, pullParser: XmlPullParser): T {
        for (i in 0 until pullParser.attributeCount) {
            base.parseAttribute(context, pullParser.getAttributeName(i), pullParser.getAttributeValue(i), config)
        }
        return base
    }

    fun back(): Boolean {
        if (!::nowPageMPreference.isInitialized) throw RuntimeException("nowPageMPreference is not initialized.")
        if (nowPageMPreference.root == null) return false
        //当root不是PageMPreference时循环
        while (nowPageMPreference.root is CategoryMPreference) nowPageMPreference = nowPageMPreference.root!!
        setList(nowPageMPreference.root!!.content)
        nowPageMPreference = nowPageMPreference.root!!
        return true
    }

    fun setList(array: Array<BaseMPreference>, addOneByOne: Boolean = true) = setList(array.asList(), addOneByOne)

    fun setList(list: List<BaseMPreference>, addOneByOne: Boolean = true) {
        showList.clear()
        adapter.notifyDataSetChanged()
        if (addOneByOne) {
            list.forEach {
                showList.add(it)
                adapter.notifyItemInserted(showList.size - 1)
            }
        } else {
            showList.addAll(list)
            adapter.notifyDataSetChanged()
        }
        setEmptyMPreferenceClickListener()
    }

    fun find(id: String): BaseMPreference = find(id, rootMPreference.content)
        ?: throw NullPointerException("cannot find preference. id: $id")

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

    fun setOnClickListener(listener: (BaseMPreference) -> Unit) = setOnClickListener(showList, listener)

    /**
     * 递归设置点击事件
     */
    private fun setOnClickListener(list: List<BaseMPreference>, listener: (BaseMPreference) -> Unit) {
        list.forEach { base ->
            when (base) {
                //Category必须在Page前面，因为二者有继承关系
                is CategoryMPreference -> setOnClickListener(base.content, listener)
                is PageMPreference -> setOnPageMPreferenceClickListener(base)
                else -> base.setOnMPreferenceClickListener { listener.invoke(it) }
            }
        }
    }

    fun setOnValueChangeListener(listener: (BaseMPreference) -> Unit) = showList.forEach { base -> base.setOnMPreferenceChangeListener { listener.invoke(base) } }

    private fun setEmptyMPreferenceClickListener() = setOnClickListener { }

    private fun setOnPageMPreferenceClickListener(baseMPreference: PageMPreference) {
        baseMPreference.setOnMPreferenceClickListener {
            setList(baseMPreference.content)
            nowPageMPreference = baseMPreference
        }
    }
}