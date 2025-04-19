package zed.rainxch.april2minichallenge.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Egg(
    val place: String,
    var isChecked: MutableState<Boolean> = mutableStateOf(false)
) {
    companion object {
        fun getEggs() = listOf<Egg>(
            Egg("Behind the TV stand"),
            Egg("In the garden shed"),
            Egg("Under the picnic table"),
            Egg("Inside the birdhouse"),
            Egg("Behind the garden bushes"),
            Egg("Inside the flower pot"),
            Egg("In the mailbox"),
            Egg("In the kitchen pantry"),
        ).shuffled()
    }
}
