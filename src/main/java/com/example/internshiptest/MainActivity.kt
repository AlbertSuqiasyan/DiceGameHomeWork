package com.example.internshiptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var diceImageView1: ImageView
    lateinit var diceImageView2: ImageView
    lateinit var diceImageView3: ImageView
    lateinit var diceImageView4: ImageView
    lateinit var playerScoreTextView1: TextView
    lateinit var playerScoreTextView2: TextView
    lateinit var winnerTextView: TextView
    var playerScore1: Int = 0
    var playerScore2: Int = 0
    var gameLimit = 30
    var currentPlayer = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        diceImageView1 = findViewById(R.id.diceImageView)
        diceImageView2 = findViewById(R.id.diceImageView1)
        diceImageView3 = findViewById(R.id.diceImageView3)
        diceImageView4 = findViewById(R.id.diceImageView4)
        playerScoreTextView1 = findViewById(R.id.playerTextView1)
        playerScoreTextView2 = findViewById(R.id.playerTextView2)
        winnerTextView = findViewById(R.id.winnerTextView)
    }

    private fun rollDices(): Pair<Int, Int> {
        var random: Int = Random.nextInt(6) + 1
        return when (random) {
            1 -> Pair(R.drawable.dice_1, random)
            2 -> Pair(R.drawable.dice_2, random)
            3 -> Pair(R.drawable.dice_3, random)
            4 -> Pair(R.drawable.dice_4, random)
            5 -> Pair(R.drawable.dice_5, random)
            else -> Pair(R.drawable.dice_6, random)
        }
    }

    fun rollDice(view: View) {
        if (playerScore1 < gameLimit || playerScore2 < gameLimit) {
            if (currentPlayer) {
                var (dice1, number1) = rollDices()
                addScore1(number1)
                diceImageView1.setImageResource(dice1)
                annimateDice(diceImageView1)
                var (dice2, number2) = rollDices()
                addScore1(number2)
                diceImageView2.setImageResource(dice2)
                annimateDice(diceImageView2)
                currentPlayer = false
            } else {
                var (dice1, number1) = rollDices()
                addScore2(number1)
                annimateDice(diceImageView3)
                diceImageView3.setImageResource(dice1)
                var (dice2, number2) = rollDices()
                addScore2(number2)
                annimateDice(diceImageView4)
                diceImageView4.setImageResource(dice2)
                currentPlayer = true
            }
        } else {
            if (playerScore1 > playerScore2) {
                winnerTextView.text = "Player 1 Won"
            } else {
                winnerTextView.text = "Player 2 Won"
            }
        }
    }

    fun clear(view: View) {
        diceImageView1.setImageResource(R.drawable.empty_dice)
        diceImageView2.setImageResource(R.drawable.empty_dice)
        diceImageView3.setImageResource(R.drawable.empty_dice)
        diceImageView4.setImageResource(R.drawable.empty_dice)
        playerScore1 = 0
        playerScore2 = 0
        playerScoreTextView1.text = "Player 1 Score: $playerScore1"
        playerScoreTextView2.text = "Player 2 Score: $playerScore2"
        winnerTextView.text = ""
        currentPlayer = true
    }

    fun addScore1(diceNumber: Int) {
        playerScore1 += diceNumber
        playerScoreTextView1.text = "Player 1 Score: $playerScore1"
    }

    fun addScore2(diceNumber: Int) {
        playerScore2 += diceNumber
        playerScoreTextView2.text = "Player 1 Score: $playerScore2"
    }

    fun annimateDice(dice: ImageView) {           // mi angama ashxatum amen view i hamar chem haskanum inchu
        dice.animate().rotationX(720f).duration = 1000
    }
}
