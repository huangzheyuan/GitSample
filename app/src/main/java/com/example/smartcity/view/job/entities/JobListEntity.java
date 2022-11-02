package com.example.smartcity.view.job.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JobListEntity {

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
        @JsonProperty("companyId")
        private Integer companyId;
        @JsonProperty("professionId")
        private Integer professionId;
        @JsonProperty("contacts")
        private String contacts;
        @JsonProperty("name")
        private String name;
        @JsonProperty("obligation")
        private String obligation;
        @JsonProperty("address")
        private String address;
        @JsonProperty("need")
        private String need;
        @JsonProperty("salary")
        private String salary;
        @JsonProperty("companyName")
        private String companyName;
        @JsonProperty("professionName")
        private String professionName;

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

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public Integer getProfessionId() {
            return professionId;
        }

        public void setProfessionId(Integer professionId) {
            this.professionId = professionId;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getObligation() {
            return obligation;
        }

        public void setObligation(String obligation) {
            this.obligation = obligation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNeed() {
            return need;
        }

        public void setNeed(String need) {
            this.need = need;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getProfessionName() {
            return professionName;
        }

        public void setProfessionName(String professionName) {
            this.professionName = professionName;
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
                companyId = null;
            } else {
                companyId = in.readInt();
            }
            if (in.readByte() == 0) {
                professionId = null;
            } else {
                professionId = in.readInt();
            }
            contacts = in.readString();
            name = in.readString();
            obligation = in.readString();
            address = in.readString();
            need = in.readString();
            salary = in.readString();
            companyName = in.readString();
            professionName = in.readString();
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
            if (companyId == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(companyId);
            }
            if (professionId == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(professionId);
            }
            dest.writeString(contacts);
            dest.writeString(name);
            dest.writeString(obligation);
            dest.writeString(address);
            dest.writeString(need);
            dest.writeString(salary);
            dest.writeString(companyName);
            dest.writeString(professionName);
        }

        public static class ParamsDTO {
        }
    }
}
