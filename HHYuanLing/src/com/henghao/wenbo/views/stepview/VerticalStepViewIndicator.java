package com.henghao.wenbo.views.stepview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.henghao.wenbo.R;

/**
 * 日期：16/6/24 11:48
 * <p/>
 * 描述：
 */
public class VerticalStepViewIndicator extends View {
	// 定义默认的高度 definition default height
	private final int defaultStepIndicatorNum = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40,
	        getResources().getDisplayMetrics());

	private float mCompletedLineHeight;// 完成线的高度 definition completed line
	                                   // height
	private float mCircleRadius;// 圆的半径 definition circle radius

	private Drawable mCompleteIcon;// 完成的默认图片 definition default completed icon
	private Drawable mAttentionIcon;// 正在进行的默认图片 definition default underway
	                                // icon
	private Drawable mDefaultIcon;// 默认的背景图 definition default unCompleted icon
	private float mCenterX;// 该View的X轴的中间位置
	private float mLeftY;
	private float mRightY;

	private int mStepNum = 0;// 当前有几部流程 there are currently few step
	private float mLinePadding;// 两条连线之间的间距 definition the spacing between the
	                           // two circles

	private List<Float> mCircleCenterPointPositionList;// 定义所有圆的圆心点位置的集合
	                                                   // definition all of
	                                                   // circles center point
	                                                   // list
	private Paint mUnCompletedPaint;// 未完成Paint definition mUnCompletedPaint
	private Paint mCompletedPaint;// 完成paint definition mCompletedPaint
	private int mUnCompletedLineColor = getContext().getResources().getColor(R.color.uncompleted_color);// 定义默认未完成线的颜色
	                                                                                                    // definition
	                                                                                                    // mUnCompletedLineColor
	private int mCompletedLineColor = Color.WHITE;// 定义默认完成线的颜色 definition
	                                              // mCompletedLineColor
	private PathEffect mEffects;

	private int mComplectingPosition;// 正在进行position underway position
	private Path mPath;

	private OnDrawIndicatorListener mOnDrawListener;
	private Rect mRect;
	private int mHeight;// 这个控件的动态高度 this view dynamic height
	private boolean mIsReverseDraw;// is reverse draw this view;

	/**
	 * 设置监听
	 * @param onDrawListener
	 */
	public void setOnDrawListener(OnDrawIndicatorListener onDrawListener) {
		this.mOnDrawListener = onDrawListener;
	}

	/**
	 * get圆的半径 get circle radius
	 * @return
	 */
	public float getCircleRadius() {
		return this.mCircleRadius;
	}

	public VerticalStepViewIndicator(Context context) {
		this(context, null);
	}

	public VerticalStepViewIndicator(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public VerticalStepViewIndicator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	/**
	 * init
	 */
	private void init() {
		this.mPath = new Path();
		this.mEffects = new DashPathEffect(new float[] {
		        8, 8, 8, 8
		}, 1);

		this.mCircleCenterPointPositionList = new ArrayList<Float>();// 初始化

		this.mUnCompletedPaint = new Paint();
		this.mCompletedPaint = new Paint();
		this.mUnCompletedPaint.setAntiAlias(true);
		this.mUnCompletedPaint.setColor(this.mUnCompletedLineColor);
		this.mUnCompletedPaint.setStyle(Paint.Style.STROKE);
		this.mUnCompletedPaint.setStrokeWidth(2);

		this.mCompletedPaint.setAntiAlias(true);
		this.mCompletedPaint.setColor(this.mCompletedLineColor);
		this.mCompletedPaint.setStyle(Paint.Style.STROKE);
		this.mCompletedPaint.setStrokeWidth(2);

		this.mUnCompletedPaint.setPathEffect(this.mEffects);
		this.mCompletedPaint.setStyle(Paint.Style.FILL);

		// 已经完成线的宽高 set mCompletedLineHeight
		this.mCompletedLineHeight = 0.05f * this.defaultStepIndicatorNum;
		// 圆的半径 set mCircleRadius
		this.mCircleRadius = 0.28f * this.defaultStepIndicatorNum;
		// 线与线之间的间距 set mLinePadding
		this.mLinePadding = 0.85f * this.defaultStepIndicatorNum;

		this.mCompleteIcon = ContextCompat.getDrawable(getContext(), R.drawable.complted);// 已经完成的icon
		this.mAttentionIcon = ContextCompat.getDrawable(getContext(), R.drawable.attention);// 正在进行的icon
		this.mDefaultIcon = ContextCompat.getDrawable(getContext(), R.drawable.default_icon);// 未完成的icon

		this.mIsReverseDraw = true;// default draw
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = this.defaultStepIndicatorNum;
		this.mHeight = 0;
		if (this.mStepNum > 0) {
			// dynamic measure VerticalStepViewIndicator height
			this.mHeight = (int) (getPaddingTop() + getPaddingBottom() + this.mCircleRadius * 2 * this.mStepNum + (this.mStepNum - 1)
			        * this.mLinePadding);
		}
		if (MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(widthMeasureSpec)) {
			width = Math.min(width, MeasureSpec.getSize(widthMeasureSpec));
		}
		if (MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(heightMeasureSpec)) {
			this.mHeight = MeasureSpec.getSize(heightMeasureSpec);
		}
		setMeasuredDimension(width, this.mHeight);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		this.mCenterX = getWidth() / 2;
		this.mLeftY = this.mCenterX - (this.mCompletedLineHeight / 2);
		this.mRightY = this.mCenterX + (this.mCompletedLineHeight / 2);

		for (int i = 0; i < this.mStepNum; i++) {
			// reverse draw VerticalStepViewIndicator
			if (this.mIsReverseDraw) {
				this.mCircleCenterPointPositionList.add(this.mHeight
				        - (this.mCircleRadius + i * this.mCircleRadius * 2 + i * this.mLinePadding));
			}
			else {
				this.mCircleCenterPointPositionList.add(this.mCircleRadius + i * this.mCircleRadius * 2 + i
				        * this.mLinePadding);
			}
		}
		/**
		 * set listener
		 */
		if (this.mOnDrawListener != null) {
			this.mOnDrawListener.ondrawIndicator();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (this.mOnDrawListener != null) {
			this.mOnDrawListener.ondrawIndicator();
		}
		this.mUnCompletedPaint.setColor(this.mUnCompletedLineColor);
		this.mCompletedPaint.setColor(this.mCompletedLineColor);

		// -----------------------画线-------draw
		// line-----------------------------------------------
		for (int i = 0; i < this.mCircleCenterPointPositionList.size() - 1; i++) {
			// 前一个ComplectedXPosition
			final float preComplectedXPosition = this.mCircleCenterPointPositionList.get(i);
			// 后一个ComplectedXPosition
			final float afterComplectedXPosition = this.mCircleCenterPointPositionList.get(i + 1);

			if (i < this.mComplectingPosition)// 判断在完成之前的所有点
			{
				// 判断在完成之前的所有点，画完成的线，这里是矩形,很细的矩形，类似线，为了做区分，好看些
				if (this.mIsReverseDraw) {
					canvas.drawRect(this.mLeftY, afterComplectedXPosition + this.mCircleRadius - 10, this.mRightY,
					        preComplectedXPosition - this.mCircleRadius + 10, this.mCompletedPaint);
				}
				else {
					canvas.drawRect(this.mLeftY, preComplectedXPosition + this.mCircleRadius - 10, this.mRightY,
					        afterComplectedXPosition - this.mCircleRadius + 10, this.mCompletedPaint);
				}
			}
			else {
				if (this.mIsReverseDraw) {
					this.mPath.moveTo(this.mCenterX, afterComplectedXPosition + this.mCircleRadius);
					this.mPath.lineTo(this.mCenterX, preComplectedXPosition - this.mCircleRadius);
					canvas.drawPath(this.mPath, this.mUnCompletedPaint);
				}
				else {
					this.mPath.moveTo(this.mCenterX, preComplectedXPosition + this.mCircleRadius);
					this.mPath.lineTo(this.mCenterX, afterComplectedXPosition - this.mCircleRadius);
					canvas.drawPath(this.mPath, this.mUnCompletedPaint);
				}

			}
		}
		// -----------------------画线-------draw
		// line-----------------------------------------------

		// -----------------------画图标-----draw
		// icon-----------------------------------------------
		for (int i = 0; i < this.mCircleCenterPointPositionList.size(); i++) {
			final float currentComplectedXPosition = this.mCircleCenterPointPositionList.get(i);
			this.mRect = new Rect((int) (this.mCenterX - this.mCircleRadius),
			        (int) (currentComplectedXPosition - this.mCircleRadius),
			        (int) (this.mCenterX + this.mCircleRadius), (int) (currentComplectedXPosition + this.mCircleRadius));
			if (i < this.mComplectingPosition) {
				this.mCompleteIcon.setBounds(this.mRect);
				this.mCompleteIcon.draw(canvas);
			}
			else if (i == this.mComplectingPosition && this.mCircleCenterPointPositionList.size() != 1) {
				this.mCompletedPaint.setColor(Color.WHITE);
				canvas.drawCircle(this.mCenterX, currentComplectedXPosition, this.mCircleRadius * 1.1f,
				        this.mCompletedPaint);
				this.mAttentionIcon.setBounds(this.mRect);
				this.mAttentionIcon.draw(canvas);
			}
			else {
				this.mDefaultIcon.setBounds(this.mRect);
				this.mDefaultIcon.draw(canvas);
			}
		}
		// -----------------------画图标-----draw
		// icon-----------------------------------------------
	}

	/**
	 * 得到所有圆点所在的位置
	 * @return
	 */
	public List<Float> getCircleCenterPointPositionList() {
		return this.mCircleCenterPointPositionList;
	}

	/**
	 * 设置流程步数
	 * @param stepNum
	 *            流程步数
	 */
	public void setStepNum(int stepNum) {
		this.mStepNum = stepNum;
		invalidate();
	}

	/**
	 * 设置线间距的比例系数 set linePadding proportion
	 * @param linePaddingProportion
	 */
	public void setIndicatorLinePaddingProportion(float linePaddingProportion) {
		this.mLinePadding = linePaddingProportion * this.defaultStepIndicatorNum;
	}

	/**
	 * 设置正在进行position
	 * @param complectingPosition
	 */
	public void setComplectingPosition(int complectingPosition) {
		this.mComplectingPosition = complectingPosition;
		invalidate();
	}

	/**
	 * 设置未完成线的颜色
	 * @param unCompletedLineColor
	 */
	public void setUnCompletedLineColor(int unCompletedLineColor) {
		this.mUnCompletedLineColor = unCompletedLineColor;
	}

	/**
	 * 设置已完成线的颜色
	 * @param completedLineColor
	 */
	public void setCompletedLineColor(int completedLineColor) {
		this.mCompletedLineColor = completedLineColor;
	}

	/**
	 * is reverse draw 是否倒序画
	 */
	public void reverseDraw(boolean isReverseDraw) {
		this.mIsReverseDraw = isReverseDraw;
		invalidate();
	}

	/**
	 * 设置默认图片
	 * @param defaultIcon
	 */
	public void setDefaultIcon(Drawable defaultIcon) {
		this.mDefaultIcon = defaultIcon;
	}

	/**
	 * 设置已完成图片
	 * @param completeIcon
	 */
	public void setCompleteIcon(Drawable completeIcon) {
		this.mCompleteIcon = completeIcon;
	}

	/**
	 * 设置正在进行中的图片
	 * @param attentionIcon
	 */
	public void setAttentionIcon(Drawable attentionIcon) {
		this.mAttentionIcon = attentionIcon;
	}

	/**
	 * 设置对view监听
	 */
	public interface OnDrawIndicatorListener {
		void ondrawIndicator();
	}
}
