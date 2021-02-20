package com.reptilesoft.plm;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PhoneLockManager extends Activity implements OnClickListener {
	
	private KeyguardManager keymgr;
	private KeyguardManager.KeyguardLock keylock;
	private Button btn_on, btn_off;
	private ImageButton btn_site;
	
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        keymgr = (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
        keylock = keymgr.newKeyguardLock("MY APP");
        
        btn_on = (Button)findViewById(R.id.btn_on);
        btn_on.setOnClickListener(this);
        btn_off = (Button)findViewById(R.id.btn_off);
        btn_off.setOnClickListener(this);
        btn_site = (ImageButton)findViewById(R.id.btn_site);
        btn_site.setOnClickListener(this);
    }

	public void onClick(View v) {
		
		switch(v.getId())
		{
		case R.id.btn_on:
	        {
	        	keylock.reenableKeyguard();
	        	Toast.makeText(this, "Keyguard on.", Toast.LENGTH_SHORT).show();
	        	finish();
	        }
	        break;
		case R.id.btn_off:
	        {
	        	keylock.disableKeyguard();
	        	Toast.makeText(this, "Keyguard off.", Toast.LENGTH_SHORT).show();
	        	finish();
	        }
	        break;
		case R.id.btn_site:
			{
				Intent i = new Intent(android.content.Intent.ACTION_SEND);
				i.setType("plain/text");
				i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"contact@reptilesoft.com"});
				i.putExtra(android.content.Intent.EXTRA_SUBJECT, "[PLM 1.0 Bug report / comments");
				startActivity(Intent.createChooser(i, "Send mail..."));
			}
			break;

		}
	}
}