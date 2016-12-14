package com.shankes.websocketclient.activity.wheelview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shankes.websocketclient.R;

/**
 * 省市区三级联动选择
 * 
 * @author shankes
 * 
 * @data 2016-12-13
 */
public class WheelViewActivity extends Activity {
	private TextView select_address_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wheelview);

		select_address_tv = (TextView) findViewById(R.id.select_address_tv);
		select_address_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(WheelViewActivity.this);
				mChangeAddressPopwindow.setAddress(WheelInitData.INIT_PROVINCE_DEFAULT,
						WheelInitData.INIT_CITY_DEFAULT, WheelInitData.INIT_AREA_DEFAULT);
				mChangeAddressPopwindow.showAtLocation(select_address_tv, Gravity.BOTTOM, 0, 0);
				mChangeAddressPopwindow.setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

					@Override
					public void onClick(String province, String city, String area) {
						// TODO Auto-generated method stub
						Toast.makeText(WheelViewActivity.this, province + "-" + city + "-" + area, Toast.LENGTH_LONG)
								.show();
						select_address_tv.setText(province + city + area);
					}
				});
			}
		});
	}
}
