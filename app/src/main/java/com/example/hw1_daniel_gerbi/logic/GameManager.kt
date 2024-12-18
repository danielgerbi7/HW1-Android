package com.example.hw1_daniel_gerbi.logic

import com.example.hw1_daniel_gerbi.utilities.Constants

class GameManager(private val lifeCount: Int = 3, private val numOfLanes : Int = 3) {

    var score: Int = 0
        private set

    var playerPosition = 1
    var hitPosition = 0

    val cakeMatrix: Array<Array<Boolean>> = getRandomMatrix()

    fun canMovePlayerLeft(): Boolean {
        return playerPosition > 0
    }

    fun canMovePlayerRight(): Boolean {
        return playerPosition < 2
    }

    fun movePlayerLeft() {
        if (canMovePlayerLeft())
            playerPosition--
    }

    fun movePlayerRight() {
        if (canMovePlayerRight())
            playerPosition++
    }

    val isGameOver: Boolean
        get() = hitPosition == lifeCount

    private fun getRandomMatrix() : Array<Array<Boolean>>{
        val arr: Array<Array<Boolean>> = Array(5) { Array(numOfLanes) { false } }
        for(i in 0..2){
            arr[i] = getRandomBooleanArray(numOfLanes)
        }
        return arr
    }

    fun moveCakesDown() {
        for (row in 4 downTo 1) {
            for (col in 0 until 3) {
                    cakeMatrix[row][col] = cakeMatrix[row - 1][col]
            }
        }
        cakeMatrix[0] = getRandomBooleanArray(numOfLanes)
    }

    private fun getRandomBooleanArray(size: Int): Array<Boolean> {
        val array = Array(size) { false }
        val randomIndex = (0 until size).random()
        array[randomIndex] = true
        return array
    }

    fun checkCollision(): Boolean {
        if (cakeMatrix[3][playerPosition]) {
            hitPosition++
            return true
        }
        return false
    }

    fun updateScore() {
        score += Constants.GameLogic.AVOID_POINTS
    }

}
