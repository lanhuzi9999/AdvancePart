package com.tarena.xmpp.view;

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
import android.widget.Button;
import android.widget.EditText;

import com.tarena.xmpp.R;
import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.model.GroupChatBiz;
import com.tarena.xmpp.util.LogUtil;

public class InputRoomFragment extends Fragment {
	Button btnSubmit;
	EditText etName;
	JoinReceiver receiver;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		IntentFilter intentFilter=new IntentFilter(ApplicationData.ACTION_JOIN);
		receiver=new JoinReceiver();
		getActivity().registerReceiver(receiver, intentFilter);
		
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.input_room, null);
		try {
			setupView(view);
			addListener();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}

	private void addListener() {
		// TODO Auto-generated method stub
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					String room=etName.getText().toString();
					String name=ApplicationData.currentUser;
					name=name.substring(0,name.indexOf("@"));
					//���
		GroupChatBiz groupChatBiz=new GroupChatBiz();
					groupChatBiz.join(room, name);
					
//					groupChatBiz.sendMessage("���");
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		
	}

	private void setupView(View view) {
		// TODO Auto-generated method stub
		btnSubmit=(Button) view.findViewById(R.id.btn_input_room_submit);
		etName=(EditText) view.findViewById(R.id.et_input_room_name);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		getActivity().unregisterReceiver(receiver);
	}
class JoinReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		try {
			boolean isSuccess=intent.getBooleanExtra(ApplicationData.KEY_ISSUCCESS, false);
			if (isSuccess)
			{
				Intent intentGroup=new Intent(getActivity(),GroupChatActivity.class);
				getActivity().startActivity(intentGroup);
				LogUtil.i("join", "�ɹ�");
			}else
			{
				LogUtil.i("join", "ʧ��");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	}
}
