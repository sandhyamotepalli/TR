package utilities;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


import javax.servlet.http.Part;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.auth.Credentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;


import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;


import com.google.cloud.storage.StorageOptions;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class Storgaeutility {

	
	private static Storage storage = null;
	private static Credentials credentials = null;
	
	//Project Id can be obtained from your GCP console dashboard.
		private static String projectId = "double-archive-262004";
		
		//Create the bucket using the REST API or manually using the Google Cloud Storage Browser Interface.
		private static String bucketName = "technoreachitbucket1";
		
		//Following 4 parameters can be obtained from the Private Key file.
		//Client Id will usually be a numeric string
		private static String clientId = "117800629335333910188";
		
		//Client Email Id is the email Id generated when you create the service account. This will be in the format of: *.iam.gserviceaccount.com
		private static String clientEmailId = "tr-serviceaccount@double-archive-262004.iam.gserviceaccount.com";
		
		//Private key can be obtained from the key file. This will be a very long string within the file. Paste the entire string here.
		private static String privateKey = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDeSFf4HEqKdIhn\nSk2tcvLJM0ze6dlpqm3qKZ3ul4Q6+1sLcSxQ4AFwZ/JYn0cmEO8nqsH+g0slSycM\ncTaM4FyxI60z5xpj7atA/j7KrAMTImUdBGhGVxWkgTwCl0ShZ1P70wLaBahbVKrc\n/O+EQI7ozUA3M2yZmRHfESW+YuhBsVqMIT97lK4zTKo3u2xhhYxQGpgca8xASP+F\ndRfDMNvRr/QiHonqjflkpYK/cc3KlN0eZo0AyPURwEm9axkCslvweotutAJZIFPY\n3FrIrg5VBY8R6/1jBhSnXOeFALo94m16R6fuhi5ri13bC8Im4rUe10OSXBoHujfc\nbbqYkra1AgMBAAECggEAAk59u8XhGP9tShNj0D+aCzkqRYtVQSip8uYuqEwY7NHD\nDpnW1RoDsKpreLyriERgk4QoUjGV4IqNLNM8NuS9/OzfouMsZtyh3qpt4xfNbu/l\nypHvx+3v13xiZojEmXq+zNwbVxyOWMKPzemvck/QVPjTth7uauzDNaRNjb//ttrH\n+cR/dXyrjsB3h9rqXYVOFwjKEaJAJg9A4SpWSrjD+xCrO7d/4tBJGzi0wlrnNA5s\nyHrZCkRu1cO7C1jci72W1shWdBABNhWiBZzJH1y7iuadbSQxx0dxmHPs7RKdfJff\n9cbtpeNNSnPG10Y7ZN1+C1vCVkejLUJqg8aj2kcAkQKBgQD7l3HizigioDAqhGTt\ncAgP00mlF07sVp/TEPq+b29DIXFuKctz6pnTk5iGUF99GU/zJKGR80uXVPoqGuTB\nWWT/kqzwX35AMNbc2XXM9QeXtPRp9nUUWIxN7xZXtM/Fz4WkIW9+epS3jkA1nulk\nzM+V39rS1AWv4Xul5XFSBYev7QKBgQDiLW2ydKRSJP76Im6jj9KnB+0P5lA7WXGl\nZoyRcnDNerwVz0leGljK4QMZPBWuW76CAkwjh4tPMoSyTl/FinAR72N4i8VKazBk\nYSgqjfwAuaikkTAr5O/qTfrPbkbYmrLxN+v6ynXmmH0jl0ikuseS+V3V6HsGg8Co\niDrmw3746QKBgBpbqXDLjGTkR2eM60382LtftW14XaphAOmJ89sTraJo1ItoEYqZ\nX3hNFEOuE/L1Pe0JP2wdAyRdoL/chW7EQNKNuVSNjJqJA1mr5q2MVjlMmfan/Wge\nRsS7canKpaHhteyWHCoyCXaZjpyPg4At/OmExFYmJFpi0yN0QEUJk0eZAoGBAJeL\nBrAGVT8wyKLCRU1UBTmjJuR7VZCeenXvQwoFGq2iqbkXPTpO/uVYZGIZDxHXKMsl\nTOyVWfMDj0OFHchW3IY31ncjd1pIfZEUQ/xXS90UiHlTSmpup41fGEi5w8BHztOu\nu287DkomK9qX97Vf7G5+pK+5aAzSZFjHhmet3zpZAoGAGJfc7bRFWuXiBd0bylJt\necJRR03zK3u8fehUKjkwRUNe6L2BGymK/dEYSeYYMn9qi9MCa3ZV41EQNItqfQLp\n6MM99fNE3ggzwtuwetuIcPgZCncsHyTu8Ui3KB+laTeMXtKyIiv1l/cq94A04cd3\nkyJnKWkS7y6vfme/aw4ej7k=\n-----END PRIVATE KEY-----\n";
		
		//Private Key Id can be obtained from the key file. This is ususally a numeric string.
		private static String privateKeyId = "4a28e011abf6b855cfa03b7f98b2b6981c69e17b";
	

		/**
		 * This method sets the storage credentials for the default storage object. 
		 */
		private static void setDefaultStorageCredentials() {
			try {			
				credentials = ServiceAccountCredentials.fromPkcs8(clientId, clientEmailId, privateKey, privateKeyId, null);			
				storage = StorageOptions.newBuilder()
						.setCredentials(credentials)
						.setProjectId(projectId).build().getService();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	  // [START init]
	  static {
	    storage = StorageOptions.getDefaultInstance().getService();
	  }
	  // [END init]

	  // [START uploadFile]

	  /**
	   * Uploads a file to Google Cloud Storage to the bucket specified in the BUCKET_NAME
	   * environment variable, appending a timestamp to end of the uploaded filename.
	   */
	  // Note: this sample assumes small files are uploaded. For large files or streams use:
	  // Storage.writer(BlobInfo blobInfo, Storage.BlobWriteOption... options)
	  public static String uploadFile(Part filePart) throws IOException {
		  
		  try {
				setDefaultStorageCredentials();
	    DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-dd-HHmmssSSS-");
	    DateTime dt = DateTime.now(DateTimeZone.UTC);
	    String dtString = dt.toString(dtf);
	    final String fileName = dtString + filePart.getSubmittedFileName();
	    
	    // The InputStream is closed by default, so we don't need to close it here
	    // Read InputStream into a ByteArrayOutputStream.
	    InputStream is = filePart.getInputStream();
	   // ByteArrayOutputStream os = new ByteArrayOutputStream();
	    BlobInfo build = BlobInfo
        .newBuilder(bucketName, fileName)
        // Modify access list to allow all users with link to read file
        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
        .build();

	  //  BlobWriteOption[] readBuf = new BlobWriteOption[4096];
	   /* while (is.available() > 0) {
	      int bytesRead = is.read(readBuf);
	      os.write(readBuf, 0, bytesRead);
	    }*/
	    
	 // Convert ByteArrayOutputStream into byte[]
	    @SuppressWarnings("deprecation")
		BlobInfo blobInfo = 
	        storage.create(build, is);
	           
	   
	    // return the public download link
	    System.out.println("blobid:"+blobInfo.getBlobId().getName());
	   // return blobInfo.getMediaLink();
	    return blobInfo.getGeneratedId();
	    } catch (Exception e) {
		   e.printStackTrace();
			return null;
		}
    }
	  // [END uploadFile]

	
	  // [START downloadFile]
	  
	 /* public static byte[] downloadFile(String genId) throws FileNotFoundException, IOException {
			setDefaultStorageCredentials();		
			return storage.get(bucketName).get(genId).getContent();
		}*/
	  
	  // [END downloadFile]

	
	  
	  /**
	   * Example of creating a signed URL that is valid for 2 weeks, using the default credentials for
	   * signing the URL.
	   */
	  // [TARGET signUrl(BlobInfo, long, TimeUnit, SignUrlOption...)]
	  // [VARIABLE "my_unique_bucket"]
	  // [VARIABLE "my_blob_name"]
	  public static URL signUrl(String blobName) {
		try{
		  setDefaultStorageCredentials();	
	    // [START signUrl]
	    URL signedUrl =
	        storage.signUrl(BlobInfo.newBuilder(bucketName, blobName).build(), 14, TimeUnit.DAYS);
	    // [END signUrl]
	    return signedUrl;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	  }

	  
}
