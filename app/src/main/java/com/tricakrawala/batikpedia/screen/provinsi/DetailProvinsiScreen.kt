package com.tricakrawala.batikpedia.screen.provinsi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.tricakrawala.batikpedia.model.KatalogBatik
import com.tricakrawala.batikpedia.model.Wisata
import com.tricakrawala.batikpedia.ui.common.UiState
import com.tricakrawala.batikpedia.ui.components.ImgDetailBig
import com.tricakrawala.batikpedia.ui.components.ImgRowDetail
import com.tricakrawala.batikpedia.ui.components.TextWithCard
import com.tricakrawala.batikpedia.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.ui.theme.background2
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.ui.theme.textColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailProvinsiScreen(
    modifier: Modifier = Modifier,
    idProvinsi: Long,
    viewModel: ProvinsiViewModel = koinViewModel()
) {

    val uiState by viewModel.uiStateDetail.collectAsState(initial = UiState.Loading)
    val uiStateBatik by viewModel.uiStateBatik.collectAsState(initial = UiState.Loading)
    val uiStateWisata by viewModel.uiStateWisata.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiState is UiState.Loading) {
            viewModel.getProvinsiById(idProvinsi)
        }

        if (uiStateBatik is UiState.Loading) {
            viewModel.getAllBatik()
        }

        if (uiStateWisata is UiState.Loading) {
            viewModel.getAllWisata()
        }

    }

    when (val provinsi = uiState) {
        is UiState.Success -> {
            when (val batik = uiStateBatik) {
                is UiState.Error -> {}
                is UiState.Success -> {
                    when (val wisata = uiStateWisata) {

                        is UiState.Error -> {}
                        is UiState.Success -> {
                            DetailProvinsiContent(
                                navController = rememberNavController(),
                                image = provinsi.data.image,
                                textContent = provinsi.data.provinsi,
                                listBatik = batik.data,
                                listWisata = wisata.data
                            )

                        }

                        else -> {}
                    }
                }

                else -> {}
            }

        }

        is UiState.Error -> {}
        else -> {

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProvinsiContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    image: Int,
    listBatik: List<KatalogBatik>,
    listWisata: List<Wisata>,
    textContent: String
) {

    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()
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
                    text = stringResource(id = R.string.menu_provinsi),
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
            ImgDetailBig(image = image, text = textContent, modifier = Modifier)
            Spacer(modifier = Modifier.height(8.dp))
            TextWithCard(text = stringResource(id = R.string.tentang_yogya))

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.motif_batik),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 16.dp)
                ) {

                    items(listBatik){batik ->
                        ImgRowDetail(image = batik.image, modifier = Modifier.padding(end = 16.dp) )
                    }
                }

                Text(
                    text = stringResource(id = R.string.destinasi_wisata),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {

                    items(listWisata){wisata ->
                        ImgRowDetail(image = wisata.image, modifier = Modifier.padding(end = 16.dp) )
                    }
                }

            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun preview() {
    val dummyBatik = listOf(
        KatalogBatik(1, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
        KatalogBatik(2, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
        KatalogBatik(3, R.drawable.batik1, "Mega Mendung", "Batik Tradisional")
    )

    val listWisata = listOf(
        Wisata(1, R.drawable.wisata1, "Kampung Batik Laweyan"),
        Wisata(2, R.drawable.wisata1, "Kampung Batik Laweyan"),

        )

    BatikPediaTheme {
        DetailProvinsiContent(
            navController = rememberNavController(),
            image = R.drawable.yogyakarta,
            textContent = "Yogyakarta",
            listBatik = dummyBatik,
            listWisata = listWisata
        )
    }
}