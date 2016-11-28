package com.henghao.wenbo.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;

import com.benefit.buy.library.http.query.callback.AjaxStatus;
import com.benefit.buy.library.utils.NSLog;
import com.benefit.buy.library.utils.tools.ToolsToast;
import com.benefit.buy.library.views.ToastView;
import com.henghao.wenbo.ActivityFragmentSupport;
import com.henghao.wenbo.Constant;
import com.henghao.wenbo.ProtocolUrl;
import com.henghao.wenbo.R;
import com.henghao.wenbo.adapter.FragmentTabAdapter;
import com.henghao.wenbo.fragment.FragmentSupport;
import com.henghao.wenbo.fragment.HomeFragment;
import com.henghao.wenbo.model.entity.HCMenuEntity;
import com.henghao.wenbo.model.protocol.NfcDetailsProtocol;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 主页
 * @author zhangxianwen
 */
@SuppressLint("NewApi")
public class MainActivity extends ActivityFragmentSupport {

	@ViewInject(R.id.tabs_rg)
	private RadioGroup rgs;

	@ViewInject(R.id.tab_top)
	public View mTabLinearLayout;

	private boolean isExit = false;

	private ToastView mToastView;

	public List<FragmentSupport> fragments = new ArrayList<FragmentSupport>();

	private List<HCMenuEntity> menuLists;

	private boolean ready = true;    // 是否获取nfc信息

	private NfcAdapter mAdapter;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private String[][] mTechLists;

	private FragmentTabAdapter tabAdapter;

	private NfcDetailsProtocol mDetailsProtocol;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (this.tabAdapter != null) {
			this.tabAdapter.remove();
		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.mActivityFragmentView.viewMain(R.layout.activity_main);
		this.mActivityFragmentView.viewEmpty(R.layout.activity_empty);
		this.mActivityFragmentView.viewEmptyGone();
		this.mActivityFragmentView.viewLoading(View.GONE);
		this.mActivityFragmentView.clipToPadding(true);
		setContentView(this.mActivityFragmentView);
		com.lidroid.xutils.ViewUtils.inject(this);
		menuList();
		try {
			// 动态加载tab
			// 动态设置tab item
			for (int i = 0; i < this.menuLists.size(); i++) {
				HCMenuEntity menu = this.menuLists.get(i);
				if (menu.getStatus() == -1) {
					@SuppressWarnings("unchecked")
					Class<FragmentSupport> clazz = (Class<FragmentSupport>) Class.forName(menu.getClazz());
					FragmentSupport fragmentSuper = clazz.newInstance();
					fragmentSuper.fragmentId = menu.getmId();
					this.fragments.add(fragmentSuper);
				}
			}
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Intent intent = getIntent();
		// page = intent.getIntExtra("page", 0);
		// if (page == 3) {
		// hcShopcar.setChecked(true);
		// }
		this.tabAdapter = new FragmentTabAdapter(this, this.fragments, R.id.tab_content, this.rgs);
		this.tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {

			@Override
			public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
				System.out.println("Extra---- " + index + " checked!!! ");
			}
		});
		initData();
		this.mAdapter = NfcAdapter.getDefaultAdapter(MainActivity.this);
		// 创建一个通用的PendingIntent，将交付给了这个活动。NFC堆栈将填写的意图与发现的标签的细节提供这个活动之前。
		this.mPendingIntent = PendingIntent.getActivity(MainActivity.this, 0,
		        new Intent(MainActivity.this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		// 设置所有的MIME基础派遣一个意图过滤器
		IntentFilter ntech = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
		this.mFilters = new IntentFilter[] {
			ntech,
		};

		// 设置所有nfcf标签技术清单
		this.mTechLists = new String[][] {
		        new String[] {
			        MifareClassic.class.getName()
		        }, new String[] {
			        NfcV.class.getName()
		        }
		};
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		// 导航栏
		initwithContent();
		this.mDetailsProtocol = new NfcDetailsProtocol(this);
		this.mDetailsProtocol.addResponseListener(this);
	}

	private void initwithContent() {
		// TODO Auto-generated method stub
		initWithCenterBar();
		this.mCenterTextView.setVisibility(View.VISIBLE);
		this.mCenterTextView.setText(getResources().getString(R.string.app_name));
		this.rgs.setVisibility(View.GONE);
	}

	/**
	 * 牵扯到的tab items
	 */
	public void menuList() {
		this.menuLists = new ArrayList<HCMenuEntity>();
		HCMenuEntity mMenuHome = new HCMenuEntity(1, getResources().getString(R.string.hc_home),
		        R.drawable.selector_home, HomeFragment.class.getName(), -1);// 首页
		this.menuLists.add(mMenuHome);
		HCMenuEntity mMenuJob = new HCMenuEntity(2, getResources().getString(R.string.hc_xinxi),
		        R.drawable.selector_xiangmu, HomeFragment.class.getName(), -1);// 信息
		this.menuLists.add(mMenuJob);
		HCMenuEntity mMenuCommunication = new HCMenuEntity(3, getResources().getString(R.string.hc_xiangmu),
		        R.drawable.selector_mall, HomeFragment.class.getName(), -1);// 项目信息
		this.menuLists.add(mMenuCommunication);
		HCMenuEntity mMenuMore = new HCMenuEntity(4, getResources().getString(R.string.hc_my), R.drawable.selector_my,
		        HomeFragment.class.getName(), -1);// 我的
		this.menuLists.add(mMenuMore);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if ((event.getKeyCode() == KeyEvent.KEYCODE_BACK) && (event.getAction() != KeyEvent.ACTION_UP)) {
			if (!this.isExit) {
				this.isExit = true;
				this.mToastView = ToastView.makeText(this, getResources().getString(R.string.home_exit)).setGravity(
				        Gravity.CENTER, 0, 0);
				this.mToastView.show();
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						MainActivity.this.isExit = false;
					}
				}, 3000);
				return true;
			}
			else {
				this.mToastView.cancel();
				this.mApplication.exit();
				return false;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case Constant.SCANNIN_GREQUEST_CODE:
				if (resultCode == RESULT_OK) {
					Bundle bundle = data.getExtras();
					// 显示扫描到的内容
					String content = bundle.getString("result");
					// 显示
					Bitmap bitmap = data.getParcelableExtra("bitmap");
					NSLog.e(this, "content:" + content);
				}
				break;
		}
	}

	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
		resolveIntent(intent);
	}

	void resolveIntent(Intent intent) {
		// 分析intent
		String action = intent.getAction();
		if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
			if (this.ready == false) {
				return;
			}
			// 提示音
			MediaPlayer.create(this, R.raw.discovered_tag_notification).start();

			Bundle b = intent.getExtras();
			Tag t = (Tag) b.get(NfcAdapter.EXTRA_TAG);
			byte[] idarr = t.getId();
			final String idString = convertByteArray(idarr);

			this.ready = false;

			System.out.println("idString:::::::" + idString);
			this.ready = true;
			// 触发执行
			// EventBus.getDefault().post("谁看到我了就执行");
			HomeFragment homeFragment = (HomeFragment) this.fragments.get(0);

			this.mDetailsProtocol.getNfcById(idString);
			this.mActivityFragmentView.viewLoading(View.VISIBLE);

		}
	}

	String convertByteArray(byte[] ids) {
		if (ids.length == 0) {
			return "empty";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ids.length; i++) {
			if (i > 0) {
				sb.append('-');
			}
			String s = Integer.toHexString(ids[i] & 0xff);
			if (s.length() == 1) {
				sb.append('0');
			}
			sb.append(s);
		}
		return sb.toString();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (this.mAdapter != null) {
			this.mAdapter.enableForegroundDispatch(this, this.mPendingIntent, this.mFilters, this.mTechLists);
			HomeFragment homeFragment = (HomeFragment) this.fragments.get(0);
			homeFragment.setNfcText();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.henghao.suyuan.ActivityFragmentSupport#OnMessageResponse(java.lang.String, java.lang.Object,
	 * com.benefit.buy.library.http.query.callback.AjaxStatus)
	 */
	@Override
	public void OnMessageResponse(String url, Object jo, AjaxStatus status) throws JSONException {
		// TODO Auto-generated method stub
		super.OnMessageResponse(url, jo, status);
		this.mActivityFragmentView.viewLoading(View.GONE);
		if (url.endsWith(ProtocolUrl.APP_GET_NFCBYID)) {
			if (jo == null) {
				return;
			}
			String string = (String) jo;
			if (string.contains("不存在")) {
				ToolsToast.show(this, string);
				return;
			}
			Intent intent = new Intent();
			intent.setClass(this, CommonWebActivity.class);
			intent.putExtra(Constant.INTNET_URL, string);
			intent.putExtra(Constant.INTNET_TITLE, "详情");
			startActivity(intent);
		}
	}
}
