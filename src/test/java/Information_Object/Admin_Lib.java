package Information_Object;

import java.awt.RenderingHints.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Entity;


public class Admin_Lib {
	private final 	java.security.Key key=Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public String jsonWebToken() {
		
		return "Sucess";
	}
	
	public String createJwt(String username) {
		long nowMillis=System.currentTimeMillis();
		long expMillis=nowMillis+3600;
		Date now=new Date(nowMillis);
		Date exp=new Date(expMillis);
        return Jwts.builder()
                .setSubject("user-info")              // 主題
                .claim("username", username)          // 自訂內容
                .setIssuedAt(now)                     // 發行時間
                .setExpiration(exp)                   // 過期時間
                .signWith(key)                        // 使用密鑰簽名
                .compact();		
	}
	
	public String parseJwt(String jwt) {  //validateToken
		try {
			
			Jws<Claims> claimsJWS=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            Claims claims = claimsJWS.getBody();
            System.out.println("JWT 驗證成功: ");
            System.out.println("使用者: " + claims.get("username"));
            System.out.println("到期時間: " + claims.getExpiration());

			return "Sucess";
		}catch(JwtException e) {
            System.out.println("JWT 驗證失敗: " + e.getMessage());
    		return "fail";

		}
		
	}
	public String hashPassword(String password) throws NoSuchAlgorithmException { // password hash
		StringBuilder stringBuilder=new StringBuilder();
	    try {
	    	
	    	MessageDigest digest=MessageDigest.getInstance("SHA-256");   
			byte[] hashBytes=digest.digest(password.getBytes());
			for(byte b:hashBytes) {
				stringBuilder.append(String.format("%02x", b));
			}
			
			return stringBuilder.toString();
	    	
	    }catch(NoSuchAlgorithmException e) {
	    	System.out.println("Passwod hashError"+e.toString());
	    	return "fail";
	    }
	
	}
	public boolean accountCheck(String loginPassword,String hashPassword) {  //hash check
		try {
			return (hashPassword(loginPassword).equals(hashPassword))?true:false;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
