package com.example.mycalculator.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mycalculator.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContact.View, View.OnClickListener {

    private val presenter: MainPresenter = MainPresenter(this)
    var inputKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButton()
    }

    private fun initButton() {
        button_e.setOnClickListener(this)
        button_pi.setOnClickListener(this)
        button_mu.setOnClickListener(this)
        button_del.setOnClickListener(this)
        button_ac.setOnClickListener(this)
        button_ngoac_mo.setOnClickListener(this)
        button_ngoac_dong.setOnClickListener(this)
        button_0.setOnClickListener(this)
        button_1.setOnClickListener(this)
        button_2.setOnClickListener(this)
        button_3.setOnClickListener(this)
        button_4.setOnClickListener(this)
        button_5.setOnClickListener(this)
        button_6.setOnClickListener(this)
        button_7.setOnClickListener(this)
        button_8.setOnClickListener(this)
        button_9.setOnClickListener(this)
        button_cham.setOnClickListener(this)
        button_cong.setOnClickListener(this)
        button_tru.setOnClickListener(this)
        button_nhan.setOnClickListener(this)
        button_chia.setOnClickListener(this)
        button_bang.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button_e -> {
                inputKey += "e"
            }
            R.id.button_pi -> {
                inputKey += "π"
            }
            R.id.button_mu -> {
                inputKey += "^"
            }
            R.id.button_del -> {
                if (inputKey.isNotEmpty()) {
                    inputKey = inputKey.substring(0, inputKey.lastIndex)
                }
            }
            R.id.button_ac -> {
                inputKey = ""
            }
            R.id.button_ngoac_mo -> {
                inputKey += "("
            }
            R.id.button_ngoac_dong -> {
                inputKey += ")"
            }
            R.id.button_0 -> {
                inputKey += "0"
            }
            R.id.button_1 -> {
                inputKey += "1"
            }
            R.id.button_2 -> {
                inputKey += "2"
            }
            R.id.button_3 -> {
                inputKey += "3"
            }
            R.id.button_4 -> {
                inputKey += "4"
            }
            R.id.button_5 -> {
                inputKey += "5"
            }
            R.id.button_6 -> {
                inputKey += "6"
            }
            R.id.button_7 -> {
                inputKey += "7"
            }
            R.id.button_8 -> {
                inputKey += "8"
            }
            R.id.button_9 -> {
                inputKey += "9"
            }
            R.id.button_cham -> {
                inputKey += "."
            }
            R.id.button_cong -> {
                inputKey += "+"
            }
            R.id.button_tru -> {
                inputKey += "-"
            }
            R.id.button_nhan -> {
                inputKey += "*"
            }
            R.id.button_chia -> {
                inputKey += "/"
            }
            R.id.button_bang -> {
                presenter.calculator(text_calculator.text.toString())
            }
        }
        text_calculator.text = inputKey
    }

    override fun showResult(peek: String?) {
        Thread {
            inputKey = peek!!
            text_calculator.text = peek
        }.start()
    }
}