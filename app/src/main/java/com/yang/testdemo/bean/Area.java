package com.yang.testdemo.bean;

import java.util.List;

/**
 * Created by yangle on 2017/5/17.
 */

public class Area {
    /**
     * name : 中国
     * province : [{"name":"黑龙江","cities":{"city":["哈尔滨","大庆"]}},{"name":"广东","cities":{"city":["广州","深圳","珠海"]}},{"name":"台湾","cities":{"city":["台北","高雄"]}},{"name":"新疆","cities":{"city":["乌鲁木齐"]}}]
     */

    private String name;
    private List<ProvinceBean> province;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProvinceBean> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceBean> province) {
        this.province = province;
    }

    public static class ProvinceBean {
        /**
         * name : 黑龙江
         * cities : {"city":["哈尔滨","大庆"]}
         */

        private String name;
        private CitiesBean cities;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public CitiesBean getCities() {
            return cities;
        }

        public void setCities(CitiesBean cities) {
            this.cities = cities;
        }

        public static class CitiesBean {
            private List<String> city;

            public List<String> getCity() {
                return city;
            }

            public void setCity(List<String> city) {
                this.city = city;
            }
        }
    }
}
