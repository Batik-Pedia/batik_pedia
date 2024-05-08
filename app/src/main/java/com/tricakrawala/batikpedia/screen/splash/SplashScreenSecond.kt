package com.tricakrawala.batikpedia.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.navigation.Screen
import com.tricakrawala.batikpedia.ui.components.ButtonNextSplah
import com.tricakrawala.batikpedia.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.ui.theme.background2
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.ui.theme.primary

@Composable
fun SplashScreenSecond(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background2)
    ) {

        Box(modifier = Modifier.fillMaxWidth().padding(top = 36.dp)) {
            Image(
                painter = painterResource(id = R.drawable.logo_batik_pedia),
                contentDescription = "Logo Batik Pedia",
                modifier = Modifier
                    .padding(start = 36.dp, top = 36.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.cloud_splash),
                contentDescription = "Logo Batik Pedia",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 60.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.unesco_splash),
            contentDescription = "logo Unesco",
            modifier = Modifier.fillMaxWidth().padding(top = 82.dp).height(190.dp)
        )

        Text(
            text = "",
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        )

        Text(
            text = stringResource(id = R.string.unesco_pers),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(64.dp))


        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.cloud_splash_bottom),
                contentDescription = "Logo Batik Pedia",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(top = 70.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.splash_indicator_2),
                contentDescription = "indicator",
                modifier = Modifier.fillMaxWidth().align(Alignment.Center).padding(bottom = 72.dp)
            )

            Row(modifier = Modifier.align(Alignment.Center).padding(top = 64.dp)) {
                ButtonNextSplah(onClick = {navController.navigate(Screen.SplashThird.route)},color = primary, text = stringResource(id = R.string.selanjutnya), textColor = Color.White )
                Spacer(modifier = Modifier.width(44.dp))
                ButtonNextSplah(onClick = {navController.navigate(Screen.SplashThird.route)} ,color = Color.White, text = stringResource(id = R.string.lewati), textColor = primary )
            }

        }

    }

}

@Composable
@Preview(showBackground = true)
private fun PreviewTwo(){
    BatikPediaTheme {
        SplashScreenSecond(navController = rememberNavController())
    }
}