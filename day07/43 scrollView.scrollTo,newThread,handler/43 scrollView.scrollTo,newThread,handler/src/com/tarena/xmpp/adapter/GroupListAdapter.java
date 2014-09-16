package com.tarena.xmpp.adapter;

import java.util.ArrayList;

import org.jivesoftware.smack.RosterGroup;

import com.tarena.xmpp.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GroupListAdapter extends BaseAdapter{
Context context;
ArrayList<RosterGroup> list;

	public GroupListAdapter(Context context, ArrayList<RosterGroup> list) {
	super();
	this.context = context;
	this.list = list;
}
	public void changeData(ArrayList<RosterGroup> list)
	{
		this.list=list;
		this.notifyDataSetChanged();
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
			convertView=View.inflate(context, R.layout.group_list_item, null);
			viewHolder.tvGroupName=(TextView) convertView.findViewById(R.id.tv_group_list_item_groupName);
			convertView.setTag(viewHolder);
		}else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		RosterGroup rosterGroup=list.get(position);
		viewHolder.tvGroupName.setText(rosterGroup.getName());
		
		return convertView;
	}
	class ViewHolder
	{
		TextView tvGroupName;
	}

}
