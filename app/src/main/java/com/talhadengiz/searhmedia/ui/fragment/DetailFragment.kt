package com.talhadengiz.searhmedia.ui.fragment

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
import com.talhadengiz.searhmedia.R
import com.talhadengiz.searhmedia.data.model.Result
import com.talhadengiz.searhmedia.databinding.FragmentDetailBinding
import com.talhadengiz.searhmedia.viewModel.DetailFragmentVM
import com.talhadengiz.searhmedia.util.*

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

    @SuppressLint("SetJavaScriptEnabled", "SetTextI18n")
    private fun setData() {
        when (media) {
            "movie" -> {
                binding.iMovie.apply {
                    detailCardview.ivItemDetail.downloadFromUrl(
                        search.artworkUrl100,
                        placeHolderProgressBar(requireContext())
                    )

                    detailCardview.tvCollectionName.text = search.collectionName ?: search.trackName
                    detailCardview.tvCollectionPrice.text =
                        getString(R.string.currency, search.collectionPrice.toString())
                    detailCardview.tvReleaseDate.text =
                        search.releaseDate?.convertToDateFormat(
                            Format.FROM_DATE_FORMAT,
                            Format.TO_DATE_FORMAT
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
                    detailCardview.tvCollectionPrice.text = getString(R.string.currency, search.collectionPrice.toString())
                    detailCardview.tvReleaseDate.text = search.releaseDate?.convertToDateFormat(
                        Format.FROM_DATE_FORMAT,
                        Format.TO_DATE_FORMAT
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
                    search.trackViewUrl?.let { webView.loadUrl(it) }
                }
            }
            "ebook" -> {
                binding.iBook.apply {
                    detailCardview.ivItemDetail.downloadFromUrl(
                        search.artworkUrl100,
                        placeHolderProgressBar(requireContext())
                    )

                    detailCardview.tvCollectionName.text = search.trackName
                    detailCardview.tvCollectionPrice.text = getString(R.string.currency, search.price.toString())
                    detailCardview.tvReleaseDate.text = search.releaseDate?.convertToDateFormat(
                        Format.FROM_DATE_FORMAT,
                        Format.TO_DATE_FORMAT
                    )
                    tvPrimaryGenreName.text = search.primaryGenreName ?: search.trackName
                    tvLongDescription.text = search.description?.fromHtml()
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