package com.example.basenumbercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val BASE_HEX: Int = 16
    val BASE_DEC: Int = 10
    val BASE_OCT: Int = 8
    val BASE_BIN: Int = 2

    var selectedBase: Int = BASE_DEC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        buttonA.setOnClickListener(this)
        buttonB.setOnClickListener(this)
        buttonC.setOnClickListener(this)
        buttonD.setOnClickListener(this)
        buttonE.setOnClickListener(this)
        buttonF.setOnClickListener(this)
        buttonDel.setOnClickListener(this)
        buttonAC.setOnClickListener(this)

        buttonHex.setOnClickListener {
            selectedBase = BASE_HEX
            setIsEnabled()
        }

        buttonDec.setOnClickListener {
            selectedBase = BASE_DEC
            setIsEnabled()
        }

        buttonOct.setOnClickListener {
            selectedBase = BASE_OCT
            setIsEnabled()
        }

        buttonBin.setOnClickListener {
            selectedBase = BASE_BIN
            setIsEnabled()
        }

        setIsEnabled()
        clearAll()
    }

    override fun onClick(view: View?) {

        lateinit var textView : TextView
        when(selectedBase){
            BASE_HEX -> textView = textViewHex
            BASE_DEC -> textView = textViewDec
            BASE_OCT -> textView = textViewOct
            BASE_BIN -> textView = textViewBin
        }

        val button = view as Button
        when(button.id)
        {
            R.id.button0,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.buttonA,
            R.id.buttonB,
            R.id.buttonC,
            R.id.buttonD,
            R.id.buttonE,
            R.id.buttonF
            ->{
                var text = textView.text.toString()
                if(text == "0") {
                    text = ""
                }
                text += button.text.toString()
                textView.text = text
                calculate()
            }
            R.id.buttonDel -> {
                var text = textView.text.toString()
                val length = text.length
                if(length > 1){
                    text = text.substring(0, length - 1)
                }else{
                    text = "0"
                }
                textView.text = text
                calculate()
            }
            R.id.buttonAC -> clearAll()
        }
    }

    private fun calculate(){
        val converter = BaseConverter()

        when(selectedBase){
            BASE_HEX ->{
                textViewDec.text = converter.hexToDec(textViewHex.text.toString())
                textViewOct.text = converter.decToOct(textViewDec.text.toString())
                textViewBin.text = converter.decToBin(textViewDec.text.toString())
            }
            BASE_DEC ->{
                textViewHex.text = converter.decToHex(textViewDec.text.toString())
                textViewOct.text = converter.decToOct(textViewDec.text.toString())
                textViewBin.text = converter.decToBin(textViewDec.text.toString())
            }
            BASE_OCT ->{
                textViewDec.text = converter.octToDec(textViewOct.text.toString())
                textViewHex.text = converter.decToHex(textViewDec.text.toString())
                textViewBin.text = converter.decToBin(textViewDec.text.toString())
            }
            BASE_BIN ->{
                textViewDec.text = converter.binToDec(textViewBin.text.toString())
                textViewHex.text = converter.decToHex(textViewDec.text.toString())
                textViewOct.text = converter.decToOct(textViewDec.text.toString())
            }
        }
    }

    private fun clearAll() {
        textViewHex.text = "0"
        textViewDec.text = "0"
        textViewOct.text = "0"
        textViewBin.text = "0000"
    }

    private fun setIsEnabled() {
        buttonHex.setBackgroundResource(R.color.colorBase)
        buttonDec.setBackgroundResource(R.color.colorBase)
        buttonOct.setBackgroundResource(R.color.colorBase)
        buttonBin.setBackgroundResource(R.color.colorBase)

        when(selectedBase){
            BASE_DEC ->{
                buttonDec.setBackgroundResource(R.color.colorSelectedBase)

                setColor(textViewHex, false)
                setColor(textViewDec, true)
                setColor(textViewOct, false)
                setColor(textViewBin, false)

                button2.isEnabled = true
                button3.isEnabled = true
                button4.isEnabled = true
                button5.isEnabled = true
                button6.isEnabled = true
                button7.isEnabled = true
                button8.isEnabled = true
                button9.isEnabled = true
                buttonA.isEnabled = false
                buttonB.isEnabled = false
                buttonC.isEnabled = false
                buttonD.isEnabled = false
                buttonE.isEnabled = false
                buttonF.isEnabled = false
            }
            BASE_OCT ->{
                buttonOct.setBackgroundResource(R.color.colorSelectedBase)

                setColor(textViewHex, false)
                setColor(textViewDec, false)
                setColor(textViewOct, true)
                setColor(textViewBin, false)

                button2.isEnabled = true
                button3.isEnabled = true
                button4.isEnabled = true
                button5.isEnabled = true
                button6.isEnabled = true
                button7.isEnabled = true
                button8.isEnabled = false
                button9.isEnabled = false
                buttonA.isEnabled = false
                buttonB.isEnabled = false
                buttonC.isEnabled = false
                buttonD.isEnabled = false
                buttonE.isEnabled = false
                buttonF.isEnabled = false
            }
            BASE_BIN ->{
                buttonBin.setBackgroundResource(R.color.colorSelectedBase)

                setColor(textViewHex, false)
                setColor(textViewDec, false)
                setColor(textViewOct, false)
                setColor(textViewBin, true)

                button2.isEnabled = false
                button3.isEnabled = false
                button4.isEnabled = false
                button5.isEnabled = false
                button6.isEnabled = false
                button7.isEnabled = false
                button8.isEnabled = false
                button9.isEnabled = false
                buttonA.isEnabled = false
                buttonB.isEnabled = false
                buttonC.isEnabled = false
                buttonD.isEnabled = false
                buttonE.isEnabled = false
                buttonF.isEnabled = false
            }
            else ->{
                buttonHex.setBackgroundResource(R.color.colorSelectedBase)

                setColor(textViewHex, true)
                setColor(textViewDec, false)
                setColor(textViewOct, false)
                setColor(textViewBin, false)

                button2.isEnabled = true
                button3.isEnabled = true
                button4.isEnabled = true
                button5.isEnabled = true
                button6.isEnabled = true
                button7.isEnabled = true
                button8.isEnabled = true
                button9.isEnabled = true
                buttonA.isEnabled = true
                buttonB.isEnabled = true
                buttonC.isEnabled = true
                buttonD.isEnabled = true
                buttonE.isEnabled = true
                buttonF.isEnabled = true
            }
        }
    }

    private fun setColor(view: View, availability: Boolean) {
        if(availability){
            view.setBackgroundResource(R.color.colorAvailable)
        }else{
            view.setBackgroundResource(R.color.colorUnavailable)
        }
    }
}