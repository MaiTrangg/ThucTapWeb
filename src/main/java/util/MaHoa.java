package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;





public class MaHoa {

	
	public static String toSHA1(String mk) {
		String salt = "ashfjdgkdngnbj;.njkgjk";
		String result = null;
		mk = mk + salt;
		try {
			byte[] dataBytes = mk.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	public static void main(String[] args) {
		String s = toSHA1("123");
		System.out.println(s);
//		System.out.println(decodeSHA1(s));
	}

}

