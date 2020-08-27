package com.example.mycalculator.ui.main

interface MainContact {
    interface View {
        fun showResult(peek: String?)

    }

    interface Presenter {
        fun calculator(expression: String)
    }
}