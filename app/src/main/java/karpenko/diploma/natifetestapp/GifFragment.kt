package karpenko.diploma.natifetestapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import karpenko.diploma.natifetestapp.databinding.FragmentGifBinding
import karpenko.diploma.natifetestapp.pojo.OriginalImage
import kotlinx.android.synthetic.main.fragment_gif.*

class GifFragment : Fragment() {

    private lateinit var binding: FragmentGifBinding

    val originalImage: OriginalImage
        get() = requireArguments().getSerializable(GIF_URL) as OriginalImage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGifBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("GifFragment", originalImage.url)
        Log.d("GifFragment", originalImage.toString())
        Glide.with(requireContext())
            .load(originalImage.url)
            .into(ivGifFull)


    }

    companion object {

        private const val GIF_URL = "GifUrl"

        fun getInstance(originalImage: OriginalImage): GifFragment {
            val fragment = GifFragment()
            fragment.arguments = bundleOf("GifUrl" to originalImage)
            return fragment
        }
    }


}

