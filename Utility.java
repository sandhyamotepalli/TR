package utilities;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
//import java.util.Base64;

import javax.servlet.http.Part;

import org.apache.tomcat.util.codec.binary.Base64;

public class Utility {
public static byte[] inputStreamToByteArray(InputStream in) {
	ByteArrayOutputStream output = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int count;
    try {
		while ((count = in.read(buffer)) != -1)
   output.write(buffer, 0, count);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   byte[] contents = output.toByteArray();
		return contents;
}

public static String getImageToString(Blob blob)
{
	byte[] o = null;
        try 
{
            o = Utility.inputStreamToByteArray(blob.getBinaryStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String imgDataBase64=new String(Base64.getEncoder().encode(imgData));
    return new String(Base64.encodeBase64String(o));
}


public static String extractFileName(Part part) {
String contentDisp = part.getHeader("content-disposition");
String[] items = contentDisp.split(";");
for (String s : items) {
if (s.trim().startsWith("filename")) {
String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
clientFileName = clientFileName.replace("\\", "/");
int i = clientFileName.lastIndexOf('/');
return clientFileName.substring(i + 1);
}
}
return null;
}

public static String extractFileNames(Part part) {
String contentDisp = part.getHeader("content-disposition");
System.out.println("contentDisp"+contentDisp);
String[] items = contentDisp.split(";");
for (String s : items) {
if (s.trim().startsWith("filename")) {
String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
//clientFileName = clientFileName.replace("\\", "/");
//int i = clientFileName.lastIndexOf('/');
return clientFileName;
}
}
return null;
}
}