package com.tarena.xmpp.adapter;

import java.util.ArrayList;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;

import com.tarena.xmpp.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FriendListAdapter extends BaseAdapter{
Context context;
//RosterEntry是一个好友
ArrayList<RosterEntry> list;

	public FriendListAdapter(Context context, ArrayList<RosterEntry> list) {
	super();
	this.context = context;
	this.list = list;
}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=null;
		if (convertView==null)
		{
			viewHolder=new ViewHolder();
			convertView=View.inflate(context, R.layout.friend_list_item, null);
			viewHolder.tvFriendName=(TextView) convertView.findViewById(R.id.tv_friend_list_item_groupName);
			convertView.setTag(viewHolder);
		}else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		RosterEntry rosterEntry=list.get(position);
		viewHolder.tvFriendName.setText(rosterEntry.getName());
		
		return convertView;
	}
	class ViewHolder
	{
		TextView tvFriendName;
	}

}
