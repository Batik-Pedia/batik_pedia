package com.tricakrawala.batikpedia.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.ui.theme.textColor

@Composable
fun ImgDetailBig(
    modifier: Modifier = Modifier,
    image: Int,
    text: String,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Image Detail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(196.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Text(
            text = text,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp, bottom = 16.dp)
        )
    }
}

@Preview
@Composable
private fun preview() {
    BatikPediaTheme {
        ImgDetailBig(image = R.drawable.yogyakarta, text = "Yogyakarta")
    }
}