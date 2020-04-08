package com.vicky7230.newsapp.ui.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vicky7230.newsapp.R
import com.vicky7230.newsapp.data.network.Article
import com.vicky7230.newsapp.data.network.Resource
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_source_news.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment(), NewsAdapter.Callback {

    companion object {
        val SOURCE_ID = "id"
        val SOURCE_NAME = "name"
        val NEWS_LIST = "news"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var newsAdapter: NewsAdapter

    lateinit var newsViewModel: NewsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_source_news, container, false)
        newsViewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter.setCallback(this)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {

        news_list.layoutManager = LinearLayoutManager(context)
        news_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        news_list.adapter = newsAdapter

        val newsList = savedInstanceState?.getParcelableArrayList<Article>(NEWS_LIST)

        if (newsList != null) {
            progress.visibility = View.GONE
            news_list.visibility = View.VISIBLE
            newsAdapter.updateItems(newsList)
            return
        }

        newsViewModel.news.observe(this.viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> progress.visibility = View.VISIBLE
                is Resource.Error -> {
                    progress.visibility = View.GONE
                }
                is Resource.Success -> {
                    progress.visibility = View.GONE
                    news_list.visibility = View.VISIBLE
                    newsAdapter.updateItems(it.data)
                }
            }
        })

        arguments?.takeIf { it.containsKey(SOURCE_ID) }?.apply {
            newsViewModel.getNews(getString(SOURCE_ID))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(NEWS_LIST, newsAdapter.getItems())
        super.onSaveInstanceState(outState)
    }

    override fun onItemClick(article: Article) {
        arguments?.takeIf { it.containsKey(SOURCE_NAME) }?.apply {
            startActivity(
                WebViewUI.getStartIntent(
                    context!!,
                    article.url,
                    getString(SOURCE_NAME)!!
                )
            )
        }
    }
}
