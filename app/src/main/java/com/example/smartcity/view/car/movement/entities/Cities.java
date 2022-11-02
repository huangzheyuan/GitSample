package com.example.smartcity.view.car.movement.entities;

import java.util.List;

public class Cities {

    private String provinceCode;
    private String provinceName;
    private List<MallCityListBean> mallCityList;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<MallCityListBean> getMallCityList() {
        return mallCityList;
    }

    public void setMallCityList(List<MallCityListBean> mallCityList) {
        this.mallCityList = mallCityList;
    }

    public static class MallCityListBean {

        private String cityCode;
        private String cityName;
        private List<MallAreaListBean> mallAreaList;

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public List<MallAreaListBean> getMallAreaList() {
            return mallAreaList;
        }

        public void setMallAreaList(List<MallAreaListBean> mallAreaList) {
            this.mallAreaList = mallAreaList;
        }

        public static class MallAreaListBean {

            private String areaCode;
            private String areaName;

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }
        }
    }
}
