package karpenko.diploma.natifetestapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import karpenko.diploma.natifetestapp.R
import karpenko.diploma.natifetestapp.pojo.OriginalImage
import kotlinx.android.synthetic.main.item_gif_layout.view.*

class AllGifsAdapter(private val context: Context) :
    RecyclerView.Adapter<AllGifsAdapter.GifsViewHolder>() {

    var gifsList: List<OriginalImage> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onGifClickListener: OnGifClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gif_layout, parent, false)
        return GifsViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        val gif = gifsList[position]
        with(holder) {
            Glide.with(context).load(gif.url)
                .into(ivGif)
            itemView.setOnClickListener {
                onGifClickListener?.onGifClick(gif)
            }

        }

    }

    override fun getItemCount() = gifsList.size

    inner class GifsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGif = itemView.ivGif

    }

    interface OnGifClickListener {
        fun onGifClick(originalImage: OriginalImage)
    }

}