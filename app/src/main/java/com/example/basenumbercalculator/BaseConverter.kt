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

        temp = "00000000000000000000000000000000$temp".takeLast(32)
        val top = temp.take(16)
        val last = temp.takeLast(16)

        var ret = ""
        temp = ""
        for(i in 0 until 4){
            temp += " " + top.substring(i * 4, i * 4 + 4)
        }
        ret = "${temp.trimStart()}\n"

        temp = ""
        for(i in 0 until 4){
            temp += " " + last.substring(i * 4, i * 4 + 4)
        }
        ret = "$ret${temp.trimStart()}"

        return ret
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

        val numbers = value.replace(" ", "").replace("\n", "")
        var sum = 0
        for(i in numbers.indices){
            val digits = (numbers.length - 1) - i
            val e = base.toDouble().pow(digits).toInt()
            val c = toDecNumber(numbers.substring(i, i+1)).toInt()
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

