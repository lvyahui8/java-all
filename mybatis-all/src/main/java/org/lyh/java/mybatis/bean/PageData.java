package org.lyh.java.mybatis.bean;


import org.lyh.java.mybatis.model.BaseModel;

import java.util.List;

/**
 *
 * Created by lvyahui on 2015/7/12.
 */
@SuppressWarnings("unused")
public class PageData<T extends BaseModel> {

    /**
     * 前端做分页，所以这里limit设置的非常大，相当于不分页
     */
    public static final int DEFAULT_SIZE = 1000;

    private List<T> datas;

    private int currentPage = 1;

    private int totalPage;

    private int totalItem;


    private int maxBtnCount = 10;

    private int pageSize = DEFAULT_SIZE;

    private int start = 1;
    private int end;

    /**
     * 总项目数
     */
    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
        paging();
    }

    private void paging() {
        totalPage = (int) Math.ceil(((double) totalItem) / pageSize);
        if(totalPage > maxBtnCount){
            if(currentPage <= (maxBtnCount-1)/2){
                // 靠近首页
                start = 1;
            }else if(totalPage-currentPage < (maxBtnCount-1)/2){
                // 靠近尾页
                start = totalPage - maxBtnCount - 1;
            }else{
                start = currentPage - (maxBtnCount-1)/2;
            }
            end = maxBtnCount-1 + start > totalPage ? totalPage : maxBtnCount - 1 + start;
        }else{
            end = totalPage;
        }
//        System.out.println("start:"+start+",end:"+end);
    }

    /**
     * 总页数
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 当前页
     */
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 页面数据
     */
    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    /**
     * 每页大小，可放多少个项，默认为10
     */


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return 最大分页按钮数，默认值为10
     */
    public int getMaxBtnCount() {
        return maxBtnCount;
    }

    public void setMaxBtnCount(int maxBtnCount) {
        this.maxBtnCount = maxBtnCount;
    }

    /**
     * @return  第一个按钮的页号
     */
    public int getStart() {
        return start;
    }

    /**
     * @return 最后一个按钮上的页号
     */
    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }


    private String listUrl;

    public String getListUrl() {
        return listUrl;
    }

    public void setListUrl(String listUrl) {
        this.listUrl = listUrl;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "datas_size="+ (datas != null ? datas.size() : 0) +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalItem=" + totalItem +
                ", maxBtnCount=" + maxBtnCount +
                ", pageSize=" + pageSize +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

}