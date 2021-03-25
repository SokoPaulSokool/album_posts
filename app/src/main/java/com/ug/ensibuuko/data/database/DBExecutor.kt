package com.ug.ensibuuko.data.database

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//class DBExecutor {
//    fun IOThread(t: Runnable?) {
//        val IO_EXECUTOR: ExecutorService = Executors.newSingleThreadExecutor()
//        IO_EXECUTOR.execute(t)
//    }
//}

object DBExecutor {
    fun IOThread(t: Runnable?) {
        val IO_EXECUTOR: ExecutorService = Executors.newSingleThreadExecutor()
        IO_EXECUTOR.execute(t)
    }
}