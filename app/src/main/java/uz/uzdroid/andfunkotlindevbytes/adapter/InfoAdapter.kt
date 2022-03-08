package uz.uzdroid.andfunkotlindevbytes.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.uzdroid.andfunkotlindevbytes.databinding.InfoItemBinding
import uz.uzdroid.andfunkotlindevbytes.model.Info

class InfoAdapter : ListAdapter<Info, InfoAdapter.ViewHolder>(IfoComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = InfoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))

    }

    class ViewHolder(val binding: InfoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(info: Info) {
            binding.apply {
                Glide.with(imgPhoto.context)
                    .load(info.thumbnail)
                    .into(imgPhoto)

                tvTitle.text = info.title
                tvDescription.text = info.description
                tvTime.text = info.updated
            }
        }

    }

    class IfoComparator : DiffUtil.ItemCallback<Info>() {

        override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean {
            return oldItem.description == newItem.description &&
                    oldItem.thumbnail == newItem.thumbnail &&
                    oldItem.title == newItem.title &&
                    oldItem.updated == newItem.updated
        }

    }

}