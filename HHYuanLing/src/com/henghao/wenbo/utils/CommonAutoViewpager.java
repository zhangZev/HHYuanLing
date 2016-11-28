package com.henghao.wenbo.utils;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import com.benefit.buy.library.viewpagerindicator.CirclePageIndicator;
import com.henghao.wenbo.ActivityFragmentSupport;
import com.henghao.wenbo.R;
import com.henghao.wenbo.adapter.ImagePagerAdapter;
import com.henghao.wenbo.views.viewpage.AutoScrollViewPager;

/**
 * 更方便我们设置滚动图 〈一句话功能简述〉 〈功能详细描述〉
 * @author Administrator
 * @version HDMNV100R001, 2016年8月26日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CommonAutoViewpager {

	private static CirclePageIndicator indicator;

	private static AutoScrollViewPager mPager;

	public static void setViewpager(ActivityFragmentSupport mContext) {
		mPager = (AutoScrollViewPager) mContext.findViewById(R.id.view_pager);
		indicator = (CirclePageIndicator) mContext.findViewById(R.id.indicator);
		mPager.setInterval(2000);
		mPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_TO_PARENT);
		indicator.setOnPageChangeListener(new HomeOnPageChangeListener());
		mPager.startAutoScroll();
		mPager.setCurrentItem(0);
		viewPageAdapter(mContext, mPager, indicator);
	}

	public static void setViewpager(ActivityFragmentSupport mactivity, View mContext) {
		mPager = (AutoScrollViewPager) mContext.findViewById(R.id.view_pager);
		indicator = (CirclePageIndicator) mContext.findViewById(R.id.indicator);
		mPager.setInterval(2000);
		mPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_TO_PARENT);
		indicator.setOnPageChangeListener(new HomeOnPageChangeListener());
		mPager.startAutoScroll();
		mPager.setCurrentItem(0);
		viewPageAdapter(mactivity, mPager, indicator);
	}

	public static void viewPageAdapter(ActivityFragmentSupport mContext, AutoScrollViewPager viewPager,
	        CirclePageIndicator indicator) {
		/** 广告滚动 **/
		List<Integer> imageIdList = new ArrayList<Integer>();
		imageIdList.add(R.drawable.img_loading_default_big);
		imageIdList.add(R.drawable.img_loading_default_big);
		// imageIdList.add(R.drawable.images_icon);
		indicator.setVisibility(View.VISIBLE);
		viewPager.startAutoScroll();
		if (imageIdList.size() == 1) {
			indicator.setVisibility(View.GONE);
			viewPager.stopAutoScroll();
		}
		viewPager.setAdapter(new ImagePagerAdapter(mContext, imageIdList));
		indicator.setViewPager(viewPager);
	}

	public static class HomeOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int position) {
			// indicator.setSelectStrokeWidth(20);
			// indicator.setUnSelectStrokeWidth(10);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
}
