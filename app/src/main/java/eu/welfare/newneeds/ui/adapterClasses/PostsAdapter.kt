package eu.welfare.newneeds.ui.adapterClasses


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelLazy
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import eu.welfare.myapplication.ui.PostItem
import eu.welfare.newneeds.R
import eu.welfare.newneeds.ui.home.products.products
import eu.welfare.newneeds.util.getSupportFragmentManager
import eu.welfare.newneeds.util.toast


class PostsAdapter internal constructor(
    private val context: Context,
    private val postItems: List<PostItem>
) :
    RecyclerView.Adapter<PostsAdapter.ListViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postImageView: RoundedImageView = itemView.findViewById(R.id.imagePost)

        fun setPostImage(postItem: PostItem) {
            postImageView.setImageResource(postItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = inflater.inflate(R.layout.post_item_container, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.setPostImage(postItems[position])
        // implement setOnClickListener event on item view.
        val animal: PostItem = postItems.get(position)
        context.toast(postItems[position].toString())
        holder.itemView.setOnClickListener {
            // open another activity on item click`
            context.toast(postItems[position].name.toString())
            val nextFrag = products()
//            val fragmentManager: FragmentManager  = getFragmentManager()

val view:ViewGroup = it as ViewGroup
            inflater.inflate(R.layout.products_fragment, view);
        }
    }

    override fun getItemCount(): Int {
        return postItems.size
    }


}
