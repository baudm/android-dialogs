package com.baudm.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * DialogFragment which wraps a simple AlertDialog
 *
 * @author Darwin Bautista
 */
public final class AlertDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

	public interface OnClickListener {
		int BUTTON_POSITIVE = DialogInterface.BUTTON_POSITIVE;
		int BUTTON_NEGATIVE = DialogInterface.BUTTON_NEGATIVE;

		void onClick(AlertDialogFragment dialogFragment, int which);
	}

	private static final String ARG_TITLE = "title";
	private static final String ARG_MESSAGE = "message";
	private static final String ARG_POSITIVE_BUTTON_TEXT = "positive_button_text";
	private static final String ARG_NEGATIVE_BUTTON_TEXT = "negative_button_text";

	private OnClickListener onClickListener;

	public static AlertDialogFragment newInstance(String title, String message, String positiveButtonText) {
		return newInstance(title, message, positiveButtonText, null);
	}

	public static AlertDialogFragment newInstance(String title, String message, String positiveButtonText, String negativeButtonText) {
		final Bundle args = new Bundle();
		args.putString(ARG_TITLE, title);
		args.putString(ARG_MESSAGE, message);
		args.putString(ARG_POSITIVE_BUTTON_TEXT, positiveButtonText);
		args.putString(ARG_NEGATIVE_BUTTON_TEXT, negativeButtonText);

		final AlertDialogFragment fragment = new AlertDialogFragment();
		fragment.setArguments(args);
		fragment.setCancelable(false);
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Target fragment takes precedence over parent Activity
		final Fragment fragment = getTargetFragment();
		if (fragment instanceof OnClickListener) {
			onClickListener = (OnClickListener) fragment;
		} else {
			try {
				onClickListener = (OnClickListener) activity;
			} catch (ClassCastException e) {
				// Ignore, it means activity is not interested in getting feedback
			}
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		onClickListener = null;
	}

	/**
	 * Should be used by other Fragments in order to be set as listeners
	 * @param fragment
	 * @param tag
	 */
	public void show(Fragment fragment, String tag) {
		// Prevent duplicate dialogs
		if (fragment.getFragmentManager().findFragmentByTag(tag) != null) {
			return;
		}
		if (fragment instanceof OnClickListener) {
			setTargetFragment(fragment, 0);
		}
		show(fragment.getFragmentManager(), tag);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Bundle args = getArguments();
		if (args == null) {
			return super.onCreateDialog(savedInstanceState);
		}

		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
				.setMessage(args.getString(ARG_MESSAGE))
				.setPositiveButton(args.getString(ARG_POSITIVE_BUTTON_TEXT), this);

		final String title = args.getString(ARG_TITLE);
		if (title != null) {
			builder.setTitle(title);
		}

		final String negativeButtonText = args.getString(ARG_NEGATIVE_BUTTON_TEXT);
		if (negativeButtonText != null) {
			builder.setNegativeButton(negativeButtonText, this);
		}

		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialogInterface, int which) {
		if (onClickListener != null) {
			onClickListener.onClick(this, which);
		}
	}
}
