package com.example.navvisapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.navvisapp.api.NavVisApi
import com.example.navvisapp.databinding.FragmentMainBinding
import com.example.navvisapp.utils.Constants.TAG
import com.example.navvisapp.utils.Helper
import com.example.navvisapp.utils.NetworkResult
import com.example.serverlibrary.Server
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var navVisApi: NavVisApi
    private val mainViewModel by viewModels<MainViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var sectionAdapter: SectionAdapter
    private var mapList: TreeMap<String, ArrayList<Pair<Int, String>>> = TreeMap()
    private var dataSourceHashMap: TreeMap<String, ArrayList<Pair<Int, String>>> = TreeMap()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        sectionAdapter = SectionAdapter(requireActivity().application, mapList)
        bindingObserver()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getNumbers()
        binding.numberList.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.numberList.adapter = sectionAdapter
    }

    private fun bindingObserver() {
        mainViewModel.numberResponseLiveDate.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    val list = it.data?.numbers
                    list?.let {
                        mapList = convertIntToBinary(list)
                        sectionAdapter = SectionAdapter(requireActivity().application, mapList)
                        binding.numberList.adapter = sectionAdapter
                    }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    private fun convertIntToBinary(numbers: List<Int>): TreeMap<String, ArrayList<Pair<Int, String>>> {
        for (num in numbers) {
            if (Helper.readIntBetween(num)) {
                val binaryResult = Server.convertDecimalToBinary(num.toLong())
                val sectionName = Server.getSectionName(binaryResult.subSequence(6, 8).toString())
                val itemValue =
                    Server.getItemValue(binaryResult.subSequence(1, 6).toString().toLong())
                val isCheckMark = Server.getCheckMarkResult(binaryResult.first().toString().toInt())
                if (!dataSourceHashMap.containsKey(sectionName)) {
                    dataSourceHashMap[sectionName] = ArrayList<Pair<Int, String>>()
                    dataSourceHashMap.getValue(sectionName).add(
                        Pair(
                            itemValue.substring(itemValue.length - 1).toInt(),
                            "$itemValue+$isCheckMark"
                        )
                    )
                } else {
                    dataSourceHashMap.getValue(sectionName).add(
                        Pair(
                            itemValue.substring(itemValue.length - 1).toInt(),
                            "$itemValue+$isCheckMark"
                        )
                    )
                }
            }
        }
            return dataSourceHashMap

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}