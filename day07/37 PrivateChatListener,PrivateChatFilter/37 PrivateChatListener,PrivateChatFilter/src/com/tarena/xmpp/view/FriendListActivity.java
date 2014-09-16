package com.tarena.xmpp.view;

import java.util.ArrayList;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tarena.xmpp.R;
import com.tarena.xmpp.adapter.FriendListAdapter;
import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.model.PrivateChatBiz;
import com.tarena.xmpp.util.ExceptionUtil;

public class FriendListActivity extends BaseActivity {
	ListView lv;
	FriendListAdapter adapter;

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
		String groupName = this.getIntent().getStringExtra("groupName");
		RosterGroup rosterGroup = ApplicationData.xmppConnection.getRoster()
				.getGroup(groupName);
		ArrayList<RosterEntry> list = new ArrayList(rosterGroup.getEntries());

		adapter = new FriendListAdapter(this, list);
		lv.setAdapter(adapter);

	}

	private void addListener() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					// intent
					PrivateChatBiz privateChatBiz = new PrivateChatBiz();
					String friendUser = ((RosterEntry) adapter
							.getItem(position)).getUser();
					privateChatBiz.sendMessage(friendUser, "ÄãºÃ");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}

	private void setupView() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.lv_friend_list);
	}

}
