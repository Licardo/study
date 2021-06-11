package com.liepu.fluttermix

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.addItemDecoration(CustomItemDecoration())
        recyclerView.adapter = MyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = CustomItemAnimator()




        val asyncLayout = AsyncLayoutInflater(this)
        asyncLayout.inflate(R.layout.activity_main, recyclerView) { view, resid, parent ->
            print("")
        }


        LayoutInflater.from(this).factory2 = object : LayoutInflater.Factory2 {
            override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
                return null
            }

            override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
                val count = attrs.attributeCount
                for (index in 0..count) {
                    print(">>>${attrs.getAttributeName(index)}")
                }
                return null
            }
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }



    class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val text: TextView = TextView(parent.context)
            return MyViewHolder(text)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.text.text = "kotlin"
        }

        override fun getItemCount(): Int {
            return 20
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView as TextView
    }
}