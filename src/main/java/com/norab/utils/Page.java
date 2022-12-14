package com.norab.utils;

import java.util.Objects;

public final class Page {
    private final Integer page;
    private final Integer size;

    public Page(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public static Page of(Integer page, Integer size) {
        if (page <= 0) {
            throw new IllegalArgumentException("The page cannot be either zero or negative");
        } else if (size <= 0) {
            throw new IllegalArgumentException("The size cannot be either zero or negative");
        }
        return new Page(page, size);
    }

    public Integer getLimit() {
        return size;
    }

    public Integer getOffset() {
        return (page - 1) * size;
    }

    public Page next() {
        return Page.of(page + 1, size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Page page1 = (Page) o;
        return page == page1.page && size == page1.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size);
    }

    @Override
    public String toString() {
        return "Page{" +
            "page=" + page +
            ", size=" + size +
            '}';
    }
}
