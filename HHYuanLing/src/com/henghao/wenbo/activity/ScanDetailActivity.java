package com.henghao.wenbo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benefit.buy.library.utils.tools.ToolsMoney;
import com.henghao.wenbo.ActivityFragmentSupport;
import com.henghao.wenbo.Constant;
import com.henghao.wenbo.R;
import com.henghao.wenbo.model.entity.TreeEntity;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 扫描之后详情
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangxianwen
 * @version HDMNV100R001, 2016-11-17
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ScanDetailActivity extends ActivityFragmentSupport {

	@ViewInject(R.id.tv_name)
	private TextView tv_name;
	@ViewInject(R.id.tv_address)
	private TextView tv_address;
	@ViewInject(R.id.tv_along)
	private TextView tv_along;

	@ViewInject(R.id.tv_height)
	private TextView tv_height;

	@ViewInject(R.id.tv_line)
	private TextView tv_line;

	@ViewInject(R.id.tv_nianling)
	private TextView tv_nianling;

	@ViewInject(R.id.tv_chandi)
	private TextView tv_chandi;

	@ViewInject(R.id.tv_jingwei)
	private TextView tv_jingwei;

	@ViewInject(R.id.tv_location)
	private TextView tv_location;

	@ViewInject(R.id.tv_jiangkang)
	private TextView tv_jiangkang;

	@ViewInject(R.id.tv_fuzeren)
	private TextView tv_fuzeren;

	@ViewInject(R.id.tv_miaomu)
	private TextView tv_miaomu;

	@ViewInject(R.id.tv_suoshu)
	private TextView tv_suoshu;

	@ViewInject(R.id.image_tree)
	private ImageView image_tree;

	@ViewInject(R.id.view_gone)
	private LinearLayout view_gone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mActivityFragmentView.viewMain(R.layout.activity_scandetail);
		this.mActivityFragmentView.viewEmpty(R.layout.activity_empty);
		this.mActivityFragmentView.viewEmptyGone();
		this.mActivityFragmentView.viewLoading(View.GONE);
		this.mActivityFragmentView.clipToPadding(true);
		setContentView(this.mActivityFragmentView);
		com.lidroid.xutils.ViewUtils.inject(this);
		initWidget();

	}

	/*
	 * (non-Javadoc)
	 * @see com.henghao.wenbo.ActivityFragmentSupport#initWidget()
	 */
	@Override
	public void initWidget() {
		// TODO Auto-generated method stub
		initWithBar();
		this.mLeftTextView.setText("详情");
		this.mLeftTextView.setVisibility(View.VISIBLE);
		setView();
	}

	/**
	 * 〈一句话功能简述〉
	 * 〈功能详细描述〉
	 * @see [类、类#方法、类#成员]
	 * @since [产品/模块版本]
	 */
	private void setView() {

		int type = getIntent().getIntExtra(Constant.INTNET_TYPE, 0);
		switch (type) {
			case 111:
				// 111地图点击
				this.view_gone.setVisibility(View.VISIBLE);
				break;
			case 112:
				// 适配器
				this.view_gone.setVisibility(View.GONE);

				break;

			default:
				break;
		}
		TreeEntity mEntity = (TreeEntity) getIntent().getSerializableExtra(Constant.INTNET_DATA);
		this.tv_name.setText("名称:" + mEntity.getTreeName());
		this.tv_along.setText("属:" + mEntity.getTreeAlong());
		this.tv_address.setText("区域:" + mEntity.getTreeAddress());
		this.image_tree.setImageResource(mEntity.getImageResouse());
		this.tv_height.setText(mEntity.getTreeHeight());
		this.tv_chandi.setText(mEntity.getTreeChandi());
		this.tv_location.setText(mEntity.getTreeLocation());
		this.tv_nianling.setText(mEntity.getTreeNianLing());
		this.tv_line.setText(mEntity.getTreeZhiJing());
		this.tv_jiangkang.setText(mEntity.getTreeJK());
		this.tv_suoshu.setText(mEntity.getSuoshu());
		this.tv_fuzeren.setText(mEntity.getFuzeren());
		this.tv_miaomu.setText(mEntity.getMiaomu());
		this.tv_jingwei.setText(ToolsMoney.DoubleNumUtil(mEntity.getLng(), 4) + ","
		        + ToolsMoney.DoubleNumUtil(mEntity.getLat(), 4));
	}

	/*
	 * (non-Javadoc)
	 * @see com.henghao.wenbo.ActivityFragmentSupport#initData()
	 */
	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
