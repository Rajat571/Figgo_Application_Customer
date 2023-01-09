package com.pearlorganisation.figgo

interface IOnBackPressed {
    fun onBackPressed(): Boolean
    fun openSomeActivityForResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    )
}

