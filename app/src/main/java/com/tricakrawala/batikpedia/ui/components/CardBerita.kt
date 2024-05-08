package com.tricakrawala.batikpedia.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.ui.theme.background2
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily

@Composable
fun CardBerita(
    modifier: Modifier = Modifier,
    image: Int,
    text: String,
) {
    Column(
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(165.dp)
                .clip(RoundedCornerShape(16.dp))
                ,

        )

        Text(
            text = text,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            color = background2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            fontSize = 12.sp
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun preview() {
    BatikPediaTheme {
        Box {
            CardBerita(
                image = R.drawable.fake_berita_image,
                text = "Melihat Proses Pembuatan batik"
            )
        }
    }
}