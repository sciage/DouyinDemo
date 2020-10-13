package com.example.administrator.douyin;

public interface OnViewPagerListener {
    /*loading finished*/
    void onInitComplete();

    /*Release monitor*/
    void onPageRelease(boolean isNext, int position);

    /*Selected monitor and judge whether to slide to the bottom*/
    void onPageSelected(int position, boolean isBottom);

}
