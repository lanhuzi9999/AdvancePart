package com.tarena.xmpp.view;

import com.tarena.xmpp.R;
import com.tarena.xmpp.model.ApplicationData;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MoreFragment extends Fragment {
	Button btnExit,btnCopyright;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.more, null);
		setupView(view);
		addListener();
		return view;
	}

	private void addListener() {
		// TODO Auto-generated method stub
		btnExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ApplicationData.instance.exit();
			}
		});
		btnCopyright.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),CopyRightActivity.class);
				getActivity().startActivity(intent);
			}
		});
		
	}

	private void setupView(View view) {
		// TODO Auto-generated method stub
		btnExit=(Button) view.findViewById(R.id.btn_more_exit);
		btnCopyright=(Button) view.findViewById(R.id.btn_more_copyright);
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
