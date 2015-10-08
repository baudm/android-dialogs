package com.baudm.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;

public class TaskFragment extends Fragment implements DummyTask.OnEventListener {

	public interface OnEventListener {
		void onTaskPreExecute();
		void onTaskPostExecute();
	}

	private DummyTask task;
	private OnEventListener listener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (TaskFragment.OnEventListener) activity;
		} catch (ClassCastException e) {
			//
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		listener = null;
	}

	public void executeTask() {
		task = new DummyTask(this);
		task.execute();
	}

	@Override
	public void onTaskPreExecute() {
		if (listener != null) {
			listener.onTaskPreExecute();
		}
	}

	@Override
	public void onTaskPostExecute() {
		if (listener != null) {
			listener.onTaskPostExecute();
		}
	}
}
