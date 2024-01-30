package com.example.quizapp.activities.activities.activities

import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizapp.R
import com.example.quizapp.activities.activities.adapters.QuizAdapter
import com.example.quizapp.activities.activities.models.Quiz
import com.example.quizapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects


class MainActivity : AppCompatActivity() {

    lateinit var firestore:FirebaseFirestore
    private lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        populateDummyData()
        setUpViews()

    }

    private fun populateDummyData() {
        quizList.add(Quiz(id = "12-10-2023", title = "12-10-2023"))
        quizList.add(Quiz(id = "13-10-2023", title = "13-10-2023"))
        quizList.add(Quiz(id = "14-10-2023", title = "14-10-2023"))
        quizList.add(Quiz(id = "15-10-2023", title = "15-10-2023"))
        quizList.add(Quiz(id = "16-10-2023", title = "16-10-2023"))
        quizList.add(Quiz(id = "17-10-2023", title = "17-10-2023"))
        quizList.add(Quiz(id = "12-10-2023", title = "12-10-2023"))
        quizList.add(Quiz(id = "13-10-2023", title = "13-10-2023"))
        quizList.add(Quiz(id = "14-10-2023", title = "14-10-2023"))
        quizList.add(Quiz(id = "15-10-2023", title = "15-10-2023"))
        quizList.add(Quiz(id = "16-10-2023", title = "16-10-2023"))
        quizList.add(Quiz(id = "17-10-2023", title = "17-10-2023"))
    }

    private fun setUpViews() {
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecyclerView()
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error == null){
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()


        }


    }

    private fun setUpRecyclerView() {
         var adapter = QuizAdapter(this, quizList)
        binding.quizRecyclerView.layoutManager = GridLayoutManager(this,2)
        binding.quizRecyclerView.adapter = adapter
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(binding.appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.mainDrawer,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}