package run.ikaros.app.and.api.common;


import java.util.Collections;
import java.util.List;

import run.ikaros.app.and.Utils.Assert;

public class PagingWrap<T> {

    private int page;
    private int size;
    private long total;
    private List<T> items;

    /**
     * new a page result instance.
     *
     * @param page  page
     * @param size  size
     * @param total total
     * @param items item list
     */
    public PagingWrap(int page, int size, long total, List<T> items) {
        Assert.isTrue(total >= 0, "Total elements must be greater than or equal to 0");
        if (page < 0) {
            page = 0;
        }
        if (size < 0) {
            size = 0;
        }
        if (items == null) {
            items = Collections.emptyList();
        }
        this.page = page;
        this.size = size;
        this.total = total;
        this.items = items;
    }

    public PagingWrap(List<T> items) {
        this(0, 0, items.size(), items);
    }

    public boolean isFirstPage() {
        return !hasPrevious();
    }

    public boolean isLastPage() {
        return !hasNext();
    }

    /**
     * if this has next.
     *
     * @return true if this has next
     */
    public boolean hasNext() {
        if (page <= 0) {
            return false;
        }
        return page < getTotalPages();
    }

    public boolean hasPrevious() {
        return page > 1;
    }


    public long getTotalPages() {
        return size == 0 ? 1 : (total + size - 1) / size;
    }


    public static <T> PagingWrap<T> emptyResult() {
        return new PagingWrap<>(List.of());
    }


    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
