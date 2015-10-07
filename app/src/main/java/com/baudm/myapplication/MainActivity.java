package com.baudm.myapplication;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements DummyTask.OnEventListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_dialog) {
			Log.d("TestService", "thread: " + Thread.currentThread());
//			final Intent intent = new Intent(this, TestService.class);
//			intent.putExtra(TestService.EXTRA_TIME, 2000L);
//			startService(intent);
			new AlertDialog.Builder(this).setTitle("Dialog").setMessage("This is a Dialog.").setPositiveButton("OK", null).create().show();
			return true;
		}
		if (id == R.id.action_dialog_fragment) {
			AlertDialogFragment.newInstance("DialogFragment", "This is a DialogFragment.", "OK").show(getFragmentManager(), "DialogFragmentTag");
			return true;
		}
		if (id == R.id.action_start_task) {
			new DummyTask(this).execute();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTaskPreExecute() {
		ProgressDialogFragment.newInstance("Doing task...").show(getFragmentManager(), "ProgressDialog");
	}

	@Override
	public void onTaskPostExecute() {
		final ProgressDialogFragment dialogFragment = (ProgressDialogFragment) getFragmentManager().findFragmentByTag("ProgressDialog");
		if (dialogFragment != null) {
			dialogFragment.dismiss();
		}
	}
}
