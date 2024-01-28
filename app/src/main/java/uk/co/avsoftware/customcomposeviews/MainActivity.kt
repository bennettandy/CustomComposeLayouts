package uk.co.avsoftware.customcomposeviews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.co.avsoftware.customcomposeviews.layout.CustomFlowRow
import uk.co.avsoftware.customcomposeviews.ui.theme.CustomComposeViewsTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomComposeViewsTheme {
                CustomFlowRow {
                    repeat(50) {
                        Box(
                            modifier = Modifier
                                .width(Random.nextInt(50, 200).dp)
                                .height(100.dp)
                                .background(Color(Random.nextLong(0xFFFFFFFF)))
                        ){
                            Greeting(name = "custom")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomComposeViewsTheme {
        CustomFlowRow {
            repeat(10) {
                Box(
                    modifier = Modifier
                        .width(Random.nextInt(50, 200).dp)
                        .height(100.dp)
                        .background(Color(Random.nextLong(0xFFFFFFFF)))
                ){
                    Greeting(name = "preview")
                }
            }
        }
    }
}