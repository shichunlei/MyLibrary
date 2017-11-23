package com.chingtech.sample.bean;

import java.util.List;

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
 * Package com.chingtech.sample.bean
 * Description:
 * Created by 师春雷
 * Created at 17/9/17 下午7:05
 */
public class JieQiListBean {


    /**
     * now : {"name":"小寒","time":"2016-01-06 06:08:21","lunar":["2015","冬月","廿七","乙未","11","27","羊","0"]}
     * list : [{"jieqiid":"1","name":"立春","pic":"http://m.46644.com/jieqi/static/images/jieqi/lc.png","time":"2016-02-04
     * 17:46:00"},{"jieqiid":"2","name":"雨水","pic":"http://m.46644.com/jieqi/static/images/jieqi/ys.png","time":"2016-02-19
     * 13:33:41"}]
     * song : 春雨惊春清谷天，夏满芒夏暑相连。秋处露秋寒霜降，冬雪雪冬小大寒。
     */

    private NowBean        now;
    private String         song;
    private List<ListBean> list;

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class NowBean {
        /**
         * name : 小寒
         * time : 2016-01-06 06:08:21
         * lunar : ["2015","冬月","廿七","乙未","11","27","羊","0"]
         */

        private String       name;
        private String       time;
        private List<String> lunar;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<String> getLunar() {
            return lunar;
        }

        public void setLunar(List<String> lunar) {
            this.lunar = lunar;
        }
    }

    public static class ListBean {
        /**
         * jieqiid : 1
         * name : 立春
         * pic : http://m.46644.com/jieqi/static/images/jieqi/lc.png
         * time : 2016-02-04 17:46:00
         */

        private String jieqiid;
        private String name;
        private String pic;
        private String time;

        public String getJieqiid() {
            return jieqiid;
        }

        public void setJieqiid(String jieqiid) {
            this.jieqiid = jieqiid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "ListBean{"
                    + "jieqiid='"
                    + jieqiid
                    + '\''
                    + ", name='"
                    + name
                    + '\''
                    + ", pic='"
                    + pic
                    + '\''
                    + ", time='"
                    + time
                    + '\''
                    + '}';
        }
    }
}
