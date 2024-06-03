package com.tricakrawala.batikpedia.domain.usecase

import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaId
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogBatikItem
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogId
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataItem
import com.tricakrawala.batikpedia.domain.model.Berita
import com.tricakrawala.batikpedia.domain.model.KatalogBatik
import com.tricakrawala.batikpedia.domain.model.KursusBatik
import com.tricakrawala.batikpedia.domain.model.Nusantara
import com.tricakrawala.batikpedia.domain.model.Rekomendasi
import com.tricakrawala.batikpedia.domain.model.VideoMembatik
import com.tricakrawala.batikpedia.domain.model.Wisata
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.restapibatikpedia.data.remote.response.BeritaItem
import kotlinx.coroutines.flow.Flow

interface BatikPediaUseCase {
    suspend fun saveSession(user : UserModel)
    fun getSession(): Flow<UserModel>
    fun getAllNusantara(): Flow<List<Nusantara>>
    fun getAllRekomendasi(): Flow<List<Rekomendasi>>
    fun getAllBatik(): Flow<UiState<List<KatalogBatikItem>>>
    fun getBatikById(idBatik : Int) : Flow<UiState<KatalogId>>
    fun getAllWisata(): Flow<UiState<List<WisataItem>>>
    fun getWisataById(idWisata : Int) : Flow<UiState<WisataId>>
    fun getAllBerita() : Flow<UiState<List<BeritaItem>>>
    fun getBeritaById(idBerita : Int) : Flow<UiState<BeritaId>>
    suspend fun getProvinsiById(idProvinsi : Long) : Nusantara
    fun getAllKursus(): Flow<List<KursusBatik>>
    suspend fun getKursusById(idKursus : Long) : KursusBatik
    fun getAllVideo(): Flow<List<VideoMembatik>>
}