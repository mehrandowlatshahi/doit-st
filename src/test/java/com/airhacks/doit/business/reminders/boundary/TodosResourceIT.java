/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import static com.airhacks.rulz.jaxrsclient.JAXRSClientProvider.buildWithURI;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author ciran_000
 */
public class TodosResourceIT {
    
    @Rule
    public JAXRSClientProvider prov = buildWithURI("http://localhost:8080/doit/api/todos");
    /*
    private Client client;
    private WebTarget tut;
    
    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target("http://localhost:8080/doit/api/todos");
    }
    //*/
    @Test
    public void fetchToDos() {
        Response res = this.prov.target().request(MediaType.APPLICATION_JSON).get();
        assertThat(res.getStatus(), is(200));
        JsonObject payLoad = res.readEntity(JsonObject.class);
        System.out.println(payLoad);
        assertTrue(payLoad.getString("caption").startsWith("impl"));
    }
    
}
