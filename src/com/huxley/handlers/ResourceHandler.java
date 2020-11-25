package com.huxley.handlers;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;

public class ResourceHandler {
	private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/";

    public ResourceHandler() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        client.addFilter(new LoggingFilter());
      
    }

    public String getMessage() throws UniformInterfaceException {
        WebResource resource = webResource;
        return resource.accept(MediaType.APPLICATION_JSON).get(String.class);
    }

/*    public void putMessage(Object requestEntity) throws UniformInterfaceException {
        webResource.type(MediaType.APPLICATION_JSON).put(requestEntity);
    }*/

    public void close() {
        client.destroy();
    }

    public void setUsernamePassword(String username, String password) {
        client.addFilter(new HTTPBasicAuthFilter(username, password));
    }
    
    public void setResource(String resource){
    	webResource = client.resource(BASE_URI).path(resource);
    }
}
