package com.example.recuclerview

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recuclerview.databinding.ActivityMainBinding
import com.example.recuclerview.decorators.CustomBackgroundItemDecorator
import com.example.recuclerview.decorators.FirstNLastItemDecorator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mailAdapter: MailAdapter
    private val mailList = ArrayList<MailDetails>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        prepareDataSet()
    }
    private fun prepareDataSet(){
        val currentTime = System.currentTimeMillis()
        mailList.apply {
            add(
                MailDetails(
                    sender = "Amc Theaters",
                    title ="Tickets are booked",
                    body = "Your tickets are booked for this movie",
                    time =currentTime - (10 * 60 * 1000)
                )
            )
            add(
                MailDetails(
                    sender = "Gmail",
                    title ="Password Updated",
                    body = "Your password is updated",
                    time =currentTime - (2 * 60 * 60 * 1000)
                )
            )
            add(
                MailDetails(
                    sender = "Apolis",
                    title ="Job Interview",
                    body = "You have an interview tomorrow",
                    time =currentTime - (2 * 24 * 60 * 60 * 1000)
                )
            )
            add(
                MailDetails(
                    sender = "Ram",
                    title ="Attaching the required docs",
                    body = "Here is the documents you asked for",
                    time =currentTime - (7 * 24 * 60 * 60 * 1000)
                )
            )
        }
        mailAdapter  = MailAdapter(mailList)
        with(binding){
            recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
            recyclerView.adapter=mailAdapter
            ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    mailList.removeAt(position)
                    mailAdapter.notifyItemRemoved(position)
                    Toast.makeText(this@MainActivity,"Deleted",Toast.LENGTH_LONG).show()
                }

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }
            }).attachToRecyclerView(recyclerView)
            btnLinear.setOnClickListener {
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = mailAdapter
            }

            btnGrid.setOnClickListener {
                recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                recyclerView.adapter = mailAdapter
            }

            btnStaggered.setOnClickListener {
                recyclerView.layoutManager = StaggeredGridLayoutManager(2, 0)
                recyclerView.adapter = mailAdapter
            }

            btnHorizontal.setOnClickListener {
                recyclerView.layoutManager =
                    LinearLayoutManager(
                        this@MainActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                recyclerView.adapter = mailAdapter
            }

            btnDecorator2.setOnClickListener {
                val spaceItemDecoration = FirstNLastItemDecorator(
                    resources.getDimensionPixelSize(R.dimen._50dp),
                    resources.getDimensionPixelSize(R.dimen._10dp)
                )
                recyclerView.layoutManager =
                    LinearLayoutManager(this@MainActivity)
                recyclerView.addItemDecoration(spaceItemDecoration)
                recyclerView.adapter = mailAdapter
            }

            btnDecorator3.setOnClickListener {
                val uiDecorator = CustomBackgroundItemDecorator(
                    ContextCompat.getDrawable(this@MainActivity, R.drawable.progress)!!,
                    resources.getDimensionPixelSize(R.dimen._50dp),
                    resources.getDimensionPixelSize(R.dimen._50dp)
                )
                recyclerView.layoutManager =
                    LinearLayoutManager(this@MainActivity)
                recyclerView.addItemDecoration(uiDecorator)
                recyclerView.adapter = mailAdapter
            }

            btnDecorator3.setOnClickListener {
                val uiDecorator = CustomBackgroundItemDecorator(
                    ContextCompat.getDrawable(this@MainActivity, R.drawable.progress)!!,
                    resources.getDimensionPixelSize(R.dimen._50dp),
                    resources.getDimensionPixelSize(R.dimen._50dp)
                )
                recyclerView.layoutManager =
                    LinearLayoutManager(this@MainActivity)
                recyclerView.addItemDecoration(uiDecorator)
                recyclerView.adapter = mailAdapter
            }

            btnDecorator3.setOnClickListener {
                val uiDecorator = CustomBackgroundItemDecorator(
                    ContextCompat.getDrawable(this@MainActivity, R.drawable.progress)!!,
                    resources.getDimensionPixelSize(R.dimen._50dp),
                    resources.getDimensionPixelSize(R.dimen._50dp)
                )
                recyclerView.layoutManager =
                    LinearLayoutManager(this@MainActivity)
                recyclerView.addItemDecoration(uiDecorator)
                recyclerView.adapter = mailAdapter
            }

            btnAnim.setOnClickListener {

                recyclerView.layoutManager =
                    LinearLayoutManager(this@MainActivity)
                val animation: LayoutAnimationController =
                    AnimationUtils.loadLayoutAnimation(this@MainActivity, R.anim.layout_animation)
                recyclerView.layoutAnimation = animation
                recyclerView.adapter = mailAdapter
            }

        }
    }
}