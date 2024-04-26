package com.example.final_book_explorer_project.textbook_handlers;

public class PageFlipper {
    private int currentPageIndex = 0;
    private final int pageCount;

    public PageFlipper(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void nextPage() {
        if (currentPageIndex < pageCount - 1) {
            currentPageIndex++;
        }
    }

    public void previousPage() {
        if (currentPageIndex > 0) {
            currentPageIndex--;
        }
    }

    public void goToPage(int pageIndex) {
        if (pageIndex >= 0 && pageIndex < pageCount) {
            currentPageIndex = pageIndex;
        }
    }
}
