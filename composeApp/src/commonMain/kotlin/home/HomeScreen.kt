package home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import bentu.composeapp.generated.resources.Res
import bentu.composeapp.generated.resources.home_page_subtitle
import bentu.composeapp.generated.resources.home_page_title
import bentu.composeapp.generated.resources.home_page_trek_list_button
import bentu.composeapp.generated.resources.home_screen_background
import bentu.composeapp.generated.resources.next_g_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.Dimens
import ui.widgets.CustomBodyL
import ui.widgets.CustomButton
import ui.widgets.CustomHeadlineM

@Composable
fun HomeScreen(
    onTrekList: () -> Unit = {}
) {
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background image
            Image(
                painter = painterResource(Res.drawable.home_screen_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                alpha = 0.5f, // Adjust transparency as needed
                contentScale = ContentScale.FillHeight // fills screen, keeps aspect ratio
            )

            // Foreground UI
            Scaffold(
                containerColor = Color.Transparent
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CustomHeadlineM(stringResource(Res.string.home_page_title))
                        CustomBodyL(stringResource(Res.string.home_page_subtitle))
                        Image(
                            painter = painterResource(Res.drawable.next_g_logo),
                            contentDescription = null,
                            modifier = Modifier.padding(Dimens.xx_large)
                        )
                    }

                    CustomButton(
                        text = stringResource(Res.string.home_page_trek_list_button),
                        onClick = onTrekList,
                        modifier = Modifier.padding(horizontal = Dimens.large)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}