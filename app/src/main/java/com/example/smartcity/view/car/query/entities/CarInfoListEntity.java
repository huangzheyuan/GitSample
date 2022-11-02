package com.example.smartcity.view.car.query.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class CarInfoListEntity {


    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1,"plateNum":"京 A888888","mainNum":"JD12121991DSX223332","carType":"轿车","mileage":"10000000","phone":"139010888813","userId":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"plateNum":"京AL1111","mainNum":"JD12121991DSX88888","carType":"大货车","mileage":"10000000","phone":"139010111111","userId":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":17,"plateNum":"京 A888888","mainNum":"JD12121991DSX223332","carType":"轿车","mileage":"10000000","phone":"139010888813","userId":1}]
     * code : 200
     * msg : 查询成功
     */

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
        @JsonProperty("userId")
        private Integer userId;
        @JsonProperty("plateNo")
        private String plateNo;
        @JsonProperty("engineNo")
        private String engineNo;
        @JsonProperty("type")
        private String type;
        @JsonProperty("userName")
        private String userName;


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

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getPlateNo() {
            return plateNo;
        }

        public void setPlateNo(String plateNo) {
            this.plateNo = plateNo;
        }

        public String getEngineNo() {
            return engineNo;
        }

        public void setEngineNo(String engineNo) {
            this.engineNo = engineNo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public static Creator<RowsBean> getCREATOR() {
            return CREATOR;
        }

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
            if (in.readByte() == 0) {
                userId = null;
            } else {
                userId = in.readInt();
            }
            plateNo = in.readString();
            engineNo = in.readString();
            type = in.readString();
            userName = in.readString();
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
            if (userId == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(userId);
            }
            dest.writeString(plateNo);
            dest.writeString(engineNo);
            dest.writeString(type);
            dest.writeString(userName);
        }


        public static class ParamsDTO {
        }
    }

}
