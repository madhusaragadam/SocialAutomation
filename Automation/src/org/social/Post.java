package org.social;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.social.dao.ModuleDAO;
import org.social.dao.TestRunDAO;
import org.social.database.DatabaseOps;

@Path("/post")
public class Post {

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response postModule(ModuleDAO param1) {
		DatabaseOps ops = new DatabaseOps(new ModuleDAO());
		ops.insertData(param1);
		String result = "Track saved : " + param1.getModuleName();
		return Response.status(201).entity(result).build();
	}
	
	
	@Path("/run")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Response postRunData(TestRunDAO param1) {
		DatabaseOps ops = new DatabaseOps(new TestRunDAO());
		Long id = (long) param1.getModuleId();
		ModuleDAO dao = (ModuleDAO) ops.fetchData(ModuleDAO.class, id);
		if(dao == null) {
			return Response.status(500).entity("Module Id provided is not in the database").build();
		}
		param1.setModule(dao);
		ops.insertData(param1);
		String result = "Track saved : " + param1.getPass();
		return Response.status(201).entity(result).build();
	}	
}
