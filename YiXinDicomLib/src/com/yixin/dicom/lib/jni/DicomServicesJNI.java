package com.yixin.dicom.lib.jni;

/**
 * JNI �ײ㷽����װ
 * 
 * @author ChenRui
 * 
 */
public class DicomServicesJNI
{
	
	static{
		System.loadLibrary(""); // TODO:������д����ʱ�Ŀ�����
	}
	
	/**
	 * ��ʾӰ��
	 * 
	 * @param params
	 *            ����
	 * @param imageDataArray
	 *            �������
	 * @return 1Ϊ����
	 */
	public native int ShowDicomImage(String params, byte[] imageDataArray);
}
