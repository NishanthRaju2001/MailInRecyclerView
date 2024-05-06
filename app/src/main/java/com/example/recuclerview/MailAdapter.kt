package com.example.recuclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recuclerview.databinding.ItemEmailBinding


class MailAdapter(private val mails: List<MailDetails>) :
    RecyclerView.Adapter<MailAdapter.MailViewHolder>() {

    override fun getItemCount() = mails.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEmailBinding.inflate(layoutInflater, parent, false)
        return MailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        val data = mails[position]
        holder.bindData(mails[position])
        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MailDetailActivity::class.java)
            intent.putExtra("Data", data)
            context.startActivity(intent)
        }
    }

    inner class MailViewHolder(private val binding: ItemEmailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(mailDetails: MailDetails) {
            with(binding) {
                fromTV.text = mailDetails.sender
                subjectTV.text = mailDetails.title
                bodyTV.text = mailDetails.body

                timeTV.text = getTimeDifference(mailDetails.time)
            }
        }
    }

    private fun getTimeDifference(timeInMillis: Long): String {
        val currentTime = System.currentTimeMillis()
        val diffInMillis = currentTime - timeInMillis

        val seconds = diffInMillis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = weeks / 4
        val years = months / 12

        return when {
            years > 0 -> "$years year(s) ago"
            months > 0 -> "$months month(s) ago"
            weeks > 0 -> "$weeks week(s) ago"
            days > 0 -> "$days day(s) ago"
            hours > 0 -> "$hours hour(s) ago"
            minutes > 0 -> "$minutes minute(s) ago"
            else -> "Just now"
        }
    }
}
