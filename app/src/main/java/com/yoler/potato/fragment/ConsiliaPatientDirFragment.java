package com.yoler.potato.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoler.potato.R;

public class ConsiliaPatientDirFragment extends BaseFragment {

	@Override
	public String getTagName() {
		return null;
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_consilia_patient_dir;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
