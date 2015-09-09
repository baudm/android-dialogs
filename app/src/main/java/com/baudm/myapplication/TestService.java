package com.baudm.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by dbautista on 9/3/15.
 */
public final class TestService extends IntentService {

	private static final String TAG = TestService.class.getName();

	public static final String EXTRA_TIME = TAG + ".intent.extra.TIME";

	public TestService() {
		super("TestService");
	}

	private void delay(long sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate: " + Thread.currentThread());
		delay(2000L);

	}

	@Override
	protected void onHandleIntent(Intent intent) {
		final long sleepTime = intent.getLongExtra(EXTRA_TIME, 1000L);
		Log.d(TAG, "onHandleIntent: " + sleepTime + ", t: " + Thread.currentThread());
		delay(sleepTime);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy: " + Thread.currentThread());
	}
}
