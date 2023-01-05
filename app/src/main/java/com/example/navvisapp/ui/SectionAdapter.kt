package com.example.navvisapp.ui


import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navvisapp.databinding.NumberItemBinding
import com.example.navvisapp.utils.Constants
import java.util.*


class SectionAdapter() : RecyclerView.Adapter<SectionAdapter.NumberViewHolder>() {
    var mutableSet = mutableSetOf<String>()
    private var parentMap = TreeMap<String, ArrayList<Pair<Int, String>>>()
    private var context: Application? = null

    constructor(
        context: Application,
        list: TreeMap<String, ArrayList<Pair<Int, String>>>
    ) : this() {
        parentMap = list
        this.context = context
        list.putAll(list.mapKeys {
            it.key
        })
        mutableSet = list.keys
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val binding = NumberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val list = mutableSet.elementAt(position)
        // Set item views based on your views and data model
        list.let {
            holder.bind(it)
        }
    }

    inner class NumberViewHolder(private val binding: NumberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sectionName: String) {
            binding.title.text = sectionName
            val childList = parentMap.get(sectionName)
            if (childList != null) {
                var cnt =childList.size
                for (item in childList.sortedByDescending { it.first }) {
                    cnt--
                    val v: View = (context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                            com.example.navvisapp.R.layout.child_item,
                            null
                        )
                    (v.findViewById(com.example.navvisapp.R.id.childItemName) as TextView).text = item.second.split("+").first()
                    isCheckMark(item, v)
                    setBackground(cnt, v,childList.size)
                    binding.llChildItem.addView(
                        v,
                        0,
                        ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    )

                }
            }


        }

    }

    private fun setBackground(cnt: Int, v: View, childListSize :Int) {
        if (cnt == 0 && childListSize==1) {
            (v.findViewById(com.example.navvisapp.R.id.childItemView) as View).visibility = View.INVISIBLE
        } else {
            if(cnt==childListSize-1){
                (v.findViewById(com.example.navvisapp.R.id.childItemView) as View).visibility = View.INVISIBLE
            }else {
                (v.findViewById(com.example.navvisapp.R.id.childItemView) as View).visibility = View.VISIBLE
            }
        }
    }

    private fun isCheckMark(item: Pair<Int, String>, v: View) {
        if (item.second.split("+").last() == Constants.CHECKED) {
            (v.findViewById(com.example.navvisapp.R.id.childItemName) as TextView).setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                com.example.navvisapp.R.drawable.ic_check,
                0
            )
        } else {
            (v.findViewById(com.example.navvisapp.R.id.childItemName) as TextView).setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                0,
                0
            )
        }
    }

    override fun getItemCount(): Int {
        return mutableSet.size

    }


}


