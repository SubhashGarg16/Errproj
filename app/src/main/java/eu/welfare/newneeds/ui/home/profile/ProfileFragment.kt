package eu.welfare.newneeds.ui.home.profile

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import eu.welfare.myapplication.ui.PostItem
import eu.welfare.newneeds.R
import eu.welfare.newneeds.databinding.ProductsFragmentBinding
import eu.welfare.newneeds.databinding.ProfileFragmentBinding
import eu.welfare.newneeds.ui.adapterClasses.PostsAdapter
import eu.welfare.newneeds.ui.adapterClasses.imageSliderAdapter
import eu.welfare.newneeds.ui.home.products.products
import eu.welfare.newneeds.util.toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class ProfileFragment() : Fragment(),KodeinAware {
    override val kodein by kodein()
    private val factory : ProfileViewModelFactory by instance()

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val colorDrawable = ColorDrawable(Color.parseColor("#0F9D58"))
        // Now we need ProfileViewModelFactory as we need to pass repository as arguments
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        val binding: ProfileFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)

        binding.viewmodel = viewModel

        // As we are binding liveData to our layout so we require a lifecycle ownwer
        binding.lifecycleOwner = this
        getActivity()?.let { populateImages(it.applicationContext, binding) };
        binding.pager2.setOnClickListener{
            viewProducts(it)
        }
        return binding.root
       // return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    fun populateImages(ctx: Context, binding: ProfileFragmentBinding)
    {
        val job : Job = GlobalScope.launch {

            var imgs: List<Int> = listOf<Int>(R.drawable.vegetable4)
            var adapter = imageSliderAdapter(imgs, ctx)
            binding.pager.adapter = adapter

            var imgs1: List<Int> = listOf<Int>(R.drawable.banner2)
            var adapter1 = imageSliderAdapter(imgs1, ctx)
            binding.pager1.adapter = adapter1

            var imgs2: List<Int> = listOf<Int>(R.drawable.banner1)
            var adapter2 = imageSliderAdapter(imgs2, ctx)
            binding.pager2.adapter = adapter2

            val postsRecyclerView: RecyclerView = binding.postsRecyclerView
            postsRecyclerView.layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )

            val postItems: MutableList<PostItem> = mutableListOf()
            postItems.add(PostItem("Milk", R.drawable.milk))
            postItems.add(PostItem("Vegetables", R.drawable.vegetables))
            postItems.add(PostItem("Fruits", R.drawable.fruits))
            postItems.add(PostItem("Snacks", R.drawable.snacks))

            postsRecyclerView.adapter = PostsAdapter(ctx, postItems)




        }
    }

    fun viewProducts(view: View){
       context?.toast("Hello")
        val fragment = products();
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.profileFragment, fragment)
        fragmentTransaction?.commit();




    }
}