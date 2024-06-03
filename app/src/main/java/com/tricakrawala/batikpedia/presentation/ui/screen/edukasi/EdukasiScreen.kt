package com.tricakrawala.batikpedia.presentation.ui.screen.edukasi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.data.resource.remote.response.KursusItem
import com.tricakrawala.batikpedia.domain.model.VideoMembatik
import com.tricakrawala.batikpedia.presentation.model.edukasi.EdukasiViewModel
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.KursusBox
import com.tricakrawala.batikpedia.presentation.ui.components.NavbarHome
import com.tricakrawala.batikpedia.presentation.ui.components.VideoColumn
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@Composable
fun EdukasiScreen(
    viewModel: EdukasiViewModel = hiltViewModel(),
    navigateToDetail: (Long) -> Unit,
    navController: NavHostController,
) {
    val uiStateKursus by viewModel.uiStateKursus.collectAsState(initial = UiState.Loading)
    val uiStateVideoMembatik by viewModel.uiStateVideoMembatik.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiStateKursus is UiState.Loading || uiStateVideoMembatik is UiState.Loading) {
            viewModel.getAllKursus()
            viewModel.getAllVideo()
        }
    }

    when(val kursus = uiStateKursus){
        is UiState.Success -> {
            when (val video = uiStateVideoMembatik) {
                is UiState.Success -> {
                    EdukasiContent(
                        navigateToDetail = navigateToDetail,
                        listKursus = kursus.data,
                        listVideoMembatik = video.data,
                        navController = navController
                    )

                }

                is UiState.Error -> {}
                else -> {}
            }
        }
        is UiState.Error -> {}
        else -> {}
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdukasiContent(
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    navController: NavHostController,
    listVideoMembatik: List<VideoMembatik>,
    listKursus: List<KursusItem> = emptyList(),
) {

    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()

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
                    text = stringResource(id = R.string.edukasi),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,
                    fontSize = 16.sp
                )
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, start = 24.dp, end = 24.dp)


        )
        {NavbarHome(textContent = stringResource(id = R.string.kursus_membatik), modifier = Modifier.clickable { navController.navigate(
            Screen.ToListKursus.route)})

           Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)

            ){

            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(top = 5.dp)

            ) {
                items(listKursus) { data ->
                    KursusBox(
                        image = data.image,
                        kursus = data.namaKursus,
                        modifier = modifier.clickable { navigateToDetail(data.idKursus.toLong()) })
                }
            }}

            Spacer(modifier = Modifier.height(8.dp))

            NavbarHome(textContent = stringResource(id = R.string.video_membatik),
                modifier = Modifier.clickable { navController.navigate(Screen.VideoEdukasi.route)})

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(end = 4.dp, start = 4.dp, bottom = 4.dp),
            ) {
                items(listVideoMembatik) { data ->
                    VideoColumn(
                        image = data.image,
                        deskripsi = data.deskripsi
                    )
                }
            }
        }

    }
}



@Preview
@Composable
private fun preview() {
    val fakeVideoMembatik = listOf(
        VideoMembatik(1, R.drawable.videomembatik, "Tutorial Membatik Teknik Canting Tulis - PART 1"),
        VideoMembatik(2, R.drawable.videomembatik, "Tutorial Membatik Teknik Canting Tulis - PART 1"),
        VideoMembatik(3, R.drawable.kursus1, "Udemy"),
        VideoMembatik(4, R.drawable.kursus2, "Superprof")
    )



    BatikPediaTheme {
        EdukasiContent(
            navController = rememberNavController(),
            navigateToDetail = {  },
            listVideoMembatik = fakeVideoMembatik
        )
    }
}