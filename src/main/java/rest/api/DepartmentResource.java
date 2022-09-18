package rest.api;

import rest.client.model.DepartmentRequest;
import service.DepartmentService;
import service.dto.DepartmentDto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Stateless
@Path(DepartmentResource.PATH)
public class DepartmentResource {
    public static final String PATH = "departments";
    @Inject
    DepartmentService departmentService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok(departmentService.getAll()).build();
    }

    @GET
    @Path("{DepartmentId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("DepartmentId") Integer departmentId) {
        return Response.ok(departmentService.findById(departmentId)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addDepartment(DepartmentRequest departmentRequest) {
        DepartmentDto createdDept = departmentService.save(departmentRequest);
        System.out.println(Response.created(appendCurrentUriWith(createdDept.getDepartmentId().toString())).build());
        return Response.ok().entity(createdDept).status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{DepartmentId}")
    public Response delete(@PathParam("DepartmentId") Integer departmentId) {
        departmentService.delete(departmentId);
        return Response.ok().build();
    }

    @PUT
    @Path("{DepartmentId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})

    public Response updateDepartment(@PathParam("DepartmentId") Integer departmentId, DepartmentRequest departmentRequest) {
        DepartmentDto updatedDepartment = departmentService.update(departmentId, departmentRequest);
        return Response.ok().entity(updatedDepartment).build();
    }

    private URI appendCurrentUriWith(String fragment) {
        return uriInfo.getAbsolutePathBuilder().path(fragment).build();
    }
}
