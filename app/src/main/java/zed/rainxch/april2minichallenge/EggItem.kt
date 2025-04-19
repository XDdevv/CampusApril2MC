package zed.rainxch.april2minichallenge

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class EggItem (
    val locationDescription: String,
    var isChecked: MutableState<Boolean> = mutableStateOf(false)
)