package net.outboxit.tipcalc;

import net.outboxit.tipcalc.R;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class TipCalc extends Activity {
	
	private static final String TOTAL_BILL = "TOTAL_BILL";
	private static final String CURRENT_TIP = "CURRENT_TIP";
	private static final String BILL = "BILL";
	private static final String SPLIT = "SPLIT";
	
	private double mTotal;
	private double mTip;
	private double mBill;
	private double mSplit;
	
	EditText edtBill;
	EditText edtTotal;
	EditText edtTip;
	EditText edtPersons;
	EditText edtSplit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(savedInstanceState == null){
			mTotal=0.0;
			mTip=0.10;
			mBill=0.0;
		} else {
			mTotal = savedInstanceState.getDouble(TOTAL_BILL);
			mTip = savedInstanceState.getDouble(CURRENT_TIP);
			mBill = savedInstanceState.getDouble(BILL);		
		}
		
		edtBill = (EditText) findViewById(R.id.edt_bill);
		edtTotal = (EditText) findViewById(R.id.edt_total);
		edtTip = (EditText) findViewById(R.id.edt_tip);
		edtPersons = (EditText) findViewById(R.id.edt_persons);
		edtSplit = (EditText) findViewById(R.id.edt_split);
		
		edtBill.addTextChangedListener(edtBillListener);
		edtTip.addTextChangedListener(edtTipListener);
	}
	
	private TextWatcher edtTipListener = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable s) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			try{
				mTip = Double.parseDouble(s.toString());
			} catch ( NumberFormatException e){
				mTip = 0.10;
			}
			updateTipAndTotal();
		}
	};
	
	private TextWatcher edtBillListener = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable arg0) {
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			try{
				mBill = Double.parseDouble(s.toString());
			} catch ( NumberFormatException e){
				mBill = 0.0;
			}
			updateTipAndTotal();
		}
	};
	
	private void updateTipAndTotal() {
		//mBill = Double.parseDouble(edtBill.getText().toString());
		//mTip = Double.parseDouble(edtTip.getText().toString());
		mTotal = mBill + (mBill*mTip);
		
		edtTotal.setText(String.format("%.02f", mTotal));
	}
	
	public void SplitBill(View v){
		mBill = Double.parseDouble(edtBill.getText().toString());
		mTip = Double.parseDouble(edtTip.getText().toString());
		int persons = Integer.parseInt(edtPersons.getText().toString());
		mTotal = mBill + (mBill*mTip);
		try{
			mSplit = mTotal / persons;
		} catch (Exception e){
			mSplit = mTotal;
		}
		edtSplit.setText(String.format("%.02f", mSplit));
	}
	
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		
		outState.putDouble(BILL, mBill);
		outState.putDouble(CURRENT_TIP, mTip);
		outState.putDouble(TOTAL_BILL, mTotal);
		outState.putDouble(SPLIT, mSplit);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
