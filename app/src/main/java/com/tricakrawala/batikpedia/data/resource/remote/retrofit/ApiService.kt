package com.tricakrawala.batikpedia.data.resource.remote.retrofit

import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaIdResponse
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogIdResponse
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogResponse
import com.tricakrawala.batikpedia.data.resource.remote.response.KursusResponse
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiIdResponse
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiResponse
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataIdResponse
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataResponse
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("berita")
    suspend fun getAllBerita() : BeritaResponse

    @GET("berita/{idBerita}")
    suspend fun getDetailBerita(@Path("idBerita") idBerita: Int) : BeritaIdResponse

    @GET("katalog")
    suspend fun getKatalogBatik() : KatalogResponse

    @GET("katalog/{idBatik}")
    suspend fun getDetailBatik(@Path("idBatik")idBatik : Int) : KatalogIdResponse

    @GET("wisata")
    suspend fun getAllWisata(): WisataResponse

    @GET("wisata/{idWisata}")
    suspend fun getDetailWisata(@Path("idWisata")idWisata : Int) : WisataIdResponse

    @GET("provinsi")
    suspend fun getAllProvinsi() : ProvinsiResponse
    @GET("provinsi/{idProvinsi}")
    suspend fun getDetailProvinsi(@Path("idProvinsi")idProvinsi : Int) : ProvinsiIdResponse

    @GET("kursus")
    suspend fun getAllKursus() : KursusResponse
}