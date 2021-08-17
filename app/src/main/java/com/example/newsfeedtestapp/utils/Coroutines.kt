package com.example.newsfeedtestapp.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object Coroutines {
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    fun io(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    fun globalIo(work: suspend (() -> Unit)) =
        GlobalScope.launch(Dispatchers.IO) {
            work()
        }

    fun lifecycle(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main.immediate)
}