package security;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import dao.UserDao;

/**
 * 
 * @author arun.chougule
 * Class to implement security filter
 */
@Provider
public class SecurityFilter implements  ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER_KEY= "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX= "Basic ";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
			List<String> authHeader=requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			
			if(authHeader!=null && authHeader.size()>0)
			{
				String authToken=authHeader.get(0);
				authToken=authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodedString=Base64.decodeAsString(authToken);
				StringTokenizer tokenizer=new StringTokenizer(decodedString,":");
				String username=tokenizer.nextToken();
				String password=tokenizer.nextToken();
				
				Map<String,String> userAuthMap=new UserDao().getUserAuthMap();
				Set<String> userIdSet=userAuthMap.keySet();
				for(String uid:userIdSet)
				{
					if(uid.equalsIgnoreCase(username)&& userAuthMap.get(uid).equals(password))
					{
						return;
					}
				}
			}
			
			Response unauthorized=Response.status(Response.Status.UNAUTHORIZED)
											.entity("Unauthorized User")
											.build();
			requestContext.abortWith(unauthorized);
		
	}

}
