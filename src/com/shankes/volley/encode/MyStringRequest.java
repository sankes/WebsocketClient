package com.shankes.volley.encode;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

public class MyStringRequest extends StringRequest {

	public MyStringRequest(String url, Listener<String> listener, ErrorListener errorListener) {
		super(url, listener, errorListener);
	}

	public MyStringRequest(int method, String url, Listener<String> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}

	/**
	 * 重写以解决乱码问题
	 */
	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		String str = null;
		try {
			str = new String(response.data, "utf-8");
			str = UnicodeUtil.unicodeToString(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
	}
}
