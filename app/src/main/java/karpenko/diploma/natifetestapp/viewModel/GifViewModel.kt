package karpenko.diploma.natifetestapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import karpenko.diploma.natifetestapp.api.ApiFactory
import karpenko.diploma.natifetestapp.database.AppDataBase
import karpenko.diploma.natifetestapp.pojo.OriginalImage

class GifViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDataBase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    val gifsList = db.allGifsDao().getAllGifs()

    fun getGifDetail(url: String): LiveData<OriginalImage>{
        return db.allGifsDao().getGif(url)
    }

    init {
        loadData()
    }

    private fun loadData() {

        val disposable = ApiFactory.apiService.getGifs(limit = 10)
            .map { it.result.map { it.images.originalImage } } // url
            .subscribeOn(Schedulers.io())
            .retry()
            .subscribe(
                {
                    //val a = it.result.map { it.images.originalImage.url }.joinToString(" , ")
                    //add.toList
                    db.allGifsDao().insertGifsList(it)
                    Log.d("ALLGIFSVM", it.toString())
                },
                {
                    Log.d("ALLGIFSVM", it.message.toString())
                })

        compositeDisposable.add(disposable)

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}