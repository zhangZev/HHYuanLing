package com.henghao.wenbo.views.stepview;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henghao.wenbo.R;

/**
 * 日期：16/6/22 15:47
 * <p>
 * 描述：StepView
 */
@SuppressLint("NewApi")
public class HorizontalStepView extends LinearLayout implements HorizontalStepsViewIndicator.OnDrawIndicatorListener {

	private RelativeLayout mTextContainer;
	private HorizontalStepsViewIndicator mStepsViewIndicator;
	private List<String> mTexts;
	private int mComplectingPosition;
	private int mUnComplectedTextColor = getContext().getResources().getColor(R.color.uncompleted_text_color);// 定义默认未完成文字的颜色;
	private int mComplectedTextColor = getContext().getResources().getColor(android.R.color.white);// 定义默认完成文字的颜色;

	public HorizontalStepView(Context context) {
		this(context, null);
	}

	public HorizontalStepView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HorizontalStepView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_horizontal_stepsview, this);
		this.mStepsViewIndicator = (HorizontalStepsViewIndicator) rootView.findViewById(R.id.steps_indicator);
		this.mStepsViewIndicator.setOnDrawListener(this);
		this.mTextContainer = (RelativeLayout) rootView.findViewById(R.id.rl_text_container);
		this.mTextContainer.removeAllViews();
	}

	/**
	 * 设置显示的文字
	 * @param texts
	 * @return
	 */
	public HorizontalStepView setStepViewTexts(List<String> texts) {
		this.mTexts = texts;
		this.mStepsViewIndicator.setStepNum(this.mTexts.size());
		return this;
	}

	/**
	 * 设置正在进行的position
	 * @param complectingPosition
	 * @return
	 */
	public HorizontalStepView setStepsViewIndicatorComplectingPosition(int complectingPosition) {
		this.mComplectingPosition = complectingPosition;
		this.mStepsViewIndicator.setComplectingPosition(complectingPosition);
		return this;
	}

	/**
	 * 设置未完成文字的颜色
	 * @param unComplectedTextColor
	 * @return
	 */
	public HorizontalStepView setStepViewUnComplectedTextColor(int unComplectedTextColor) {
		this.mUnComplectedTextColor = unComplectedTextColor;
		return this;
	}

	/**
	 * 设置完成文字的颜色
	 * @param complectedTextColor
	 * @return
	 */
	public HorizontalStepView setStepViewComplectedTextColor(int complectedTextColor) {
		this.mComplectedTextColor = complectedTextColor;
		return this;
	}

	/**
	 * 设置StepsViewIndicator未完成线的颜色
	 * @param unCompletedLineColor
	 * @return
	 */
	public HorizontalStepView setStepsViewIndicatorUnCompletedLineColor(int unCompletedLineColor) {
		this.mStepsViewIndicator.setUnCompletedLineColor(unCompletedLineColor);
		return this;
	}

	/**
	 * 设置StepsViewIndicator完成线的颜色
	 * @param completedLineColor
	 * @return
	 */
	public HorizontalStepView setStepsViewIndicatorCompletedLineColor(int completedLineColor) {
		this.mStepsViewIndicator.setCompletedLineColor(completedLineColor);
		return this;
	}

	/**
	 * 设置StepsViewIndicator默认图片
	 * @param defaultIcon
	 */
	public HorizontalStepView setStepsViewIndicatorDefaultIcon(Drawable defaultIcon) {
		this.mStepsViewIndicator.setDefaultIcon(defaultIcon);
		return this;
	}

	/**
	 * 设置StepsViewIndicator已完成图片
	 * @param completeIcon
	 */
	public HorizontalStepView setStepsViewIndicatorCompleteIcon(Drawable completeIcon) {
		this.mStepsViewIndicator.setCompleteIcon(completeIcon);
		return this;
	}

	/**
	 * 设置StepsViewIndicator正在进行中的图片
	 * @param attentionIcon
	 */
	public HorizontalStepView setStepsViewIndicatorAttentionIcon(Drawable attentionIcon) {
		this.mStepsViewIndicator.setAttentionIcon(attentionIcon);
		return this;
	}

	@Override
	public void ondrawIndicator() {
		List<Float> complectedXPosition = this.mStepsViewIndicator.getCircleCenterPointPositionList();
		if (this.mTexts != null) {
			for (int i = 0; i < this.mTexts.size(); i++) {
				TextView textView = new TextView(getContext());
				textView.setText(this.mTexts.get(i));
				textView.setX(complectedXPosition.get(i) - this.mStepsViewIndicator.getCircleRadius() - 10);// 这里的-10是将文字进行调整居中，稍后再动态修改
				textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				        ViewGroup.LayoutParams.WRAP_CONTENT));

				if (i <= this.mComplectingPosition) {
					textView.setTypeface(null, Typeface.BOLD);
					textView.setTextColor(this.mComplectedTextColor);
				}
				else {
					textView.setTextColor(this.mUnComplectedTextColor);
				}

				this.mTextContainer.addView(textView);
			}
		}
	}
}
