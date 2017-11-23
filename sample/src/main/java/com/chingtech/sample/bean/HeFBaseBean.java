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
 * Created at 17/11/23 下午5:17
 */
public class HeFBaseBean {

    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101010100","location":"北京","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.90498734","lon":"116.40528870","tz":"8.0"}
         * now : {"cond_code":"101","cond_txt":"多云","fl":"16","hum":"73","pcpn":"0","pres":"1017","tmp":"14","vis":"1","wind_deg":"11","wind_dir":"北风","wind_sc":"微风","wind_spd":"6"}
         * status : ok
         * update : {"loc":"2017-10-26 17:29","utc":"2017-10-26 09:29"}
         */

        private BasicBean           basic;
        private NowBean             now;
        private String              status;
        private UpdateBean          update;
        private List<DailyForecast> daily_forecast;
        private List<Hourly> hourly;
        private List<Lifestyle> lifestyle;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public List<DailyForecast> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecast> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class UpdateBean {
            /**
             * loc : 2017-10-26 17:29
             * utc : 2017-10-26 09:29
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }
    }
}
