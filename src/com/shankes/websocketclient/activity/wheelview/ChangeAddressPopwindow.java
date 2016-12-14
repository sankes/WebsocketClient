package com.shankes.websocketclient.activity.wheelview;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.shankes.customview.wheelview.OnWheelChangedListener;
import com.shankes.customview.wheelview.OnWheelScrollListener;
import com.shankes.customview.wheelview.WheelView;
import com.shankes.customview.wheelview.adapter.AbstractWheelTextAdapter1;
import com.shankes.websocketclient.R;

/**
 * 省市区三级联动选择
 * 
 * @author shankes
 * 
 * @data 2016-12-13
 */
public class ChangeAddressPopwindow extends PopupWindow implements View.OnClickListener {

	private WheelView wvProvince;
	private WheelView wvCitys;
	private WheelView wvArea;
	private View llPopupLayoutRoot;// 弹出框根布局,全屏
	private View llPopupLayoutRootChild;// 弹出框
	private TextView btnSure;
	private TextView btnCancel;

	private Context mContext;
	private JSONObject mJsonObj;
	/**
	 * 所有省
	 */
	private String[] mProvinceDatas;
	/**
	 * key - 省 value - 市s
	 */
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - 市 values - 区s
	 */
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

	private ArrayList<String> arrProvinces = new ArrayList<String>();
	private ArrayList<String> arrCitys = new ArrayList<String>();
	private ArrayList<String> arrAreas = new ArrayList<String>();
	private AddressTextAdapter provinceAdapter;
	private AddressTextAdapter cityAdapter;
	private AddressTextAdapter areaAdapter;

	private String strProvince = WheelInitData.INIT_PROVINCE_DEFAULT;
	private String strCity = WheelInitData.INIT_CITY_DEFAULT;
	private String strArea = WheelInitData.INIT_AREA_DEFAULT;
	private OnAddressCListener onAddressCListener;

	private int maxsize = 14;
	private int minsize = 12;

	public ChangeAddressPopwindow(final Context context) {
		super(context);
		this.mContext = context;
		View view = View.inflate(context, R.layout.activity_wheelview_popup_layout, null);

		wvProvince = (WheelView) view.findViewById(R.id.wv_address_province);
		wvCitys = (WheelView) view.findViewById(R.id.wv_address_city);
		wvArea = (WheelView) view.findViewById(R.id.wv_address_area);
		llPopupLayoutRoot = view.findViewById(R.id.ll_popup_layout_root);
		llPopupLayoutRootChild = view.findViewById(R.id.ll_popup_layout_root_child);
		btnSure = (TextView) view.findViewById(R.id.btn_myinfo_sure);
		btnCancel = (TextView) view.findViewById(R.id.btn_myinfo_cancel);

		// 设置SelectPicPopupWindow的View
		this.setContentView(view);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.PopupAnimation);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);

		llPopupLayoutRootChild.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		initJsonData();
		initDatas();

		initProvinces();
		provinceAdapter = new AddressTextAdapter(context, arrProvinces, getProvinceItem(strProvince), maxsize, minsize);
		wvProvince.setVisibleItems(5);
		wvProvince.setViewAdapter(provinceAdapter);
		wvProvince.setCurrentItem(getProvinceItem(strProvince));

		initCitys(mCitisDatasMap.get(strProvince));
		cityAdapter = new AddressTextAdapter(context, arrCitys, getCityItem(strCity), maxsize, minsize);
		wvCitys.setVisibleItems(5);
		wvCitys.setViewAdapter(cityAdapter);
		wvCitys.setCurrentItem(getCityItem(strCity));

		initAreas(mAreaDatasMap.get(strCity));
		areaAdapter = new AddressTextAdapter(context, arrAreas, getAreaItem(strArea), maxsize, minsize);
		wvArea.setVisibleItems(5);
		wvArea.setViewAdapter(areaAdapter);
		wvArea.setCurrentItem(getAreaItem(strArea));

		wvProvince.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				strProvince = currentText;
				setTextviewSize(currentText, provinceAdapter);

				String[] citys = mCitisDatasMap.get(currentText);
				initCitys(citys);
				cityAdapter = new AddressTextAdapter(context, arrCitys, 0, maxsize, minsize);
				wvCitys.setVisibleItems(5);
				wvCitys.setViewAdapter(cityAdapter);
				wvCitys.setCurrentItem(0);
				setTextviewSize("0", cityAdapter);

				// 根据市，地区联动
				String[] areas = mAreaDatasMap.get(citys[0]);
				initAreas(areas);
				areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize, minsize);
				wvArea.setVisibleItems(5);
				wvArea.setViewAdapter(areaAdapter);
				wvArea.setCurrentItem(0);
				setTextviewSize("0", areaAdapter);
			}
		});

		wvProvince.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
			}
		});

		wvCitys.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
				strCity = currentText;
				setTextviewSize(currentText, cityAdapter);

				// 根据市，地区联动
				String[] areas = mAreaDatasMap.get(currentText);
				initAreas(areas);
				areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize, minsize);
				wvArea.setVisibleItems(5);
				wvArea.setViewAdapter(areaAdapter);
				wvArea.setCurrentItem(0);
				setTextviewSize("0", areaAdapter);

			}
		});

		wvCitys.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, cityAdapter);
			}
		});

		wvArea.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
				strArea = currentText;
				setTextviewSize(currentText, cityAdapter);
			}
		});

		wvArea.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, areaAdapter);
			}
		});

	}

	private class AddressTextAdapter extends AbstractWheelTextAdapter1 {
		ArrayList<String> list;

		protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.activity_wheelview_item, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(14);
			} else {
				textvew.setTextSize(12);
			}
		}
	}

	public void setAddresskListener(OnAddressCListener onAddressCListener) {
		this.onAddressCListener = onAddressCListener;
	}

	@Override
	public void onClick(View v) {
		if (v == btnSure) {
			if (onAddressCListener != null) {
				// TODO 这里控制是否保持上次选择的地区
				WheelInitData.INIT_PROVINCE_DEFAULT = strProvince;
				WheelInitData.INIT_PROVINCE_ITEM = getProvinceItem(strProvince);
				WheelInitData.INIT_CITY_DEFAULT = strCity;
				WheelInitData.INIT_CITY_ITEM = getCityItem(strCity);
				WheelInitData.INIT_AREA_DEFAULT = strArea;
				WheelInitData.INIT_AREA_ITEM = getAreaItem(strArea);
				onAddressCListener.onClick(strProvince, strCity, strArea);
			}
		} else if (v == btnCancel) {

		} else if (v == llPopupLayoutRootChild) {
			return;
		}
		dismiss();
	}

	/**
	 * 回调接口
	 * 
	 * @author Administrator
	 * 
	 */
	public interface OnAddressCListener {
		public void onClick(String province, String city, String area);
	}

	/**
	 * 从文件中读取地址数据
	 */
	private void initJsonData() {
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = mContext.getClass().getClassLoader().getResourceAsStream("assets/" + "city.json");
			int len = -1;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1) {
				sb.append(new String(buf, 0, len, "gbk"));
			}
			is.close();
			mJsonObj = new JSONObject(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析整个Json对象，完成后释放Json对象的内存
	 */
	private void initDatas() {
		try {
			JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
			mProvinceDatas = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
				String province = jsonP.getString("p");// 省名字

				mProvinceDatas[i] = province;

				JSONArray jsonCs = null;
				try {
					/**
					 * Throws JSONException if the mapping doesn't exist or is
					 * not a JSONArray.
					 */
					jsonCs = jsonP.getJSONArray("c");
				} catch (Exception e1) {
					continue;
				}
				String[] mCitiesDatas = new String[jsonCs.length()];
				for (int j = 0; j < jsonCs.length(); j++) {
					JSONObject jsonCity = jsonCs.getJSONObject(j);
					String city = jsonCity.getString("n");// 市名字
					mCitiesDatas[j] = city;
					JSONArray jsonAreas = null;
					try {
						/**
						 * Throws JSONException if the mapping doesn't exist or
						 * is not a JSONArray.
						 */
						jsonAreas = jsonCity.getJSONArray("a");
					} catch (Exception e) {
						continue;
					}

					String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
					for (int k = 0; k < jsonAreas.length(); k++) {
						String area = jsonAreas.getJSONObject(k).getString("s");// 区域的名称
						mAreasDatas[k] = area;
					}
					mAreaDatasMap.put(city, mAreasDatas);
				}

				mCitisDatasMap.put(province, mCitiesDatas);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		mJsonObj = null;
	}

	/**
	 * 初始化省会
	 */
	public void initProvinces() {
		int length = mProvinceDatas.length;
		for (int i = 0; i < length; i++) {
			arrProvinces.add(mProvinceDatas[i]);
		}
	}

	/**
	 * 根据省会，生成该省会的所有城市
	 * 
	 * @param citys
	 */
	public void initCitys(String[] citys) {
		if (citys != null) {
			arrCitys.clear();
			int length = citys.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(citys[i]);
			}
		} else {
			String[] city = mCitisDatasMap.get(strProvince);
			arrCitys.clear();
			int length = city.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(city[i]);
			}
		}
		if (arrCitys != null && arrCitys.size() > 0 && !arrCitys.contains(strCity)) {
			strCity = arrCitys.get(0);
		}
	}

	/**
	 * 根据城市，生成该城市的所有地区
	 * 
	 * @param areas
	 */
	public void initAreas(String[] areas) {
		if (areas != null) {
			arrAreas.clear();
			int length = areas.length;
			for (int i = 0; i < length; i++) {
				arrAreas.add(areas[i]);
			}
		} else {
			String[] area = mAreaDatasMap.get(strCity);
			arrAreas.clear();
			int length = area.length;
			for (int i = 0; i < length; i++) {
				arrAreas.add(area[i]);
			}
		}
		if (arrAreas != null && arrAreas.size() > 0 && !arrAreas.contains(strArea)) {
			strArea = arrAreas.get(0);
		}
	}

	/**
	 * 初始化地点
	 * 
	 * @param province
	 * @param city
	 */
	public void setAddress(String province, String city, String area) {
		if (province != null && province.length() > 0) {
			this.strProvince = province;
		}
		if (city != null && city.length() > 0) {
			this.strCity = city;
		}

		if (area != null && area.length() > 0) {
			this.strArea = area;
		}
	}

	/**
	 * 返回省会索引，没有就返回默认“福建”
	 * 
	 * @param province
	 * @return
	 */
	public int getProvinceItem(String province) {
		int size = arrProvinces.size();
		int provinceIndex = 0;
		boolean noprovince = true;
		for (int i = 0; i < size; i++) {
			if (province.equals(arrProvinces.get(i))) {
				noprovince = false;
				return provinceIndex;
			} else {
				provinceIndex++;
			}
		}
		if (noprovince) {
			strProvince = WheelInitData.INIT_PROVINCE_DEFAULT;
			return WheelInitData.INIT_PROVINCE_ITEM;
		}
		return provinceIndex;
	}

	/**
	 * 得到城市索引，没有返回默认“厦门”
	 * 
	 * @param city
	 * @return
	 */
	public int getCityItem(String city) {
		int size = arrCitys.size();
		int cityIndex = 0;
		boolean nocity = true;
		for (int i = 0; i < size; i++) {
			System.out.println(arrCitys.get(i));
			if (city.equals(arrCitys.get(i))) {
				nocity = false;
				return cityIndex;
			} else {
				cityIndex++;
			}
		}
		if (nocity) {
			strCity = WheelInitData.INIT_CITY_DEFAULT;
			return WheelInitData.INIT_CITY_ITEM;
		}
		return cityIndex;
	}

	/**
	 * 得到地区索引，没有返回默认“思明区”
	 * 
	 * @param area
	 * @return
	 */
	public int getAreaItem(String area) {
		int size = arrAreas.size();
		int areaIndex = 0;
		boolean noarea = true;
		for (int i = 0; i < size; i++) {
			System.out.println(arrAreas.get(i));
			if (area.equals(arrAreas.get(i))) {
				noarea = false;
				return areaIndex;
			} else {
				areaIndex++;
			}
		}
		if (noarea) {
			strArea = WheelInitData.INIT_AREA_DEFAULT;
			return WheelInitData.INIT_AREA_ITEM;
		}
		return areaIndex;
	}
}