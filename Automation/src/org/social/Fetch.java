package org.social;

import javax.ws.rs.Path;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.social.dao.ModuleDAO;
import org.social.dao.TestRunDAO;
import org.social.database.DatabaseOps;
import org.social.utils.DataUtils;

@Path("/Automation")
public class Fetch {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(@Context UriInfo uriInfo) {
		String query = uriInfo.getRequestUri().getQuery();

		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();

		String nameParam = queryParams.getFirst("name");
		return "Hi Welcome, " + nameParam;
	}

	@GET
	@Path("/module")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchSingleModule(@Context UriInfo uriInfo) {

		String query = uriInfo.getRequestUri().getQuery();
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();

		DatabaseOps ops = new DatabaseOps(new ModuleDAO());
		List<Object> list = ops.fetchDataBasedOnCriteria(new ModuleDAO(), (MultivaluedStringMap) queryParams);
		JSONObject responseDetailsJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (Object obj : list) {
			ModuleDAO temp = (ModuleDAO) obj;
			JSONObject json = DataUtils.convertObjectToJson(temp);
			jsonArray.put(json);
		}
		responseDetailsJson.put("modules", jsonArray);
		return responseDetailsJson.toString();
	}

	@GET
	@Path("/allModules")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchAllModulesData() {

		DatabaseOps ops = new DatabaseOps(new ModuleDAO());
		List<Object> list = ops.fetchAllData(new ModuleDAO());
		JSONObject responseDetailsJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (Object obj : list) {
			ModuleDAO temp = (ModuleDAO) obj;
			JSONObject json = DataUtils.convertObjectToJson(temp);
			jsonArray.put(json);
		}
		responseDetailsJson.put("modules", jsonArray);
		return responseDetailsJson.toString();
	}

	private Map<String, String> convertMultiToRegularMap(MultivaluedMap<String, String> m) {
		Map<String, String> map = new HashMap<String, String>();
		if (m == null) {
			return map;
		}
		for (Entry<String, List<String>> entry : m.entrySet()) {
			StringBuilder sb = new StringBuilder();
			for (String s : entry.getValue()) {
				if (sb.length() > 0) {
					sb.append(',');
				}
				sb.append(s);
			}
			map.put(entry.getKey(), sb.toString());
		}
		return map;
	}

	@GET
	@Path("/run")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchSingleRun(@Context UriInfo uriInfo) {

		String query = uriInfo.getRequestUri().getQuery();
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		Map<String, String> map = convertMultiToRegularMap(queryParams);
		DatabaseOps ops = new DatabaseOps(new TestRunDAO());

		List<Object> list = ops.fetchDataBasedOnCriteria(new TestRunDAO(), map);
		JSONObject responseDetailsJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (Object obj : list) {
			TestRunDAO temp = (TestRunDAO) obj;
			JSONObject json = DataUtils.convertObjectToJson(temp);
			jsonArray.put(json);
		}
		responseDetailsJson.put("testRun", jsonArray);
		return responseDetailsJson.toString();
	}

	@GET
	@Path("/runs")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchRunData() {

		DatabaseOps ops = new DatabaseOps(new TestRunDAO());
		List<Object> list = ops.fetchAllData(new TestRunDAO());
		JSONObject responseDetailsJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (Object obj : list) {
			TestRunDAO temp = (TestRunDAO) obj;
			JSONObject json = DataUtils.convertObjectToJson(temp);
			jsonArray.put(json);
		}
		responseDetailsJson.put("Runs", jsonArray);
		return responseDetailsJson.toString();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response postData(ModuleDAO param1) {
		String result = "Track saved : " + param1.getModuleName();
		return Response.status(201).entity(result).build();
	}

}
