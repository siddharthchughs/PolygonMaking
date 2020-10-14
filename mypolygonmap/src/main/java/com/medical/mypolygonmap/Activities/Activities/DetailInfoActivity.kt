package com.medical.mypolygonmap.Activities.Activities

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.medical.mypolygonmap.R





class DetailInfoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvCoordinatesPositionOne: TextView
    private lateinit var tvCoordinatesPositionTwo: TextView
    private lateinit var tvCoordinatesPositionThree: TextView
    private lateinit var tvCoordinatesPositionFour: TextView
    private lateinit var imgBack:ImageView
    lateinit var list: ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_info)
        tvCoordinatesPositionOne = findViewById(R.id.tvCoordinateOne)
        tvCoordinatesPositionTwo = findViewById(R.id.tvCoordinateTwo)
        tvCoordinatesPositionThree = findViewById(R.id.tvCoordinateThree)
        tvCoordinatesPositionFour = findViewById(R.id.tvCoordinateFour)
        imgBack= findViewById(R.id.imgBack)
        imgBack.setOnClickListener(this)
        val testing= intent.getParcelableArrayListExtra<Parcelable>("list")
        tvCoordinatesPositionOne.text = "Coordinates of Position 1 : "+testing!!.get(0).toString()
        tvCoordinatesPositionTwo.text = "Coordinates of Position 2 : "+testing!!.get(1).toString()+"\n"
        tvCoordinatesPositionThree.text = "Coordinates of Position 3 : "+testing!!.get(2).toString()+"\n"
        tvCoordinatesPositionFour.text = "Coordinates of Position 4 : "+testing!!.get(3).toString()+"\n"
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when(id){
            R.id.imgBack->{
                onBackPressed()
            }
        }
    }
}