package com.henghao.wenbo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.henghao.wenbo.ActivityFragmentSupport;
import com.henghao.wenbo.Constant;
import com.henghao.wenbo.R;
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
public class XiaoFangDetailActivity extends ActivityFragmentSupport {

	@ViewInject(R.id.image_view)
	private ImageView imageView;

	@ViewInject(R.id.tv_jieshao)
	private TextView tv_jieshao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mActivityFragmentView.viewMain(R.layout.activity_xiaofang);
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
		this.mLeftTextView.setText("消防详情");
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
			case 1:
				this.imageView.setImageResource(R.drawable.xiaofang1);
				this.tv_jieshao
				        .setText("消防车，又称为救火车，指主要用来执行火灾应对任务的特殊车辆，包括中国在内的大部分国家消防部门也会将其用于其他紧急抢救用途。消防车可以运送消防员抵达灾害现场，并为其执行救灾任务提供多种工具。现代消防车通常会配备钢梯、水枪、便携式灭火器、自持式呼吸器、防护服、破拆工具、急救工具等装备，部分的还会搭载水箱、水泵、泡沫灭火装置等大型灭火设备。多数地区的消防车外观为红色，但也有部分地区消防车外观为黄色，部分特种消防车亦是如此，消防车顶部通常设有警钟警笛、警灯和爆闪灯。常见的消防车种类包括水罐消防车、泡沫消防车、泵浦消防车、登高平台消防车、云梯消防车等。");
				break;
			case 2:
				this.imageView.setImageResource(R.drawable.xiaofang2);
				this.tv_jieshao
				        .setText("灭火器是一种可携式灭火工具。灭火器内放置化学物品，用以救灭火灾。灭火器是常见的防火设施之一，存放在公众场所或可能发生火灾的地方，不同种类的灭火器内装填的成分不一样，是专为不同的火警而设。使用时必须注意以免产生反效果及引起危险。");
				break;

			default:
				break;
		}

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
