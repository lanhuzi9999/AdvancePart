package com.tarena.xmpp.view;

import java.util.ArrayList;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;

import android.os.Bundle;
import android.widget.ListView;

import com.tarena.xmpp.R;
import com.tarena.xmpp.adapter.FriendListAdapter;
import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.util.ExceptionUtil;

public class FriendListActivity extends BaseActivity{
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.friendlist);
			setupView();
			addListener();
			setData();
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handle(e);
		}
	}

	private void setData() {
		// TODO Auto-generated method stub
		String groupName=this.getIntent().getStringExtra("groupName");
		RosterGroup rosterGroup=ApplicationData.xmppConnection.
		getRoster().getGroup(groupName);
		ArrayList<RosterEntry> list=new ArrayList(rosterGroup.getEntries());
		
		FriendListAdapter adapter=new FriendListAdapter(this, list);
		lv.setAdapter(adapter);
		
	}

	private void addListener() {
		// TODO Auto-generated method stub
		
	}

	private void setupView() {
		// TODO Auto-generated method stub
		lv=(ListView) findViewById(R.id.lv_friend_list);
	}

}
