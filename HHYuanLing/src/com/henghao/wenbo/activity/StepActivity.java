package com.henghao.wenbo.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.henghao.wenbo.ActivityFragmentSupport;
import com.henghao.wenbo.R;
import com.henghao.wenbo.views.stepview.VerticalStepView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangxianwen
 * @version HDMNV100R001, 2015年8月15日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class StepActivity extends ActivityFragmentSupport {

	@ViewInject(R.id.step_view0)
	private VerticalStepView mVerticalStepView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mActivityFragmentView.viewMain(R.layout.activity_step);
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
		List<String> list0 = new ArrayList<String>();
		list0.add("巡更1");
		list0.add("巡更2");
		list0.add("文物1");
		list0.add("文物2");
		list0.add("文物3");
		list0.add("巡更4");
		this.mVerticalStepView.reverseDraw(false); // AttentionIcon
		this.mVerticalStepView.setStepsViewIndicatorComplectingPosition(2)// 设置完成的步数
		        .setStepViewTexts(list0)// 总步骤
		        .setStepsViewIndicatorCompletedLineColor(getResources().getColor(android.R.color.white))// 设置StepsViewIndicator完成线的颜色
		        .setStepsViewIndicatorUnCompletedLineColor(getResources().getColor(R.color.uncompleted_text_color))// 设置StepsViewIndicator未完成线的颜色
		        .setStepViewComplectedTextColor(getResources().getColor(android.R.color.white))// 设置StepsView
		                                                                                       // text完成线的颜色
		        .setStepViewUnComplectedTextColor(getResources().getColor(R.color.uncompleted_text_color))// 设置StepsView
		                                                                                                  // text未完成线的颜色
		        .setStepsViewIndicatorCompleteIcon(getResources().getDrawable(R.drawable.complted))// 设置StepsViewIndicator
		                                                                                           // CompleteIcon
		        .setStepsViewIndicatorDefaultIcon(getResources().getDrawable(R.drawable.default_icon))// 设置StepsViewIndicator
		                                                                                              // DefaultIcon
		        .setStepsViewIndicatorAttentionIcon(getResources().getDrawable(R.drawable.attention));// 设置StepsViewIndicator

	}

	@Override
	public void initWidget() {
		/** 导航栏 */
		initWithBar();
		this.mLeftTextView.setText(getString(R.string.app_name));
		this.mLeftTextView.setVisibility(View.VISIBLE);
		initWithContent();
	}

	private void initWithContent() {
		// TODO Auto-generated method stub
	}
}
