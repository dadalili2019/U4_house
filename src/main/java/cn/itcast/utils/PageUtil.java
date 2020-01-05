package cn.itcast.utils;

/**
 * @Author caoqian
 * @Date 2019/12/20 19:28
 * @Version 1.0
 */
//easyUI传递的分页参数
public class PageUtil {

    private Integer page;
    private Integer rows;

    public PageUtil(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }

    public PageUtil() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "page=" + page +
                ", rows=" + rows +
                '}';
    }
}
