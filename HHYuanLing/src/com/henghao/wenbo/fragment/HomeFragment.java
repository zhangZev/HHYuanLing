package com.henghao.wenbo.fragment;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benefit.buy.library.utils.tools.ToolsKit;
import com.benefit.buy.library.utils.tools.ToolsToast;
import com.benefit.buy.library.viewpagerindicator.CirclePageIndicator;
import com.henghao.wenbo.R;
import com.henghao.wenbo.activity.ISOActivity;
import com.henghao.wenbo.adapter.ImagePagerAdapter;
import com.henghao.wenbo.utils.CommonAutoViewpager;
import com.henghao.wenbo.views.viewpage.AutoScrollViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.zbar.lib.zxing.CaptureActivity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * @author zhangxianwen
 * @version HDMNV100R001, 2016年8月15日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HomeFragment extends FragmentSupport {

	@ViewInject(R.id.tv_setnfc)
	public TextView tv_setnfc;

	@ViewInject(R.id.lay_1)
	public LinearLayout mNfcLayout;

	@ViewInject(R.id.view_pager)
	public AutoScrollViewPager viewPager;

	@ViewInject(R.id.indicator)
	public CirclePageIndicator indicator;

	private NfcAdapter mNfcadapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.mActivityFragmentView.viewMain(R.layout.fragment_home);
		this.mActivityFragmentView.viewEmpty(R.layout.activity_empty);
		this.mActivityFragmentView.viewEmptyGone();
		this.mActivityFragmentView.viewLoading(View.GONE);
		ViewUtils.inject(this, this.mActivityFragmentView);
		initWidget();
		initData();
		return this.mActivityFragmentView;
	}

	private void initData() {
		// TODO Auto-generated method stub
		CommonAutoViewpager.viewPageAdapter(this.mActivity, this.viewPager, this.indicator);

	}

	/**
	 * 标题操作
	 * 〈一句话功能简述〉
	 * 〈功能详细描述〉
	 * @see [类、类#方法、类#成员]
	 * @since [产品/模块版本]
	 */
	private void initwithContent() {
		// TODO Auto-generated method stub
		this.mActivityFragmentView.getNavitionBarView().setVisibility(View.GONE);
	}

	public void setNfcText() {
		this.mNfcLayout.setVisibility(View.VISIBLE);
		if (this.mNfcadapter.isEnabled()) {
			this.tv_setnfc.setText("NFC监听中...");
		}
		else {
			this.tv_setnfc.setText("请在设置中打开NFC");
		}
	}

	public void initWidget() {
		NfcManager manager = (NfcManager) this.mActivity.getSystemService(Context.NFC_SERVICE);
		this.mNfcadapter = manager.getDefaultAdapter();
		initwithContent();
	}

	@OnClick({
	        R.id.m_submit1, R.id.m_submit2, R.id.m_submit3, R.id.lay_1, R.id.m_layout
	})
	private void viewClick(View view) {
		Intent intent = new Intent();
		switch (view.getId()) {
			case R.id.lay_1:
			case R.id.m_layout:
				if (this.mNfcadapter.isEnabled()) {
					// NFC已经打开
				}
				else {
					startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
				}
				break;
			case R.id.m_submit1:
				// NFC扫描
				if (this.mNfcadapter == null) {
					return;
				}
				if (this.mNfcadapter.isEnabled()) {
					ToolsToast.show(this.mActivity, "请扫描NFC标签");
				}
				else {
					startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
				}
				break;
			case R.id.m_submit2:
				// 二维码扫描
				intent.setClass(this.mActivity, CaptureActivity.class);
				startActivityForResult(intent, 1000);
				break;
			case R.id.m_submit3:
				// 巡检
				intent.setClass(this.mActivity, ISOActivity.class);
				startActivity(intent);
				break;
			default:
				break;
		}
	}

	/**
	 * 〈一句话功能简述〉
	 * 〈功能详细描述〉
	 * @param context
	 * @return NFC是否可以开启
	 *         [类、类#方法、类#成员]
	 * @since [产品/模块版本]
	 */
	private boolean hasNfc() {
		boolean bRet = false;
//	        if (adapter != null && adapter.isEnabled()) {
		if (this.mNfcadapter != null) {
			// adapter存在，能启用
			bRet = true;
		}
		return bRet;
	}

	/**
	 * 广告
	 * @param List<AdEntity>
	 * @see [类、类#方法、类#成员]
	 * @since [产品/模块版本]
	 */
	private void homeAds(List<Integer> ads) {
		if (!ToolsKit.isEmpty(ads)) {
			this.viewPager.startAutoScroll();
			this.indicator.setVisibility(View.VISIBLE);
			if (ads.size() == 1) {
				this.indicator.setVisibility(View.GONE);
				this.viewPager.stopAutoScroll();
			}

			this.viewPager.setAdapter(new ImagePagerAdapter(this.mActivity, ads));
			this.indicator.setViewPager(this.viewPager);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/* 回调内容 */
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		switch (requestCode) {

			case 1000:
				/**
				 * 二维码返回
				 */
				String result = data.getStringExtra("result");
				break;
		}
	}

}
