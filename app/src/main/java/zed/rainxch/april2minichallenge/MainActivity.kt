package zed.rainxch.april2minichallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import zed.rainxch.april2minichallenge.data.Egg
import zed.rainxch.april2minichallenge.ui.theme.April2MiniChallengeTheme
import zed.rainxch.april2minichallenge.ui.theme.BlueDark
import zed.rainxch.april2minichallenge.ui.theme.Brown
import zed.rainxch.april2minichallenge.ui.theme.Gray
import zed.rainxch.april2minichallenge.ui.theme.Orange1
import zed.rainxch.april2minichallenge.ui.theme.Orange2
import zed.rainxch.april2minichallenge.ui.theme.Orange3
import zed.rainxch.april2minichallenge.ui.theme.Yellow
import zed.rainxch.april2minichallenge.ui.theme.Yellow1
import zed.rainxch.april2minichallenge.ui.theme.Yellow2
import zed.rainxch.april2minichallenge.ui.theme.Yellow3
import zed.rainxch.april2minichallenge.ui.theme.Yellow4
import java.util.function.UnaryOperator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            April2MiniChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EggHuntScreen(modifier = Modifier.background(BlueDark).padding(innerPadding))
                }
            }
        }
    }
}

var nunitoBlack = FontFamily(Font(R.font.nunito_black))
var nunitoBold = FontFamily(Font(R.font.nunito_semi_bold))

@Preview(showBackground = true)
@Composable
fun EggHuntScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textGradient = Brush.horizontalGradient(
            colors = listOf(
                Color(0xffFFD343),
                Color(0xffFFD64D),
                Color(0xffFFB619),
            )
        )

        val buttonGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xffFFC441),
                Color(0xffEDA616),
                Color(0xffE09723),
                Color(0xffCA7500)
            )
        )

        var eggs = remember {
            mutableStateListOf(
                EggItem("Behind the TV stand"),
                EggItem("In the garden shed"),
                EggItem("In the garden shed"),
                EggItem("Inside the birdhouse"),
                EggItem("Behind the garden bushes"),
                EggItem("Inside the flower pot"),
                EggItem("In the mailbox"),
                EggItem("In the kitchen pantry")
            )
        }

        val totalEggs = eggs.size
        val selectedEggs by remember {
            derivedStateOf {
                eggs.count { it.isChecked.value }
            }
        }

        var showDialog by remember { mutableStateOf(false) }
        var isTicked by remember { mutableStateOf(false) }

        if (showDialog) {
            CustomDialog(
                onDismiss = {
                    showDialog = false
                },
                isTicked = isTicked
            )
        }

        Text(
            text = "Egg Hunt Checklist",
            style = TextStyle(
                brush = textGradient,
                fontSize = 22.sp,
                fontFamily = nunitoBlack
            ),
            modifier = Modifier.padding(top = 24.dp)
        )
        Spacer(Modifier.height(36.dp))
        Text(
            text = "Pick locations, where you’ve found eggs",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp),
            fontSize = 18.sp,
            fontFamily = nunitoBlack,
            color = Color.White
        )
        Text(
            text = "${selectedEggs}/${totalEggs} eggs found",
            color = Color(0xffFFF583),
            fontSize = 16.sp,
            fontFamily = nunitoBlack
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            itemsIndexed(eggs.toList()) { index, egg ->
                EggItem(
                    egg = egg,
                    onCheckedChange = { isChecked, newEgg ->
                        if (isChecked) {
                            showDialog = true
                            isTicked = true
                        } else {
                            if (selectedEggs > 0) {
                                showDialog = true
                                isTicked = false
                            }
                        }
                        eggs[index] = newEgg
                    }
                )
            }
        }

        Button(
            onClick = {
                eggs.forEach { it.isChecked.value = false }
            },
            shape = androidx.compose.foundation.shape.RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(32.dp))
                .background(brush = buttonGradient)
        ) {
            Text(
                text = "Reset",
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = nunitoBold
            )
        }
    }
}


@Composable
fun EggItem(
    modifier: Modifier = Modifier,
    egg: EggItem,
    onCheckedChange: (Boolean, EggItem) -> Unit
) {
    val bgGradient = Brush.verticalGradient(
        listOf(
            Yellow1,
            Yellow2,
            Yellow3,
            Yellow4
        )
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                if (egg.isChecked.value) bgGradient else SolidColor(Gray),
                RoundedCornerShape(16.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = egg.isChecked.value,
            onCheckedChange = { isChecked ->
                egg.isChecked.value = isChecked
                onCheckedChange(isChecked, egg)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.White,
                uncheckedColor = Color.White,
                checkmarkColor = Brown
            ),
            modifier = Modifier
                .size(32.dp)
                .shadow(4.dp, RoundedCornerShape(12.dp))
                .background(Color.White, RoundedCornerShape(12.dp))
                .clipToBounds()
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = egg.locationDescription,
            color = Color.White,
            modifier = Modifier.padding(4.dp),
            fontFamily = nunitoBold
        )
    }
}
