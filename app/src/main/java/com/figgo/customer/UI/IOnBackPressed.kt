package com.figgo.customer.UI

interface IOnBackPressed {
    fun onBackPressed(): Boolean
    fun openSomeActivityForResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    )

    fun Any.onBackPressed(): Boolean
}

