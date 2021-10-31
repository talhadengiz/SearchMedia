package com.talhadengiz.hepsiburada.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.talhadengiz.hepsiburada.data.model.Result
import com.talhadengiz.hepsiburada.databinding.FragmentDetailBinding
import com.talhadengiz.hepsiburada.ui.viewModel.DetailFragmentVM
import com.talhadengiz.hepsiburada.util.convertToDateFormat
import com.talhadengiz.hepsiburada.util.downloadFromUrl
import com.talhadengiz.hepsiburada.util.fromHtml
import com.talhadengiz.hepsiburada.util.placeHolderProgressBar

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailFragmentVM
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var search: Result
    private lateinit var media: String
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        setData()
        showLayout(media)
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(DetailFragmentVM::class.java)
        search = args.search
        media = args.media
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setData() {
        when (media) {
            "movie" -> {
                binding.iMovie.apply {
                    detailCardview.ivItemDetail.downloadFromUrl(
                        search.artworkUrl100,
                        placeHolderProgressBar(requireContext())
                    )

                    detailCardview.tvCollectionName.text = search.collectionName
                    detailCardview.tvCollectionPrice.text = "$" + search.collectionPrice.toString()
                    detailCardview.tvReleaseDate.text =
                        search.releaseDate.convertToDateFormat(
                            "yyyy-MM-dd'T'HH:mm:sss",
                            "dd.MM.yyyy"
                        )
                    tvPrimaryGenreName.text = search.primaryGenreName
                    tvLongDescription.text = search.longDescription
                    vvMedia.setVideoPath(search.previewUrl)
                    vvMedia.stopPlayback()
                    vvMedia.start()
                }
            }
            "music" -> {
                binding.iMusic.apply {
                    detailCardview.ivItemDetail.downloadFromUrl(
                        search.artworkUrl100,
                        placeHolderProgressBar(requireContext())
                    )

                    detailCardview.tvCollectionName.text = search.collectionName
                    detailCardview.tvCollectionPrice.text = search.collectionPrice.toString()
                    detailCardview.tvReleaseDate.text =
                        search.releaseDate.convertToDateFormat(
                            "yyyy-MM-dd'T'HH:mm:sss",
                            "dd.MM.yyyy"
                        )
                    tvPrimaryGenreName.text = search.primaryGenreName
                    vvMedia.setVideoPath(search.previewUrl)
                    vvMedia.stopPlayback()
                    vvMedia.start()
                }
            }
            "software" -> {
                binding.iApp.apply {
                    webView.settings.javaScriptEnabled = true
                    webView.settings.javaScriptCanOpenWindowsAutomatically = true
                    webView.webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView,
                            request: WebResourceRequest
                        ): Boolean {
                            view.loadUrl(request.url.toString())
                            return true
                        }
                    }
                    webView.loadUrl(search.trackViewUrl)
                }
            }
            "ebook" -> {
                binding.iBook.apply {
                    detailCardview.ivItemDetail.downloadFromUrl(
                        search.artworkUrl100,
                        placeHolderProgressBar(requireContext())
                    )

                    detailCardview.tvCollectionName.text = search.trackName
                    detailCardview.tvCollectionPrice.text = search.price.toString()
                    detailCardview.tvReleaseDate.text =
                        search.releaseDate.convertToDateFormat(
                            "yyyy-MM-dd'T'HH:mm:sss",
                            "dd.MM.yyyy"
                        )
                    tvPrimaryGenreName.text = search.primaryGenreName ?: search.trackName
                    tvLongDescription.text = search.description.fromHtml()
                }
            }
        }
    }

    private fun showLayout(media: String) {
        when (media) {
            "movie" -> {
                binding.iMovie.root.visibility = View.VISIBLE
                binding.iMusic.root.visibility = View.GONE
                binding.iApp.root.visibility = View.GONE
                binding.iBook.root.visibility = View.GONE
            }
            "music" -> {
                binding.iMovie.root.visibility = View.GONE
                binding.iMusic.root.visibility = View.VISIBLE
                binding.iApp.root.visibility = View.GONE
                binding.iBook.root.visibility = View.GONE
            }
            "software" -> {
                binding.iMovie.root.visibility = View.GONE
                binding.iMusic.root.visibility = View.GONE
                binding.iApp.root.visibility = View.VISIBLE
                binding.iBook.root.visibility = View.GONE
            }
            "ebook" -> {
                binding.iMovie.root.visibility = View.GONE
                binding.iMusic.root.visibility = View.GONE
                binding.iApp.root.visibility = View.GONE
                binding.iBook.root.visibility = View.VISIBLE
            }
        }
    }
}