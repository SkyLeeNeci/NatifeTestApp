package karpenko.diploma.natifetestapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import karpenko.diploma.natifetestapp.adapter.AllGifsAdapter
import karpenko.diploma.natifetestapp.api.ApiFactory
import karpenko.diploma.natifetestapp.databinding.FragmentAllGifsBinding
import karpenko.diploma.natifetestapp.pojo.OriginalImage
import karpenko.diploma.natifetestapp.viewModel.GifViewModel
import kotlinx.android.synthetic.main.fragment_all_gifs.*
import java.util.concurrent.TimeUnit

class AllGifsFragment : Fragment() {

    private lateinit var viewModel: GifViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentAllGifsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_all_gifs, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AllGifsAdapter(requireContext())
        adapter.onGifClickListener = object : AllGifsAdapter.OnGifClickListener {
            override fun onGifClick(originalImage: OriginalImage) {

                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainFragmentContainer, GifFragment.getInstance(originalImage))
                    .commit()

                Log.d("ALLGIFSBUNDLE", originalImage.toString())

            }

        }
        rvGifs.adapter = adapter
        rvGifs.layoutManager = GridLayoutManager(requireContext(), 2)
        rvGifs.hasFixedSize()

        viewModel = ViewModelProvider(this)[GifViewModel::class.java]
        viewModel.gifsList.observe(viewLifecycleOwner, Observer {
            adapter?.gifsList = it
            Log.d("ALLGIFSDB", "Success $it")
        })

    }
}