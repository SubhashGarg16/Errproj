package eu.welfare.newneeds.ui.home.quotes

import com.xwray.groupie.databinding.BindableItem
import eu.welfare.newneeds.R
import eu.welfare.newneeds.data.db.entities.Quote
import eu.welfare.newneeds.databinding.ItemQuoteBinding

class QuoteItem(
    private val quote : Quote
) : BindableItem<ItemQuoteBinding>() {

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)

    }

    override fun getLayout() = R.layout.item_quote
}