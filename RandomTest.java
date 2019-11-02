package cn.io.study3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * �����ȡ��д����
 * @author dell
 *
 */

public class RandomTest {
	public static void FileReadSplit(String srcPath) throws IOException {
		File src=new File(srcPath);
		long len=src.length();//�ļ����ȣ�
		System.out.println(len);
		int blockSize=100;//ÿ���С
		int size=(int)Math.ceil(len*1.0/blockSize);//ʵ�ʿ���
		System.out.println(size);
		int beginPos=0;
		int actualSize=0;
		for(int i=0;i<size;i++) {
			beginPos=i*blockSize;
			if(i==size-1) {
				actualSize=(int)len;
			}else {
				actualSize=blockSize;
				len-=blockSize;
			}
			System.out.println(i+1+"-->"+beginPos+"-->"+actualSize);
			split(i+1,beginPos,actualSize,src);
		}
		
	}
	private static void split(int i, int beginPos, int actualSize,File file) throws IOException {
		// TODO Auto-generated method stub
		//�ֿ�˼��-ָ����ȡ��С
		RandomAccessFile raf=new RandomAccessFile(file,"r");
		raf.seek(beginPos);
		//��ȡ
		byte[] flush=new byte[1024];
		int len=-1;
		while((len=raf.read(flush))!=-1) {
			if(actualSize>len) {
				System.out.println(new String(flush,0,len));
				actualSize-=len;
			}else {
				System.out.println(new String(flush,0,actualSize));
			}
		}
	}
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf=new RandomAccessFile(new File("aaa.txt"),"r");
		
		//�����ȡ-ָ��λ�ÿ�ʼ��ֱ����ȡ��ʣ�����ݡ�
		raf.seek(2);
		//��ȡ-
		byte[] flush=new byte[1024];
		int len=-1;
		while((len=raf.read(flush))!=-1) {
			System.out.println(new String(flush,0,len));
		}
		
		FileReadSplit("E:\\workspace-java\\IO������\\src\\cn\\io\\study3\\DecorateTest01.java");
	}

}
