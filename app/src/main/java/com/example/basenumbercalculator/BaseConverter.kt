package com.example.basenumbercalculator

import kotlin.math.pow

class BaseConverter {
    fun decToHex(value: String):String{
        return decTo(value, 16)
    }

    fun decToOct(value: String):String{
        return decTo(value, 8)
    }

    fun decToBin(value: String):String{
        var temp = decTo(value, 2)

        when(temp.length % 4){
            1 -> temp = "000$temp"
            2 -> temp = "00$temp"
            3 -> temp = "0$temp"
        }

        var ret = ""
        val count = temp.length / 4
        for(i in 0 until count){
            ret += " " + temp.substring(i * 4, i * 4 + 4)
        }

        return ret.trimStart()
    }

    fun hexToDec(value: String):String{
        return  toDec(value, 16)
    }

    fun octToDec(value: String):String{
        return  toDec(value, 8)
    }

    fun binToDec(value: String):String{
        return  toDec(value, 2)
    }


    private fun decTo(value: String, base: Int):String{
        if(value.isEmpty()){
            return ""
        }

        var source = value.toInt()
        var dest = ""
        do{
            var divide = source / base
            var remainder = source % base
            dest = toHexNumber(remainder.toString()) + dest

            if(divide in 1 until base){
                dest = toHexNumber(divide.toString()) + dest
            }
            source = divide
        }while (source >= base)

        return dest
    }

    private fun toDec(value: String, base: Int): String{
        if(value.isEmpty()){
            return ""
        }

        var sum = 0
        for(i in value.indices){
            val digits = (value.length - 1) - i
            val e = base.toDouble().pow(digits).toInt()
            val c = toDecNumber(value.substring(i, i+1)).toInt()
            sum += c * e
        }
        return sum.toString()
    }

    private fun toHexNumber(num: String): String{
        return when(num){
            "10" -> "A"
            "11" -> "B"
            "12" -> "C"
            "13" -> "D"
            "14" -> "E"
            "15" -> "F"
            else -> num
        }
    }

    private fun toDecNumber(num: String): String{
        return when(num){
            "A" -> "10"
            "B" -> "11"
            "C" -> "12"
            "D" -> "13"
            "E" -> "14"
            "F" -> "15"
            else -> num
        }
    }
}

