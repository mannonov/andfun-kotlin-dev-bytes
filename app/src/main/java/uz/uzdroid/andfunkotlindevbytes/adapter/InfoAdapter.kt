package uz.uzdroid.andfunkotlindevbytes.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.uzdroid.andfunkotlindevbytes.databinding.InfoItemBinding
import uz.uzdroid.andfunkotlindevbytes.model.Info

class InfoAdapter() : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    var info: List<Info> = ArrayList<Info>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = InfoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.binding.imgPhoto.context)
            .load(info[position].thumbnail)
            .into(holder.binding.imgPhoto)

        holder.binding.tvTitle.text = info[position].title
        holder.binding.tvDescription.text = info[position].description
        holder.binding.tvTime.text = info[position].updated

    }

    override fun getItemCount(): Int {
        return info.size
    }

    class ViewHolder(val binding: InfoItemBinding) : RecyclerView.ViewHolder(binding.root)

}