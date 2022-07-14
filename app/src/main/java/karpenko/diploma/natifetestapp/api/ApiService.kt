package karpenko.diploma.natifetestapp.api

import io.reactivex.rxjava3.core.Single
import karpenko.diploma.natifetestapp.pojo.DataResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // https://api.giphy.com/v1/gifs/trending?api_key=diP82O9KXCBVoruTobb46sKyul25RcxP&q=&limit=25&offset=0&rating=g&lang=en
    @GET("gifs/trending")
    fun getGifs(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "diP82O9KXCBVoruTobb46sKyul25RcxP",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 40,
        @Query(QUERY_PARAM_LANG) lang: String = "en",
        @Query(QUERY_PARAM_OFFSET) offset: Int = 0,
        @Query(QUERY_PARAM_RATING) rating: String = "g"
    ): Single<DataResult>



    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_LANG = "lang"
        private const val QUERY_PARAM_OFFSET = "offset"
        private const val QUERY_PARAM_RATING = "rating"
    }
}