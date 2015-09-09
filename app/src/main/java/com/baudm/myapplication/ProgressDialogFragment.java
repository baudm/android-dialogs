package com.baudm.myapplication;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

/**
 * DialogFragment which wraps a ProgressDialog
 *
 * @author Darwin Bautista
 */
public final class ProgressDialogFragment extends DialogFragment {

	private static final String ARG_MESSAGE = "message";

	public static ProgressDialogFragment newInstance(String message) {
		final Bundle args = new Bundle();
		args.putString(ARG_MESSAGE, message);
		final ProgressDialogFragment fragment = new ProgressDialogFragment();
		fragment.setArguments(args);
		fragment.setCancelable(false);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Bundle args = getArguments();
		if (args == null) {
			return super.onCreateDialog(savedInstanceState);
		}
		final ProgressDialog dialog = new ProgressDialog(getActivity());
		dialog.setMessage(args.getString(ARG_MESSAGE));
		dialog.setCancelable(false);
		return dialog;
	}
}
