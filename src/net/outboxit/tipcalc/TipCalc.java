package net.outboxit.tipcalc;

import net.outboxit.tipcalc.R;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;

public class TipCalc extends Activity {
	
	private static final String TOTAL_BILL = "TOTAL_BILL";
	private static final String CURRENT_TIP = "CURRENT_TIP";
	private static final String BILL = "BILL";
	
	private double mTotal;
	private double mTip;
	private double mBill;
	
	EditText edtBill;
	EditText edtTotal;
	EditText edtTip;
	
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
		
		edtBill.addTextChangedListener(edtBillListener);
	}
	
	private TextWatcher edtBillListener = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			try{
				mBill = Double.parseDouble(arg0.toString());
			} catch ( NumberFormatException e){
				mBill = 0.0;
			}
			updateTipAndTotal();
		}

		private void updateTipAndTotal() {
			mTip = Double.parseDouble(edtTip.getText().toString());
			mTotal = mBill + (mBill*mTip);
			
			edtTotal.setText(String.format("%.02f", mTotal));
		}
		
	};
	
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		
		outState.putDouble(BILL, mBill);
		outState.putDouble(CURRENT_TIP, mTip);
		outState.putDouble(TOTAL_BILL, mTotal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
