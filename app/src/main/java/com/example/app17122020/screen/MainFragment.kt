package com.example.app17122020.screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app17122020.ItemsAdapter
import com.example.app17122020.MainActivity2
import com.example.app17122020.R
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    val itemsAdapter = ItemsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareList()
        prepareShit3()

        itemsAdapter.listItems = listOf(
            "START", "Hello", "World", "bla",
            "tra tat", "some text",
            "some another text", "ohh hoh",
            "yo pop", "bebe", "jojo", "END"
        )
    }

    private fun prepareList() {
        rvItems.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = itemsAdapter
        }
    }

//    private fun prepareShit1() {
//        val activity = activity as? MainActivity ?: return
//        val viewHeight = resources.getDimension(R.dimen.test_view_height).toInt()
//        val view = View(activity).apply {
//            setBackgroundColor(ContextCompat.getColor(activity, R.color.teal_700))
//            layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                viewHeight)
//        }
//
//        activity.additionalFragmentWrapper.addView(view)
//    }

//    private fun prepareShit2() {
//        val activity = activity as? MainActivity ?: return
//        val view = LayoutInflater.from(activity).inflate(R.layout.test, activity.additionalFragmentWrapper, false)
//        activity.additionalFragmentWrapper.addView(view)
//    }

    private fun prepareShit3() {
        inputFrame.addOnLayoutChangeListener { view, left, top, right, bottom, oldLeft,
                                            oldTop, oldRight, oldBottom ->

            val mainActivity2 = activity as? MainActivity2 ?: return@addOnLayoutChangeListener

            val additionalWrapper = mainActivity2.additional_nav_host_fragment
            val additionalWrapperParams = additionalWrapper.layoutParams as ViewGroup.MarginLayoutParams


            val oldHeight = oldBottom - oldTop
            val newHeight = bottom - top

            val oldMargin = additionalWrapperParams.bottomMargin
            val oldPadding = rvItems.paddingBottom

            Log.d("TAG", "--------- \n")
            Log.d("TAG", "oldHeight = $oldHeight")
            Log.d("TAG", "newHeight = $newHeight")
            Log.d("TAG", "oldMargin = $oldMargin")

            if(oldHeight in 1 until newHeight) {
                val newMargin = oldMargin + (newHeight - oldHeight)
                val newPadding = oldPadding + (newHeight - oldHeight)
                additionalWrapperParams.bottomMargin = newMargin
                rvItems.setPadding(0, 0, 0, newPadding)
                Log.d("TAG", "newMargin (+) = $newMargin")
            }

            if(newHeight < oldHeight && oldHeight > 0) {
                val newMargin = oldMargin - (oldHeight - newHeight)
                val newPadding = oldPadding - (oldHeight - newHeight)
                additionalWrapperParams.bottomMargin = newMargin
                rvItems.setPadding(0, 0, 0, newPadding)
                Log.d("TAG", "newMargin (-) = $newMargin")
            }
        }
    }

}