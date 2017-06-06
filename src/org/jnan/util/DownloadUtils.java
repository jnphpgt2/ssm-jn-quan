package org.jnan.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class DownloadUtils {
	
	 /** 
	  * 对文件进行压�? 
	  *  
	  * @param source 
	  *            源文�? 
	  * @param target 
	  *            目标文件 
	  * @throws IOException 
	  */  
	 public static void zipFile(String source, String target) throws IOException {
		  FileInputStream fin = null;  
		  FileOutputStream fout = null;  
		  GZIPOutputStream gzout = null;  
		  try {
			   fin = new FileInputStream(source);  
			   fout = new FileOutputStream(target);  
			   gzout = new GZIPOutputStream(fout);  
			   byte[] buf = new byte[1024];  
			   int num;
			   while ((num = fin.read(buf)) != -1) {  
			    gzout.write(buf, 0, num);  
			   }  
		  } finally {  
			   if (gzout != null)  
			    gzout.close();  
			   if (fout != null)  
			    fout.close();  
			   if (fin != null)  
			    fin.close();  
		  }  
	 }  

}
