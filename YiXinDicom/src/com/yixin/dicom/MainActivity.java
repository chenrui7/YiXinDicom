package com.yixin.dicom;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener
{
	
	private ImageView	img;
	private TextView	msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		this.img = (ImageView) findViewById(R.id.imageView1);
		this.msg = (TextView) findViewById(R.id.textView1);
		View btn8 = findViewById(R.id.btn8);
		btn8.setOnClickListener(this);
		findViewById(R.id.btn32).setOnClickListener(this);
		findViewById(R.id.btn_normal).setOnClickListener(this);
		
		btn8.performClick(); // µã»÷
		
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn8:
				show8BitImage();
				break;
			case R.id.btn32:
				show32BitImage();
				break;
			
			case R.id.btn_normal:
				showDefaultImage();
				break;
		}
	}
	
	// Õý³£Í¼Æ¬
	private void showDefaultImage()
	{
		try
		{
			InputStream stream = getAssets().open("8bit.bmp");
			Bitmap bmp = BitmapFactory.decodeStream(stream);
			img.setImageBitmap(bmp);
			msg.setText("Ä¬ÈÏÍ¼Æ¬");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	// 8 Î»Í¼Æ¬
	private void show8BitImage()
	{
		try
		{
			img.setImageBitmap(RawToBitMap.convert8bit(
					getAssets().open("8bit.raw"), 512, 512));
			msg.setText("8 bit ");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	// 24 Î»Í¼Æ¬
	private void show32BitImage()
	{
		try
		{
			img.setImageBitmap(RawToBitMap.convert24bit(
					getAssets().open("24bit.raw"), 512, 512));
			msg.setText("24 bit ");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
