package zed.rainxch.april2minichallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zed.rainxch.april2minichallenge.ui.theme.April2MiniChallengeTheme
import zed.rainxch.april2minichallenge.ui.theme.BlueDark
import zed.rainxch.april2minichallenge.ui.theme.Gray
import zed.rainxch.april2minichallenge.ui.theme.Orange1
import zed.rainxch.april2minichallenge.ui.theme.Orange2
import zed.rainxch.april2minichallenge.ui.theme.Orange3
import zed.rainxch.april2minichallenge.ui.theme.Yellow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            April2MiniChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EggHuntScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BlueDark),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var totalEggs by remember { mutableStateOf(0) }
        var selectedEggs by remember { mutableStateOf(0) }

        var eggs by remember {
            mutableStateOf(
                listOf<EggItem>(
                    EggItem("Behind the TV stand", true),
                    EggItem("In the garden shed"),
                    EggItem("In the garden shed"),
                    EggItem("Inside the birdhouse"),
                    EggItem("Behind the garden bushes"),
                    EggItem("Inside the flower pot"),
                    EggItem("In the mailbox"),
                    EggItem("In the kitchen pantry")
                )
            )
        }


        Text(
            text = stringResource(R.string.egg_hunt_checklist),
            style = TextStyle(
                Brush.verticalGradient(
                    colors = listOf(Orange1, Orange2, Orange3)
                )
            )
        )

        Text(
            text = stringResource(R.string.pick_locations_where_you_ve_found_eggs),
            color = Color.White
        )

        Text(
            text = stringResource(R.string.d_d_eggs_found, selectedEggs, totalEggs),
            color = Yellow
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(eggs) { egg ->
                EggItem(
                    egg = egg,
                    onCheckedChange = { isChecked, currentEgg ->
                        if (isChecked && !currentEgg.isChecked) {
                            selectedEggs++
                            currentEgg.isChecked = true
                        } else {
                            if (selectedEggs > 0) {
                                selectedEggs--
                                currentEgg.isChecked = true
                            }
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun EggItem(
    modifier: Modifier = Modifier,
    egg: EggItem,
    onCheckedChange: (Boolean, EggItem) -> Unit
) {
    var eggState by remember { mutableStateOf(egg) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray, RoundedCornerShape(10.dp))
            .padding(16.dp)
            .padding(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = eggState.isChecked,
            onCheckedChange = { isChecked ->
                eggState.isChecked = isChecked
                onCheckedChange(isChecked, eggState)
            },
            colors = CheckboxDefaults.colors(
                uncheckedColor = Color.Red,
                checkmarkColor = Color.Black
            ),
            modifier = Modifier
                .padding(end = 16.dp)
                .background(Color.White)
        )

        Text(
            text = egg.locationDescription,
            color = Color.White
        )
    }
}

