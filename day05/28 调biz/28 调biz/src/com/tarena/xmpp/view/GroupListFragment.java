package com.tarena.xmpp.view;

import com.tarena.xmpp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class GroupListFragment extends Fragment {
	Button btnAddFriend;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.grouplist, null);
		setupView(view);
		addListener();
		return view;
	}

	private void addListener() {
		// TODO Auto-generated method stub
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
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
