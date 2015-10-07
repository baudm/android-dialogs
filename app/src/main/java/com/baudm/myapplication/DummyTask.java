package com.baudm.myapplication;

import android.os.AsyncTask;

/**
 * Created by dbautista on 9/9/15.
 */
public final class DummyTask extends AsyncTask<Void, Void, Void> {

	public interface OnEventListener {
		void onTaskPreExecute();
		void onTaskPostExecute();
	}

	private OnEventListener listener;

	public DummyTask(OnEventListener listener) {
		this.listener = listener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		listener.onTaskPreExecute();
	}

	@Override
	protected Void doInBackground(Void... voids) {
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// nothing to do
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void aVoid) {
		super.onPostExecute(aVoid);
		listener.onTaskPostExecute();
	}
}
