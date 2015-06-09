package com.yixin.dicom.lib.jni;

/**
 * JNI 底层方法封装
 * 
 * @author ChenRui
 * 
 */
public class DicomServicesJNI
{
	
	static{
		System.loadLibrary(""); // TODO:这里填写编译时的库名。
	}
	
	/**
	 * 显示影像
	 * 
	 * @param params
	 *            参数
	 * @param imageDataArray
	 *            输出数据
	 * @return 1为正常
	 */
	public native int ShowDicomImage(String params, byte[] imageDataArray);
}
