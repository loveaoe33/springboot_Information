package Information_Object;

import java.awt.RenderingHints.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Entity;





public class Admin_Lib {
	private final java.security.Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	// password token
	private static final int SALT_LENGTH = 16;
	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 256;

	// 產生 token：密碼 + salt -> hash
	private String generateToken(String password) throws Exception {
		byte[] salt = generateSalt();
		byte[] hash = hashPassword(password.toCharArray(), salt);
		return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);

	}

	// 驗證 token 是否對應原密碼
	private boolean validateToken(String password, String token)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = token.split(":");
		if (parts.length != 2)
			return false;
		byte[] salt = Base64.getDecoder().decode(parts[0]);
		byte[] expectedHash = Base64.getDecoder().decode(parts[1]);
		byte[] actualHash = hashPassword(password.toCharArray(), salt);
		return Arrays.equals(expectedHash, actualHash);
	}

	private byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salf = new byte[SALT_LENGTH];
		random.nextBytes(salf);
		return salf;

	}

	// 將密碼與 salt hash 起來
	private byte[] hashPassword(final char[] password, final byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		return factory.generateSecret(spec).getEncoded();
	}

	// 取得token
	public String getAccount_Token(String password) throws Exception {

		return generateToken(password);
	}

	// 驗證token
	public boolean vailAccount_Token(String password, String token)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		return validateToken(password, token);
	}

	public String jsonWebToken() {

		return "Sucess";
	}

	public String createJwt(String username) {
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + 3600;
		Date now = new Date(nowMillis);
		Date exp = new Date(expMillis);
		return Jwts.builder().setSubject("user-info") // 主題
				.claim("username", username) // 自訂內容
				.setIssuedAt(now) // 發行時間
				.setExpiration(exp) // 過期時間
				.signWith(key) // 使用密鑰簽名
				.compact();
	}

	public String parseJwt(String jwt) { // validateToken
		try {

			Jws<Claims> claimsJWS = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
			Claims claims = claimsJWS.getBody();
			System.out.println("JWT 驗證成功: ");
			System.out.println("使用者: " + claims.get("username"));
			System.out.println("到期時間: " + claims.getExpiration());

			return "Sucess";
		} catch (JwtException e) {
			System.out.println("JWT 驗證失敗: " + e.getMessage());
			return "fail";

		}

	}

	public String hashPassword(String password) throws NoSuchAlgorithmException { // password hash
		StringBuilder stringBuilder = new StringBuilder();
		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(password.getBytes());
			for (byte b : hashBytes) {
				stringBuilder.append(String.format("%02x", b));
			}

			return stringBuilder.toString();

		} catch (NoSuchAlgorithmException e) {
			System.out.println("Passwod hashError" + e.toString());
			return "fail";
		}

	}

	public boolean accountCheck(String loginPassword, String hashPassword) { // hash check
		try {
			return (hashPassword(loginPassword).equals(hashPassword)) ? true : false;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
