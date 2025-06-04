package com.example.hospitalcompose.ui.utils.common

import android.content.Context
import android.os.Environment
import java.io.File

fun getRecordingsDir(context: Context): File {
    return File(
        context.getExternalFilesDir(Environment.DIRECTORY_MUSIC),
        "grabaciones"
    ).apply {
        if (!exists()) mkdirs()
    }
}
