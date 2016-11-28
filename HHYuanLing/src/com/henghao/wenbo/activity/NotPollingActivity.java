package com.henghao.wenbo.activity;

import android.os.Bundle;
import android.view.View;

import com.benefit.buy.library.views.xlistview.XListView;
import com.henghao.wenbo.ActivityFragmentSupport;
import com.henghao.wenbo.R;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 未巡检界面
 * @author zhangxianwen
 * @version HDMNV100R001, 2015年8月15日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NotPollingActivity extends ActivityFragmentSupport {

	@ViewInject(R.id.listview)
	private XListView mXListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mActivityFragmentView.viewMain(R.layout.common_xlistview);
		this.mActivityFragmentView.viewEmpty(R.layout.activity_empty);
		this.mActivityFragmentView.viewEmptyGone();
		this.mActivityFragmentView.viewLoading(View.GONE);
		this.mActivityFragmentView.clipToPadding(true);
		setContentView(this.mActivityFragmentView);
		com.lidroid.xutils.ViewUtils.inject(this);
		initWidget();
		initData();
	}

	@Override
	public void initData() {
//		List<String> mList = new ArrayList<String>();
//		for (int i = 0; i < 10; i++) {
//			mList.add("测试");
//		}
//		NotPollingAdapter mAdapter = new NotPollingAdapter(this, mList);
//		this.mXListView.setAdapter(mAdapter);
//		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void initWidget() {
		initWithContent();
		this.mXListView.stopRefresh();
		this.mXListView.stopLoadMore();
	}

	private void initWithContent() {
		// TODO Auto-generated method stub
		/** 导航栏 */
		initWithBar();
		this.mLeftTextView.setText("巡检");
		this.mLeftTextView.setVisibility(View.VISIBLE);
	}
}
