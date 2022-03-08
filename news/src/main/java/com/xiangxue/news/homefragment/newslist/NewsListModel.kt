package com.xiangxue.news.homefragment.newslist

import com.xiangxue.base.compose.composablemodel.IBaseComposableModel
import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.news.homefragment.api.NewsListBean
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.network.apiresponse.NetworkResponse
import com.xiangxue.network.TecentNetworkWithoutEnvelopeApi
import com.xiangxue.news.homefragment.api.NewsApiInterface
import com.xiangxue.news.homefragment.newslist.composables.titlecomposable.TitleComposableModel
import com.xiangxue.news.homefragment.newslist.composables.titlepicturecomposable.TitlePictureComposableModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
class NewsListModel(
    viewModelScope: CoroutineScope,
    private val channelId: String?,
    private val channelName: String?,
    iBaseModelListener: IBaseModelListener<List<IBaseComposableModel>>
) : BaseMvvmModel<NewsListBean, List<IBaseComposableModel>>(
    viewModelScope,
    iBaseModelListener,
    true,
    mCachedPreferenceKey = "news_list_model_${channelId}"
) {

    override suspend fun load() {
        val newsListBean = TecentNetworkWithoutEnvelopeApi.getService(
            NewsApiInterface::class.java
        ).getNewsList(channelId, channelName, mPageNumber.toString())
        when (newsListBean) {
            is NetworkResponse.Success -> {
                onDataTransform(newsListBean.body, false)
            }
            is NetworkResponse.ApiError -> {
                onFailure(newsListBean.body.toString())
            }
            is NetworkResponse.NetworkError -> {
                onFailure(newsListBean.message.toString())
            }
            is NetworkResponse.UnknownError -> {
                onFailure(newsListBean.error?.message)
            }
        }
    }

    override fun onFailure(e: String?) {
        notifyFailureToListener(e)
    }

    override fun onDataTransform(t: NewsListBean, isFromCache: Boolean) {
        val baseViewModels: ArrayList<IBaseComposableModel> = ArrayList<IBaseComposableModel>()
        for (source in (t)!!.pagebean!!.contentlist!!) {
            if (source.imageurls != null && source.imageurls.size > 1) {
                val viewModel =
                    TitlePictureComposableModel(
                        source.title ?: "",
                        source.imageurls[0].url ?: "",
                        source.channelId ?: "",
                        source.channelName ?: "",
                        source.link ?: "",
                        source.pubDate ?: "",
                        source.source ?: "",
                        ""
                    )
                baseViewModels.add(viewModel)
            } else {
                val viewModel = TitleComposableModel(
                    source.title ?: "",
                    source.channelId ?: "",
                    source.channelName ?: "",
                    source.link ?: "",
                    source.pubDate ?: "",
                    source.source ?: ""
                )
                baseViewModels.add(viewModel)
            }
        }
        notifyResultsToListener(t, baseViewModels, isFromCache)
    }
}