package ssm.utils;

import org.apache.ibatis.annotations.CacheNamespace;

import java.io.Serializable;

/**
 * @ClassName page
 * @Description TODD
 * @Autuor yh139
 * @Date 10/27/2021 11:28 AM
 * @Version 1.0
 **/
@CacheNamespace
public class Page implements Serializable {

    private static final long serialVersionUID = -3198048449643774660L;

    private long pageNow = 1; // 当前页
    private long pageSize = 4; // 每页显示记录的long
    private long totalCount; // 总的记录long
    private long totalPageCount; // 总的页数（总的记录数/每页的页数）
    @SuppressWarnings("unused")
    private long startPos; // 开始位置，从0开始

    @SuppressWarnings("unused")
    private boolean hasFirst;// 是否有首页

    @SuppressWarnings("unused")
    private boolean hasPre;// 是否有前一页

    @SuppressWarnings("unused")
    private boolean hasNext;// 是否有下一页

    @SuppressWarnings("unused")
    private boolean hasLast;// 是否有最后一页

    /**
     * 通过构造函数 传入  总记录数  和  当前页
     */
    public Page() {
        this.totalCount = totalCount;
        this.pageNow = pageNow;
    }

    /**
     * 取得总页数，总页数=总记录数/总页数
     * @return
     */
    public long getTotalPageCount() {
        totalPageCount = getTotalCount() / getPageSize();
        return (totalCount % pageSize == 0) ? totalPageCount
                : totalPageCount + 1;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public long getPageNow() {
        return pageNow;
    }

    public void setPageNow(long pageNow) {
        this.pageNow = pageNow;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
    /**
     * 取得选择记录的初始位置
     * 默认从0开始
     * @return
     */
    public long getStartPos() {
        return (pageNow - 1) * pageSize;
    }

    public void setStartPos(long startPos) {
        this.startPos = startPos;
    }

    /**
     * 是否是第一页 select是从0开始的  页面上显示的pageNow+1
     * @return
     */
    public boolean isHasFirst() {
        return (pageNow == 1) ? false : true;
    }

    public void setHasFirst(boolean hasFirst) {
        this.hasFirst = hasFirst;
    }
    /**
     * 是否有首页
     * @return
     */
    public boolean isHasPre() {
        // 如果有首页就有前一页，因为有首页就不是第一页
        return isHasFirst() ? true : false;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }
    /**
     * 是否有下一页
     * @return
     */
    public boolean isHasNext() {
        // 如果有尾页就有下一页，因为有尾页表明不是最后一页
        return isHasLast() ? true : false;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
    /**
     * 是否有尾页
     * @return
     */
    public boolean isHasLast() {
        // 如果不是最后一页就有尾页
        return (pageNow == getTotalCount()) ? false : true;
    }

    public void setHasLast(boolean hasLast) {
        this.hasLast = hasLast;
    }

}
