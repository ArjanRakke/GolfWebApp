package gooiseGolfclub.webservices;

import java.security.Key;
import java.util.AbstractMap.SimpleEntry;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import gooiseGolfclub.persistensie.LedenDao;
import gooiseGolfclub.persistensie.LedenMySqlDaoImpl;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Path("/authentication")
public class AuthenticationResource {
	final static public Key key = MacProvider.generateKey();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("username") String username,
									 @FormParam("password") String password) {
		try {
			// Authenticate the user against the database
			LedenDao lDao = new LedenMySqlDaoImpl();
			String role = lDao.findRoleForUser(username, password);
			
			if (role == null) {
				throw new IllegalArgumentException("No user found!");
			}
			
			String token = createToken(username, role);
			
			SimpleEntry<String, String> JWT = new SimpleEntry<String, String>("JWT", token);
			return Response.ok(JWT).build();
		} 
		
		catch(JwtException | IllegalArgumentException e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
			
	}
	
	private String createToken(String username, String role) throws JwtException {
		Calendar expiration = Calendar.getInstance();
		expiration.add(Calendar.MINUTE, 30);
		
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(expiration.getTime())
				.claim("role", role)
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}
}