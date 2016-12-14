package com.shankes.websocketclient.activity;

import com.shankes.customview.moveview.MoveParentView;
import com.shankes.websocketclient.R;

import android.app.Activity;
import android.os.Bundle;

public class MoveParentActivity extends Activity {

	private MoveParentView myParentView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_move_parent);
		myParentView = (MoveParentView) findViewById(R.id.parent_view);
	}
}
