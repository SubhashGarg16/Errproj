package eu.welfare.newneeds.ui.home.quotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import eu.welfare.newneeds.R
import eu.welfare.newneeds.data.db.entities.Quote
import eu.welfare.newneeds.util.Coroutines
import eu.welfare.newneeds.util.hide
import eu.welfare.newneeds.util.show
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class QuotesFragment : Fragment(),KodeinAware {
    private var rc : RecyclerView?=null
    private var pb : ProgressBar?=null

    override val kodein by kodein()

    private lateinit var viewModel: QuotesViewModel
    private val factory : QuotesViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         rc  = container?.findViewById(R.id.recyclerview)
         pb = container?.findViewById(R.id.progress_bar)
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)
        // TODO: Use the ViewModel


        bindUI()


        /* Coroutines.main {
            val quotes =  viewModel.quotes.await()
             quotes.observe(viewLifecycleOwner, Observer {
                    context?.toast(it.size.toString())
             })
         }*/
    }


    private  fun bindUI() = Coroutines.main {
      //  pb?.show()
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        Log.i("Test Before","test")
        viewModel.quotes.await().observe(viewLifecycleOwner, Observer {
            Log.i("Test after","test")
        //    pb?.hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(toQuoteItem: List<QuoteItem>) {
        val madapter = GroupAdapter<ViewHolder>().apply {
            addAll(toQuoteItem)
            Log.i("Qutoe Items",toQuoteItem.toString())
        }

        rc?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=madapter

        }


    }

    private fun List<Quote>.toQuoteItem() : List<QuoteItem>{
        return this.map {
            QuoteItem(it)
        }
    }

}