/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import static com.airhacks.rulz.jaxrsclient.JAXRSClientProvider.buildWithURI;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Entity;
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
    public JAXRSClientProvider prov = buildWithURI("http://localhost:8080/doit/api/todos/");
    /*
    private Client client;
    private WebTarget tut;
    
    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target("http://localhost:11080/doit/api/todos");
    }
    //*/
    @Test
    public void crud() {
        JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
        JsonObject todoToCreate = todoBuilder.
                add("caption", "implement").
                add("priority", 42).
                add("description", "mehran added description").
                build();
        this.prov.target().request().post(Entity.json(todoToCreate));
        //this.prov.target().request().post(Entity.json(todoToCreate));
        Response pr = this.prov.target().request(MediaType.APPLICATION_JSON).get();
        assertThat(pr.getStatus(), is(204));
        
        Response res = this.prov.target().request(MediaType.APPLICATION_JSON).get();
        assertThat(res.getStatus(), is(200));
        //JsonObject td = res.readEntity(JsonObject.class);
        JsonArray a = res.readEntity(JsonArray.class);
        //System.out.println(payLoad);
        JsonObject td = a.getJsonObject(0);
        assertTrue(td.getString("caption").startsWith("impl"));
        td = this.prov.target().path("42").request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        
        assertTrue(td.getString("caption").contains("42"));
        res = this.prov.target().path("42").request(MediaType.APPLICATION_JSON).delete();
        assertThat(res.getStatus(), is(204));
    }
    
}
