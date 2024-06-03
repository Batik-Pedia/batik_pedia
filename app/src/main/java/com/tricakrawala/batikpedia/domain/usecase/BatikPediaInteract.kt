package com.tricakrawala.batikpedia.domain.usecase

import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaId
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogBatikItem
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogId
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiId
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiItem
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataItem
import com.tricakrawala.batikpedia.domain.model.KursusBatik
import com.tricakrawala.batikpedia.domain.model.Rekomendasi
import com.tricakrawala.batikpedia.domain.model.VideoMembatik
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.restapibatikpedia.data.remote.response.BeritaItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BatikPediaInteract @Inject constructor(private val repository: BatikRepository) : BatikPediaUseCase {
    override suspend fun saveSession(user: UserModel) =  repository.saveSession(user)

    override fun getSession(): Flow<UserModel> = repository.getSession()

    override fun getAllNusantara(): Flow<UiState<List<ProvinsiItem>>> = repository.getAllNusantara()

    override fun getAllRekomendasi(): Flow<List<Rekomendasi>> = repository.getAllRekomendasi()

    override fun getAllBatik(): Flow<UiState<List<KatalogBatikItem>>> = repository.getAllBatik()

    override  fun getBatikById(idBatik: Int): Flow<UiState<KatalogId>> = repository.getBatikById(idBatik)

    override fun getAllWisata(): Flow<UiState<List<WisataItem>>> = repository.getAllWisata()

    override fun getWisataById(idWisata: Int): Flow<UiState<WisataId>> = repository.getWisataById(idWisata)
    override fun getAllBerita(): Flow<UiState<List<BeritaItem>>> = repository.getAllBerita()

    override fun getBeritaById(idBerita: Int): Flow<UiState<BeritaId>> = repository.getBeritaById(idBerita)

    override fun getProvinsiById(idProvinsi: Int): Flow<UiState<ProvinsiId>> = repository.getProvinsiById(idProvinsi)

    override fun getAllKursus(): Flow<List<KursusBatik>> = repository.getAllKursus()

    override suspend fun getKursusById(idKursus: Long): KursusBatik = repository.getKursusById(idKursus)

    override fun getAllVideo(): Flow<List<VideoMembatik>> = repository.getAllVideo()
}