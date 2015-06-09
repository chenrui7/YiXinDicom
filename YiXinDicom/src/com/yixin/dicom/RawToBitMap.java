package com.yixin.dicom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import android.graphics.Bitmap;

/**
 * RawתBitmap
 * 
 * @author ChenRui
 * 
 */
public class RawToBitMap
{
	/**
	 * �����ж�ȡ����
	 * 
	 * @param stream
	 *            ������
	 * @return
	 */
	public static byte[] readByteArrayFormStream(InputStream stream)
	{
		try
		{
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			int len = 0;
			byte[] tmp = new byte[1024];
			while ((len = stream.read(tmp)) != -1)
			{
				outStream.write(tmp, 0, len);
			}
			
			byte[] data = outStream.toByteArray();
			
			return data;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return new byte[0];
		}
	}
	
	/**
	 * 8λ�Ҷ�תBitmap
	 * 
	 * ͼ���ȱ����ܱ�4����
	 * 
	 * @param data
	 *            ������
	 * @param width
	 *            ͼ����
	 * @param height
	 *            ͼ��߶�
	 * @return
	 */
	public static Bitmap convert8bit(byte[] data, int width, int height)
	{
		byte[] Bits = new byte[data.length * 4]; //RGBA ����
		
		int i;
		for (i = 0; i < data.length; i++)
		{
			// ԭ��4���ֽڱ�ʾһ���Ҷȣ���RGB  = �Ҷ�ֵ�����һ��Alpha = 0xff;
			Bits[i * 4] = Bits[i * 4 + 1] = Bits[i * 4 + 2] = data[i];
			Bits[i * 4 + 3] = -1; //0xff
		}
		
		// Bitmap.Config.ARGB_8888 ��ʾ��ͼ��ģʽΪ8λ
		Bitmap bmp = Bitmap
				.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bmp.copyPixelsFromBuffer(ByteBuffer.wrap(Bits));
		
		return bmp;
	}
	
	/**
	 * 24λ�Ҷ�תBitmap
	 * 
	 * ͼ���ȱ����ܱ�4����
	 * 
	 * @param data
	 *            ������
	 * @param width
	 *            ͼ����
	 * @param height
	 *            ͼ��߶�
	 * @return
	 */
	public static Bitmap convert24bit(byte[] data, int width, int height)
	{
		byte[] Bits = new byte[data.length * 4]; //RGBA ����
		
		int i;
		
		// data.length / 3 ��ʾ 3λΪһ��
		for (i = 0; i < data.length / 3; i++)
		{
			// ԭ��24λ���в�ɫ�ģ�����Ҫ����3λ�����һλAlpha = 0xff;
			Bits[i * 4] = data[i * 3];
			Bits[i * 4 + 1] = data[i * 3 + 1];
			Bits[i * 4 + 2] = data[i * 3 + 2];
			Bits[i * 4 + 3] = -1;
		}
		
		// Bitmap.Config.ARGB_8888 ��ʾ��ͼ��ģʽΪ8λ
		Bitmap bmp = Bitmap
				.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bmp.copyPixelsFromBuffer(ByteBuffer.wrap(Bits));
		
		return bmp;
	}
	
	/**
	 * 8λ�Ҷ�תBitmap
	 * 
	 * @param stream
	 *            ������
	 * @param width
	 *            ͼ����
	 * @param height
	 *            ͼ��߶�
	 * @return
	 */
	public static Bitmap convert8bit(InputStream stream, int width, int height)
	{
		return convert8bit(readByteArrayFormStream(stream), width, height);
	}
	
	/**
	 * 24λ�Ҷ�תBitmap
	 * 
	 * @param data
	 *            ������
	 * @param width
	 *            ͼ����
	 * @param height
	 *            ͼ��߶�
	 * @return
	 */
	public static Bitmap convert24bit(InputStream stream, int width, int height)
	{
		return convert24bit(readByteArrayFormStream(stream), width, height);
	}
}
