package com.tarena.xmpp.view;

import java.util.ArrayList;

import com.tarena.xmpp.R;
import com.tarena.xmpp.adapter.MainFragmentPagerAdatper;
import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.util.ExceptionUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements
		OnPageChangeListener, OnClickListener {
	ArrayList<Fragment> list = new ArrayList();
	ViewPager viewPager;
	Button[] btnArray = new Button[4];

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub

		super.onCreate(arg0);
		ApplicationData.instance.list.add(this);
		try {
			setContentView(R.layout.main);
			setupView();
			addListener();
			
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handle(e);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ApplicationData.instance.exit();
			return true;

		}
		return super.onKeyDown(keyCode, event);
	}

	private void addListener() {
		// TODO Auto-generated method stub
		viewPager.setOnPageChangeListener(this);
		for (Button button : btnArray) {
			button.setOnClickListener(this);
		}

	}

	private void setupView() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager) findViewById(R.id.viewPager_main);

		GroupListFragment groupListFragment = new GroupListFragment();
		InputRoomFragment groupChatFragment = new InputRoomFragment();
		TopicListFragment topicListFragment = new TopicListFragment();
		MoreFragment moreFragment = new MoreFragment();
		list.add(groupListFragment);
		list.add(groupChatFragment);
		list.add(topicListFragment);
		list.add(moreFragment);

		MainFragmentPagerAdatper adatper = new MainFragmentPagerAdatper(
				getSupportFragmentManager(), list);
		viewPager.setAdapter(adatper);

		btnArray[0] = (Button) findViewById(R.id.btn_main_groupList);
		btnArray[1] = (Button) findViewById(R.id.btn_main_groupChat);
		btnArray[2] = (Button) findViewById(R.id.btn_main_topic);
		btnArray[3] = (Button) findViewById(R.id.btn_main_more);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int selectedIndex) {
		// TODO Auto-generated method stub
		for (int i = 0; i < btnArray.length; i++) {
			if (selectedIndex == i) {
				btnArray[i].setTextColor(0xFFFFFFFF);
			} else {
				btnArray[i].setTextColor(0xFF000000);

			}
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int pageIndex = -1;
		switch (v.getId()) {
		case R.id.btn_main_groupList:
			pageIndex = 0;
			break;
		case R.id.btn_main_groupChat:
			pageIndex = 1;
			break;
		case R.id.btn_main_topic:
			pageIndex = 2;
			break;
		case R.id.btn_main_more:
			pageIndex = 3;
			break;
		}
		viewPager.setCurrentItem(pageIndex);
	}
}
