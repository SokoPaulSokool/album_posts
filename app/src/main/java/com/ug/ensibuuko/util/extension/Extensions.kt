package com.tranzkargo.mobile.util.extension

import java.text.DecimalFormat

/**
 * Define all extensions function here
 * One example is given below
 */

fun Int?.numberFormat(): String {
    return DecimalFormat("#,###,###").format(this)
}