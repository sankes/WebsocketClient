package com.shankes.websocketclient.turing.util;

import java.util.Date;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shankes.util.LogUtil;
import com.shankes.util.NotifyUtil;
import com.shankes.util.ToastUtil;
import com.shankes.websocketclient.activity.TuringChatActivity;
import com.shankes.websocketclient.turing.domain.MessageType;
import com.shankes.websocketclient.turing.domain.TuringBaseInfo;
import com.shankes.websocketclient.turing.domain.TuringChildPoem;
import com.shankes.websocketclient.turing.domain.TuringChildSong;
import com.shankes.websocketclient.turing.domain.TuringCook;
import com.shankes.websocketclient.turing.domain.TuringError;
import com.shankes.websocketclient.turing.domain.TuringLink;
import com.shankes.websocketclient.turing.domain.TuringNews;
import com.shankes.websocketclient.turing.domain.TuringText;

public class TuringHandler {

	protected static final int ID = 0x999;
	private Context mContext;
	private static TuringHandler instance;
	private static Gson gson = new GsonBuilder().create();

	public TuringHandler(Context context) {
		this.mContext = context;
	}

	public static TuringHandler getInstance(Context context) {
		if (instance == null) {
			instance = new TuringHandler(context);
		}
		return instance;
	}

	public Handler handler = new Handler() {
		String result = null;
		TuringBaseInfo turingInfo = null;

		public void handleMessage(Message msg) {
			result = msg.getData().getString("result");
			switch (TuringCodeType.valueOfInt(msg.what)) {
			case NORMAL_TEXT:// 文本类
				LogUtil.i(TuringCodeType.NORMAL_TEXT.getValueText());
				ToastUtil.showShortDebug(mContext, TuringCodeType.NORMAL_TEXT.getValueText());
				turingInfo = gson.fromJson(result, TuringText.class);
				break;
			case NORMAL_LINK:// 链接类
				LogUtil.i(TuringCodeType.NORMAL_LINK.getValueText());
				ToastUtil.showShortDebug(mContext, TuringCodeType.NORMAL_LINK.getValueText());
				turingInfo = gson.fromJson(result, TuringLink.class);
				break;
			case NORMAL_NEWS:// 新闻类
				LogUtil.i(TuringCodeType.NORMAL_NEWS.getValueText());
				ToastUtil.showShortDebug(mContext, TuringCodeType.NORMAL_NEWS.getValueText());
				turingInfo = gson.fromJson(result, TuringNews.class);
				break;
			case NORMAL_COOK:// 菜谱类
				LogUtil.i(TuringCodeType.NORMAL_COOK.getValueText());
				ToastUtil.showShortDebug(mContext, TuringCodeType.NORMAL_COOK.getValueText());
				turingInfo = gson.fromJson(result, TuringCook.class);
				break;
			case NORMAL_CHILD_SONG:// （儿童版） 儿歌类
				LogUtil.i(TuringCodeType.NORMAL_CHILD_SONG.getValueText());
				ToastUtil.showShortDebug(mContext, TuringCodeType.NORMAL_CHILD_SONG.getValueText());
				turingInfo = gson.fromJson(result, TuringChildSong.class);
				break;
			case NORMAL_CHILD_POEM:// （儿童版）诗词类
				LogUtil.i(TuringCodeType.NORMAL_CHILD_POEM.getValueText());
				ToastUtil.showShortDebug(mContext, TuringCodeType.NORMAL_CHILD_POEM.getValueText());
				turingInfo = gson.fromJson(result, TuringChildPoem.class);
				break;
			case ERROR_KEY:// 参数key错误
				LogUtil.i(TuringCodeType.ERROR_KEY.getValueText());
				ToastUtil.showShortDebug(mContext, TuringCodeType.ERROR_KEY.getValueText());
				turingInfo = gson.fromJson(result, TuringError.class);
				break;
			case ERROR_INFO:// 请求内容info为空
				LogUtil.i(TuringCodeType.ERROR_INFO.getValueText());
				ToastUtil.showShortDebug(mContext, TuringCodeType.ERROR_INFO.getValueText());
				turingInfo = gson.fromJson(result, TuringError.class);
				break;
			case ERROR_TOO_MANY_TIMES:// 当天请求次数已使用完
				LogUtil.i(TuringCodeType.ERROR_TOO_MANY_TIMES.getValueText());
				ToastUtil.showShortDebug(mContext, TuringCodeType.ERROR_TOO_MANY_TIMES.getValueText());
				turingInfo = gson.fromJson(result, TuringError.class);
				break;
			case ERROR_DATA_TYPE_EXCEPTION:// 数据格式异常
				LogUtil.i(TuringCodeType.ERROR_DATA_TYPE_EXCEPTION.getValueText());
				ToastUtil.showShortDebug(mContext, TuringCodeType.ERROR_DATA_TYPE_EXCEPTION.getValueText());
				turingInfo = gson.fromJson(result, TuringError.class);
				break;
			case RECEIVE_RESULT:// 收到消息
				//
				Message message = new Message();
				turingInfo = gson.fromJson(result, TuringBaseInfo.class);
				message.what = turingInfo.getCode();
				Bundle bundle = new Bundle();
				bundle.putString("result", result);
				message.setData(bundle);
				handler.sendMessage(message);
				return;
			default:
				break;
			}
			LogUtil.i(result);
			ToastUtil.showLongDebug(mContext, result);
			LogUtil.i(turingInfo.toString());
			ToastUtil.showLongDebug(mContext, turingInfo.toString());

			TuringBaseInfo turingBaseInfo = new TuringBaseInfo();
			turingBaseInfo.setMessageType(MessageType.FROM);
			turingBaseInfo.setText(turingInfo.getText());
			turingBaseInfo.setTime(new Date());
			TuringChatActivity.getInstance().mData.add(turingBaseInfo);
			TuringChatActivity.getInstance().mTuringInfoAdapter.notifyDataSetChanged();

			// 通知栏
			NotifyUtil notifyUtil = NotifyUtil.getInstance(TuringChatActivity.getInstance(), ID);

			Intent intent = new Intent(TuringChatActivity.getInstance(), TuringChatActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(TuringChatActivity.getInstance(), ID, intent,
					PendingIntent.FLAG_UPDATE_CURRENT);
			String ticker = "小墨消息";
			String title = "小墨title";
			String content = turingInfo.getText();
			boolean sound = true;
			boolean vibrate = true;
			boolean lights = true;
			notifyUtil.notify_normail_moreline(pendingIntent, 0, ticker, title, content, sound, vibrate, lights);
		};
	};
}
