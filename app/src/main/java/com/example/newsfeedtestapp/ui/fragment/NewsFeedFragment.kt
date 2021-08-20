package com.example.newsfeedtestapp.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeedtestapp.data.model.Hit
import com.example.newsfeedtestapp.databinding.FragmentNewsFeedBinding
import com.example.newsfeedtestapp.ui.adapter.NewsFeedAdapter
import com.example.newsfeedtestapp.ui.adapter.SwipeHelperCallback
import com.example.newsfeedtestapp.ui.custom.AppLoader
import com.example.newsfeedtestapp.ui.viewmodel.NewsFeedViewModel
import com.example.newsfeedtestapp.utils.toastLong
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFeedFragment : BaseFragment(), KoinComponent {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //DI
    private val newsFeedViewModel: NewsFeedViewModel by inject()
    private val appLoader: AppLoader by inject { parametersOf(requireActivity()) }

    //binding
    private var binding: FragmentNewsFeedBinding? = null

    //adapter callback
    lateinit var newsFeedAdapter: NewsFeedAdapter
    private var mItemTouchHelper: ItemTouchHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Observe viewmodel
        observeViewModel()
        // Inflate the layout for this fragment
        binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        loadNewsFeed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun loadNewsFeed() {
        newsFeedViewModel.getNewsFeedList()
    }

    private fun observeViewModel() = newsFeedViewModel.run {
        loadingState.observe(viewLifecycleOwner, Observer {
            //TODO: fix AppLoader!!!
            if (it) {
                if (!binding?.swRefreshNewsFeed?.isRefreshing!!)
                    appLoader.show(requireActivity())
            } else {
                appLoader.dismiss(requireActivity())
            }
        })
        newsList.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                //show toast? no news?
                requireActivity().toastLong("NO NEWS!!!")
            } else {
                //dismiss refresh layout loader
                binding?.swRefreshNewsFeed?.isRefreshing = false

                setUpNewsFeedAdapter()

                //initialize adapter data
                if (::newsFeedAdapter.isInitialized) {
                    newsFeedAdapter.setData(ArrayList(it))
                }

                //set up the refresh layout
                binding?.swRefreshNewsFeed?.setOnRefreshListener { loadNewsFeed() }

            }
        })
        itemDeleted.observe(viewLifecycleOwner, Observer {
            requireActivity().toastLong(it)
        })
        onError.observe(viewLifecycleOwner, Observer {
            //TODO: show toast with error!
            requireActivity().toastLong(it)
        })
        onConnError.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                //If it is not null, it means there's no internet, thus the data must be fetched from DB
                newsFeedViewModel.fetchNewsFeedList()
            }
        })
    }

    private fun NewsFeedViewModel.setUpNewsFeedAdapter() {
        //get recyclerview
        val rvNewsList = binding?.rvNewsList

        //Set up the adapter
        //Callback for onSwipe event is a placeholder, the actual swipe event takes place
        //in the SwipeHelperCallback
        newsFeedAdapter =
            NewsFeedAdapter(
                requireActivity().applicationContext,
                onClick = onItemClick,
                onSwiped = onSwipe
            )

        //set up adapter (cont'd)
        rvNewsList?.adapter = newsFeedAdapter
        rvNewsList?.layoutManager = LinearLayoutManager(context)

        //set up callback for swipe event
        val callback: ItemTouchHelper.Callback = SwipeHelperCallback(newsFeedAdapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper?.attachToRecyclerView(rvNewsList)
    }

    //Callback for onClick event
    private var onItemClick: (Hit) -> Unit = { hit ->
        val url = hit.url ?: hit.storyUrl
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireActivity(), Uri.parse(url))
    }

    //Callback for onSwipe event (remove the item from the recycler view AND deleting the entry from DB)
    private var onSwipe: (Hit) -> Unit = { hit ->
        newsFeedViewModel.deleteEntryFromNewsFeed(hit)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsFeedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFeedFragment().apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}