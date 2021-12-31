package org.rso.naloga.zapiski.api.v1.resouces;

import user.lib.File;
import user.services.beans.FileBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/files")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FileResource {

    private Logger log = Logger.getLogger(FileResource.class.getName());

    @Inject
    private FileBean fileBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getFiles(){
        List<File> files = fileBean.getAllFiles();

        return Response.status(Response.Status.OK).entity(files).build();
    }

    @GET
    @Path("{fileId}")
    public Response getFileById(@PathParam("fileId") int fileId){

        File file = fileBean.getFile(fileId);

        if (file == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.OK).entity(file).build();
    }

    @POST
    public Response createFile(File file){

        if (file.getName() == null || file.getPath() == null ||
                file.getType() == null || file.getContent() == null) {

            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        file = fileBean.createFile(file);

        return Response.status(Response.Status.OK).entity(file).build();
    }

    @PUT
    @Path("{fileId}")
    public Response putFile(@PathParam("fileId") int fileId, File file){
        if (file.getName() == null || file.getPath() == null ||
                file.getType() == null || file.getContent() == null) {

            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        file = fileBean.updateFile(fileId, file);

        if (file == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(file).build();
    }

    @DELETE
    @Path("{fileId}")
    public Response deleteLiterature(@PathParam("fileId") int fileId) {

        boolean deleted = fileBean.deleteFile(fileId);

        if (deleted) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
