package com.example.mycalculator.ui.main

import java.util.*
import kotlin.math.pow

class MainPresenter(mvpView: MainContact.View) : MainContact.Presenter {
    private val getMvpView = mvpView
    private var result: String = ""
    private val e = 2.718281828
    private val pi = 3.141592654
    override fun calculator(expression: String) {

        if (expression.length == 1 && expression.contains("e")) {
            getMvpView.showResult(e.toString())
            return
        }
        if (expression.length == 1 && expression.contains("π")) {
            getMvpView.showResult(pi.toString())
            return
        }

        if (expression.isNotEmpty() && (expression.contains("+") ||
                    expression.contains("-") || expression.contains("*") ||
                    expression.contains("/") || expression.contains("^"))
        ) {
            val str = infixToPostfix(expression)
            val stack: Stack<String> = Stack()
            var i = 0
            while (i < str!!.length) {
                if (str[i] == '+' || str[i] == '-' || str[i] == '*' || str[i] == '/' ||
                    str[i] == '^'
                ) {
                    val a: String = stack.peek()
                    stack.pop()
                    val b: String = stack.peek()
                    stack.pop()
                    stack.push(calculate(b.toLong(), a.toLong(), str[i]))
                } else {
                    // Xu ly them vao stack vs nhung so
                    var temp: Long = 0
                    while (i < str.length && str[i] >= '0' && str[i] <= '9') {
                        temp = 10 * temp + (str[i] - '0').toString().toInt()
                        i++
                    }
                    if (str[i] != '#') i--
                    stack.push(temp.toString())
                }
                i++
            }
            result = stack.peek()
            getMvpView.showResult(result)
        }

    }


    private fun calculate(a: Long, b: Long, toanTu: Char): String? {
        var res: Long = 0
        res = when (toanTu) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> a / b
            '^' -> (a.toDouble().pow(b.toDouble())).toLong()
            else -> 0
        }
        return res.toString()
    }

    private fun infixToPostfix(str: String): String? {
        var res = ""
        val listIndex: Stack<Char> = Stack()
        for (i in str.indices) {
            if (str[i] in '0'..'9') {
                res += str[i].toString()
            } else if (str[i] == '(') {
                listIndex.push(str[i])
            } else if (str[i] == ')') {
                while (listIndex.size > 0 && listIndex.peek() != '(') {
                    res += listIndex.peek().toString()
                    listIndex.pop()
                }
                listIndex.pop()
            } else if (str[i] == '+' || str[i] == '-' || str[i] == '*' || str[i] == '/' ||
                str[i] == '^'
            ) {

                //them dau # để phân biệt các số có nhiều hơn 1 chữ số.
                if (res[res.length - 1] in '0'..'9') {
                    res += "#"
                }
                while (listIndex.size > 0 && checkLevel(listIndex.peek()) >= checkLevel(str[i])) {
                    res += listIndex.peek().toString()
                    listIndex.pop()
                }
                listIndex.push(str[i])
            }
        }
        while (listIndex.size > 0) {
            if (listIndex.peek() != '(') {
                res += listIndex.peek().toString()
            }
            listIndex.pop()
        }
        return res
    }

    private fun checkLevel(s: Char): Int {
        if (s == '^') return 5
        if (s == '*' || s == '/') return 4
        return if (s == '+' || s == '-') 3 else 2
    }

}
