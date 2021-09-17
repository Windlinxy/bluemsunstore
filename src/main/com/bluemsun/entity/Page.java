package com.bluemsun.entity;

import java.util.List;

public class Page<T> {
    private String keyword;
    private int currentPage;
    private int pageSize;
    private int totalRecord;
    List<T> list; //用于存放数据库中的数据结果集,使用泛型，增强通用性
    private int totalPage;
    private int startIndex;

    public Page(int currentPage, int pageSize, int totalRecord) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;

        if (totalRecord % pageSize == 0) {// 总页数 = [总记录数/页面大小]，如果不是整除需要+1
            this.totalPage = totalRecord / pageSize;
        } else {
            this.totalPage = totalRecord / pageSize + 1;
        }
        this.startIndex = currentPage * pageSize - pageSize; //计算起始页号，（当前页号-1）*页面大小
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalRecord=" + totalRecord +
                ", list=" + list +
                ", totalPage=" + totalPage +
                ", startIndex=" + startIndex +
                '}';
    }
}
