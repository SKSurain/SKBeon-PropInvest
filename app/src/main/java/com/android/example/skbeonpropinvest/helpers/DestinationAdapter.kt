package com.android.example.skbeonpropinvest.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.skbeonpropinvest.R
import com.android.example.skbeonpropinvest.models.Property
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

private lateinit var view: View

class DestinationAdapter(private val propertyList: List<Property>) :
    RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.property = propertyList[position]
        holder.tvName.text = propertyList[position].name
        holder.tvPrice.text = "RM " + propertyList[position].price
        holder.tvMedian.text = "Median Price : RM " + propertyList[position].median

        val imageView = propertyList[position].image

        val options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(view).load(imageView).apply(options).into(holder.ivObject);

//		holder.itemView.setOnClickListener { v ->
//			val context = v.context
//			val intent = Intent(context, DestinationDetailActivity::class.java)
//			intent.putExtra(DestinationDetailActivity.ARG_ITEM_ID, holder.property!!.id)
//
//			context.startActivity(intent)
//		}
    }

    override fun getItemCount(): Int {
        return propertyList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.tvName)
        var property: Property? = null
        var ivObject: ImageView = itemView.findViewById(R.id.ivIcons)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        var tvMedian: TextView = itemView.findViewById(R.id.tvMedian)

        override fun toString(): String {
            return """${super.toString()} '${tvName.text}'"""
        }
    }
}
