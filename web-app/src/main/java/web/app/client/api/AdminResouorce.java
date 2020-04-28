package web.app.client.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import web.app.client.api.dto.AdminDto;

@Path("/admin")
@Api(value="/admin")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public interface AdminResouorce {

	@GET
	@Path("/all")
	@ApiOperation(nickname="findAll", value="Get all admin", response=AdminDto.class, responseContainer="List")
	List<AdminDto> findAll();
	
}
