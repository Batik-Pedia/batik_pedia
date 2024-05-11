package com.tricakrawala.batikpedia.screen.wisata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.screen.provinsi.WisataProvinsiContent
import com.tricakrawala.batikpedia.ui.common.UiState
import com.tricakrawala.batikpedia.ui.components.AtlasItem
import com.tricakrawala.batikpedia.ui.components.ImgDetailBig
import com.tricakrawala.batikpedia.ui.components.TextWithCard
import com.tricakrawala.batikpedia.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.ui.theme.background2
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.ui.theme.textColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailWisataScreen(
    modifier: Modifier = Modifier,
    idWisata : Long,
    navController : NavHostController,
    viewModel: WisataViewModel = koinViewModel(),
){
    val uiState by viewModel.uiStateWisataById.collectAsState(initial = UiState.Loading)

    if (uiState is UiState.Loading){
        viewModel.getWisataById(idWisata)
    }

    when(val wisata = uiState){
        is UiState.Error -> {

        }

        is UiState.Success -> {
            DetailWisataContent(navController = navController, imgWisata = wisata.data.image, titleWisata = wisata.data.namaWisata)
        }

        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailWisataContent(
    modifier: Modifier = Modifier,
    navController : NavHostController,
    imgWisata : Int,
    titleWisata : String,
){

    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())

    ) {
        Image(
            painter = painterResource(id = R.drawable.ornamen_batik_beranda),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(180.dp)

        )

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.menu_wisata_top_bar),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,
                    fontSize = 16.sp
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.back),
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent)
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 88.dp, start = 24.dp, end = 24.dp)
        ) {
            ImgDetailBig(image = imgWisata, text = titleWisata, modifier = Modifier)

            Spacer(modifier = Modifier.height(8.dp))

            TextWithCard(
                title = stringResource(id = R.string.tentang_destinasi),
                text = stringResource(id = R.string.tentang_destinasi_detail)
            )

            Spacer(modifier = Modifier.height(8.dp))

            AtlasItem(
                image = R.drawable.peta1,
                nusantara = "Yogyakarta",
                modifier = modifier.fillMaxWidth()
            )


        }
    }

}

@Composable
@Preview(showBackground = true)
private fun Preview(){
    BatikPediaTheme {
        DetailWisataContent(navController = rememberNavController(), imgWisata = R.drawable.wisata1, titleWisata = "Kampung batik laweyan")
    }
}