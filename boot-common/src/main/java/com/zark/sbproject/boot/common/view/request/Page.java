package com.zark.sbproject.boot.common.view.request;

import java.util.List;

public class Page<T> {

    /**
     * 每页显示记录数
     */
    private int length;

    /**
     * 查询结果总记录数
     */
    private int count;

    /**
     * 当前页码
     */
    private int currentPage;

    private List<T> data;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Page() {
    }

    public Page(int length, int count, int currentPage, List<T> data) {
        this.length = length;
        this.count = count;
        this.currentPage = currentPage;
        this.data = data;
    }
}
