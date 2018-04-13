package com.example.danie.test

fun changedate(duration:Long?):String{
    val minute = duration!! / 60
    val second = duration!!%60
    return if (minute>9){
        if (second>9){
         return "$minute'$second''"
        }else{
         return "$minute'0$second''"
        }
    }else{
        if (second>9){
            return "0$minute'$second''"
        }else{
            return "0$minute'0$second''"
        }
    }
}