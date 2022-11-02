package com.example.smartcity.view.house.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class HouseListEntity {

    private Integer total;
    private Integer code;
    private String msg;
    private List<RowsBean> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Parcelable {


        @JsonProperty("searchValue")
        private String searchValue;
        @JsonProperty("createBy")
        private String createBy;
        @JsonProperty("createTime")
        private String createTime;
        @JsonProperty("updateBy")
        private String updateBy;
        @JsonProperty("updateTime")
        private String updateTime;
        @JsonProperty("remark")
        private String remark;
        @JsonProperty("params")
        private ParamsDTO params;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("sourceName")
        private String sourceName;
        @JsonProperty("address")
        private String address;
        @JsonProperty("areaSize")
        private Integer areaSize;
        @JsonProperty("tel")
        private String tel;
        @JsonProperty("price")
        private String price;
        @JsonProperty("houseType")
        private String houseType;
        @JsonProperty("pic")
        private String pic;
        @JsonProperty("description")
        private String description;

        protected RowsBean(Parcel in) {
            searchValue = in.readString();
            createBy = in.readString();
            createTime = in.readString();
            updateBy = in.readString();
            updateTime = in.readString();
            remark = in.readString();
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            sourceName = in.readString();
            address = in.readString();
            if (in.readByte() == 0) {
                areaSize = null;
            } else {
                areaSize = in.readInt();
            }
            tel = in.readString();
            price = in.readString();
            houseType = in.readString();
            pic = in.readString();
            description = in.readString();
        }

        public static final Creator<RowsBean> CREATOR = new Creator<RowsBean>() {
            @Override
            public RowsBean createFromParcel(Parcel in) {
                return new RowsBean(in);
            }

            @Override
            public RowsBean[] newArray(int size) {
                return new RowsBean[size];
            }
        };

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public ParamsDTO getParams() {
            return params;
        }

        public void setParams(ParamsDTO params) {
            this.params = params;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getAreaSize() {
            return areaSize;
        }

        public void setAreaSize(Integer areaSize) {
            this.areaSize = areaSize;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(searchValue);
            dest.writeString(createBy);
            dest.writeString(createTime);
            dest.writeString(updateBy);
            dest.writeString(updateTime);
            dest.writeString(remark);
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(sourceName);
            dest.writeString(address);
            if (areaSize == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(areaSize);
            }
            dest.writeString(tel);
            dest.writeString(price);
            dest.writeString(houseType);
            dest.writeString(pic);
            dest.writeString(description);
        }

        public static class ParamsDTO {
        }
    }
}
