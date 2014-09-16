package com.tarena.xmpp.view;

import java.util.ArrayList;

import org.jivesoftware.smack.RosterGroup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.tarena.xmpp.R;
import com.tarena.xmpp.adapter.GroupListAdapter;
import com.tarena.xmpp.model.ApplicationData;

public class GroupListFragment extends Fragment {
	Button btnAddFriend;
	ListView lv;
	GroupListAdapter groupListAdapter;
	GroupListChangedReceiver receiver;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		receiver=new GroupListChangedReceiver();
		IntentFilter intentFilter=new IntentFilter(ApplicationData.ACTION_GROUP_lIST_CHANGED);
		getActivity().registerReceiver(receiver, intentFilter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.grouplist, null);
		setupView(view);
		addListener();
		setData();
		return view;
	}

	private void setData() {
		// TODO Auto-generated method stub
		ArrayList<RosterGroup> list=
				new ArrayList(ApplicationData.xmppConnection.getRoster().getGroups());
		groupListAdapter=new GroupListAdapter(getActivity(), list);
		lv.setAdapter(groupListAdapter);
	}

	private void addListener() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
try {
	RosterGroup rosterGroup=(RosterGroup) groupListAdapter.getItem(position);
	String groupName=rosterGroup.getName();
	Intent intent=new Intent(getActivity(),FriendListActivity.class);
	intent.putExtra("groupName", groupName);
	getActivity().startActivity(intent);
	
} catch (Exception e) {
	// TODO: handle exception
}				
			}
		});
		
		btnAddFriend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Intent intent=new Intent(getActivity(),AddFriendActivity.class);
					startActivity(intent);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}

	private void setupView(View view) {
		// TODO Auto-generated method stub
		btnAddFriend=(Button) view.findViewById(R.id.btn_group_list_addFriend);
		lv=(ListView) view.findViewById(R.id.lv_group_list);
		//运行时出错
		//TextView tv=(TextView) view.findViewById(R.id.lv_group_list);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			getActivity().unregisterReceiver(receiver);
		} catch ( Exception e) {
			// TODO: handle exception
		}
	}
	class GroupListChangedReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			try {
				//取最新数据
				ArrayList<RosterGroup> list=
						new ArrayList(ApplicationData.xmppConnection.getRoster().getGroups());
				
//				ArrayList<RosterGroup> list2=
//						 (ArrayList)(
//								 ApplicationData.
//								 xmppConnection.getRoster()
//								 .getGroups());
				groupListAdapter.changeData(list);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

}
