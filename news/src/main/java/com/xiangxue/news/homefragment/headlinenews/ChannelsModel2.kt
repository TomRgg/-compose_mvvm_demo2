package com.xiangxue.news.homefragment.headlinenews

import com.xiangxue.base.mvvm.model.BaseMvvmModel
import com.xiangxue.base.mvvm.model.IBaseModelListener
import com.xiangxue.network.WanAndroidNetworkWithoutEnvelopeApi
import com.xiangxue.network.apiresponse.NetworkResponse
import com.xiangxue.news.homefragment.api.NewsChannelsBean
import com.xiangxue.news.homefragment.api.ProjectServe
import com.xiangxue.news.homefragment.api.ProjectTabBean
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
const val PROJECT_TAB_CHANNELS: String =
    "[\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 294,\n" +
            "            \"name\": \"完整项目\",\n" +
            "            \"order\": 145000,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 0\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 402,\n" +
            "            \"name\": \"跨平台应用\",\n" +
            "            \"order\": 145001,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 367,\n" +
            "            \"name\": \"资源聚合类\",\n" +
            "            \"order\": 145002,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 323,\n" +
            "            \"name\": \"动画\",\n" +
            "            \"order\": 145003,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 314,\n" +
            "            \"name\": \"RV列表动效\",\n" +
            "            \"order\": 145004,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 358,\n" +
            "            \"name\": \"项目基础功能\",\n" +
            "            \"order\": 145005,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 328,\n" +
            "            \"name\": \"网络&amp;文件下载\",\n" +
            "            \"order\": 145011,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 331,\n" +
            "            \"name\": \"TextView\",\n" +
            "            \"order\": 145013,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 336,\n" +
            "            \"name\": \"键盘\",\n" +
            "            \"order\": 145015,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 337,\n" +
            "            \"name\": \"快应用\",\n" +
            "            \"order\": 145016,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 338,\n" +
            "            \"name\": \"日历&amp;时钟\",\n" +
            "            \"order\": 145017,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 339,\n" +
            "            \"name\": \"K线图\",\n" +
            "            \"order\": 145018,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 340,\n" +
            "            \"name\": \"硬件相关\",\n" +
            "            \"order\": 145019,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 357,\n" +
            "            \"name\": \"表格类\",\n" +
            "            \"order\": 145022,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 363,\n" +
            "            \"name\": \"创意汇\",\n" +
            "            \"order\": 145024,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 380,\n" +
            "            \"name\": \"ImageView\",\n" +
            "            \"order\": 145029,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 382,\n" +
            "            \"name\": \"音视频&amp;相机\",\n" +
            "            \"order\": 145030,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 383,\n" +
            "            \"name\": \"相机\",\n" +
            "            \"order\": 145031,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 310,\n" +
            "            \"name\": \"下拉刷新\",\n" +
            "            \"order\": 145032,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 385,\n" +
            "            \"name\": \"架构\",\n" +
            "            \"order\": 145033,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 387,\n" +
            "            \"name\": \"对话框\",\n" +
            "            \"order\": 145035,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 388,\n" +
            "            \"name\": \"数据库\",\n" +
            "            \"order\": 145036,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 391,\n" +
            "            \"name\": \"AS插件\",\n" +
            "            \"order\": 145037,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 400,\n" +
            "            \"name\": \"ViewPager\",\n" +
            "            \"order\": 145039,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 401,\n" +
            "            \"name\": \"二维码\",\n" +
            "            \"order\": 145040,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 312,\n" +
            "            \"name\": \"富文本编辑器\",\n" +
            "            \"order\": 145041,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 526,\n" +
            "            \"name\": \"IM\",\n" +
            "            \"order\": 145042,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [],\n" +
            "            \"courseId\": 13,\n" +
            "            \"id\": 539,\n" +
            "            \"name\": \"未分类\",\n" +
            "            \"order\": 145043,\n" +
            "            \"parentChapterId\": 293,\n" +
            "            \"userControlSetTop\": false,\n" +
            "            \"visible\": 1\n" +
            "        }\n" +
            "    ]"

class ChannelsModel2(
    viewModelScope: CoroutineScope,
    iBaseModelListener: IBaseModelListener<List<ProjectTabBean>>
) :
    BaseMvvmModel<List<ProjectTabBean>, List<ProjectTabBean>>(
        viewModelScope,
        iBaseModelListener,
//        mCachedPreferenceKey = "channel_project_tab_pref_key"
//        apkPredefinedData = PROJECT_TAB_CHANNELS
    ) {

    override suspend fun load() {
        val newsChannelsBean =
            WanAndroidNetworkWithoutEnvelopeApi.getService(ProjectServe::class.java)
                .projectTab()
        when (newsChannelsBean) {
            is NetworkResponse.Success -> {
                onDataTransform(newsChannelsBean.body, false)
            }
            is NetworkResponse.ApiError -> {
                onFailure(newsChannelsBean.body.toString())
            }
            is NetworkResponse.NetworkError -> {
                onFailure(newsChannelsBean.message.toString())
            }
            is NetworkResponse.UnknownError -> {
                onFailure(newsChannelsBean.error?.message)
            }
        }
    }

    override fun onFailure(e: String?) {
        notifyFailureToListener(e)
    }


    override fun isNeedToUpdate(cachedTimeSlot: Long): Boolean {
        return System.currentTimeMillis() - cachedTimeSlot > 24 * 60 * 60 * 1000
    }

    override fun onDataTransform(t: List<ProjectTabBean>, isFromCache: Boolean) {
        for (item in t) {
            item.name?.let {
                item.name = item.name.replace("amp;", "")
            }
        }
        notifyResultsToListener(t, t, isFromCache)
    }

}