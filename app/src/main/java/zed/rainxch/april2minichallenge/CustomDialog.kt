package zed.rainxch.april2minichallenge

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.delay
import zed.rainxch.april2minichallenge.data.FactData

@Composable
fun CustomDialog(
    onDismiss: () -> Unit,
    isTicked: Boolean,
    modifier: Modifier = Modifier
) {
    val textGradient = Brush.verticalGradient(
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
    val randomFact by remember { mutableStateOf(FactData.getRandomFact()) }
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        val animationProgress = remember { Animatable(1f) }
        var seconds by remember { mutableIntStateOf(4) }
        LaunchedEffect(Unit) {
            animationProgress.animateTo(
                0f,
                animationSpec = tween(4000, 0, LinearEasing)
            )
        }
        LaunchedEffect(Unit) {
            delay(4000)
            onDismiss()
        }
        LaunchedEffect(Unit) {
            while (seconds > 0) {
                delay(1000)
                seconds--
            }
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
                .background(Color(0xff10122C))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LinearProgressIndicator(
                    progress = {
                        animationProgress.value
                    },
                    color = Color(0xffFFC441),
                    trackColor = Color(0xff894621),
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                )
                Text(
                    text = "${seconds}s",
                    color = Color(0xffFFF583),
                    fontSize = 16.sp,
                    fontFamily = nunitoBlack
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = if (isTicked) "Easter fact!" else "Oops, the egg rolled away!",
                fontSize = 18.sp,
                fontFamily = nunitoBlack,
                color = Color.White
            )

            Spacer(Modifier.height(20.dp))
            Image(
                painter = if (isTicked) painterResource(R.drawable.ic_success) else painterResource(
                    R.drawable.ic_failure
                ),
                contentDescription = "Image",
                modifier = Modifier.size(120.dp)
            )
            Spacer(Modifier.height(12.dp))
            if (isTicked) {
                Text(
                    text = randomFact,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        brush = textGradient,
                        fontFamily = nunitoBlack,
                        fontSize = 16.sp
                    )
                )
            }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { onDismiss() },
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .width(200.dp)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                    .background(brush = buttonGradient)
            ) {
                Text(
                    text = "Dismiss",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = nunitoBlack
                )
            }
        }
    }
}
