package cn.itcast.utils;

import cn.itcast.utils.PageUtil;

/**
 * @Author caoqian
 * @ClassName SearchHouseCondition
 * @Date 2020/1/2 10:32
 * @Version 1.0
 */
//搜索房源查询条件pojo
public class SearchHouseCondition extends PageUtil {
    private String tname;//查询房屋的标题
    private Integer typeId;//查询的房屋类型id
    private Integer district_id;//查询的房屋区域信息
    private Integer streetId;//查询的街道信息id
    private Long startPrice;//查询的起始价格
    private Long endPrice;//查询的结束价格

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
    }

    public Long getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Long endPrice) {
        this.endPrice = endPrice;
    }
}
