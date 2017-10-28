package com.chingtech.sample;

import com.chingtech.sample.bean.AFanDaBaseBean;
import com.chingtech.sample.bean.Calendar;
import com.chingtech.sample.bean.CarBean;
import com.chingtech.sample.bean.JiSuBaseBean;
import com.chingtech.sample.bean.JieQiBean;
import com.chingtech.sample.bean.JieQiListBean;
import com.chingtech.sample.bean.JztkBean;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MyLibrary
 * Package com.chingtech.sample
 * Description:
 * Created by 师春雷
 * Created at 17/9/17 下午12:45
 */
public interface ApiService {

    /**
     * 驾照题库
     *
     * @param key
     * @param subject  选择考试科目类型，1：科目1；4：科目4
     * @param model    驾照类型，可选择参数为：c1,c2,a1,a2,b1,b2；当subject=4时可省略
     * @param testType 测试类型，rand：随机测试（随机100个题目），order：顺序测试（所选科目全部题目）
     * @return
     */
    @GET(Constant.JZTK)
    Observable<AFanDaBaseBean<List<JztkBean>>> getJztk(@Query("key") String key,
            @Query("subject") String subject, @Query("model") String model,
            @Query("testType") String testType);

    /**
     * 车型大全
     *
     * @param appkey
     * @return
     */
    @GET(Constant.CAR)
    Observable<JiSuBaseBean<List<CarBean>>> getCarList(@Query("appkey") String appkey);

    /**
     * 万年历
     *
     * @param appkey
     * @param date   YYYY-MM-DD
     * @return
     */
    @GET(Constant.CALENDAR)
    Observable<JiSuBaseBean<Calendar>> getCalendar(@Query("appkey") String appkey,
            @Query("date") String date);

    /**
     * 二十四节气
     *
     * @param appkey
     * @param year   YYYY
     * @return
     */
    @GET(Constant.JIEQI_LIST)
    Observable<JiSuBaseBean<JieQiListBean>> getJieQiList(@Query("appkey") String appkey,
            @Query("year") String year);

    /**
     * 二十四节气详情
     *
     * @param appkey
     * @param year    YYYY
     * @param jieqiid
     * @return
     */
    @GET(Constant.JIEQI_INFO)
    Observable<JiSuBaseBean<JieQiBean>> getJieQiInfo(@Query("appkey") String appkey,
            @Query("year") String year, @Query("jieqiid") String jieqiid);
}
