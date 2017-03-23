package com.test.demo.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.test.demo.constants.ApplicationConstants;
import com.test.demo.utils.DatastoreUtils;

@SuppressWarnings("serial")
@Path("/hello")
public class RestController extends HttpServlet implements ApplicationConstants {

  private static final Logger LOG = Logger.getLogger(RestController.class.getName());
  // request object to get remoteAddress of client machine
  @Context
  private HttpServletRequest req;

  /*
   * POST call that accepts user name and invoke google Datastore util to save user details
   */
  @POST
  public Response greetVisitedUser(@FormParam(ApplicationConstants.form_parameter) final String name) throws IOException {
    if ((name != null) && !("".equalsIgnoreCase(name))) {
      RestController.LOG.info("name: " + name);
      final String userIp = this.req.getRemoteAddr();
      DatastoreUtils.saveUserDetailsToDatastore(userIp, name);
      return Response.status(200).entity(new JSONObject().put(ApplicationConstants.json_key, name).toString()).build();

    } else {
      RestController.LOG.severe(ApplicationConstants.error_message);
      return Response.status(400).build();
    }
  }

  @GET
  public Response retriveEntityFromDatastore(){
    final JSONArray array = DatastoreUtils.retrieveEntriesFromDatastore();
    //final String sample = "[{\"0\":\"155.201.35.117\",\"1\":\"2017-03-23 (19:50:52.731) final IST\",\"2\":\"John\"},{\"0\":\"155.201.35.117\",\"1\":\"2017-03-23 (21:36:16.906) final IST\",\"2\":\"rameshwar\"},{\"0\":\"155.201.35.117\",\"1\":\"2017-03-23 (21:36:56.535) final IST\",\"2\":\"Test user\"}]";
    return Response.status(200).entity(array.toString()).build();
  }
}