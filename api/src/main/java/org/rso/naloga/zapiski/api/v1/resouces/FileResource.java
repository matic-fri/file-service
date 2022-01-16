package org.rso.naloga.zapiski.api.v1.resouces;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, OPTIONS")
public class FileResource {

    private Logger log = Logger.getLogger(FileResource.class.getName());

    @Inject
    private FileBean fileBean;

    @Context
    protected UriInfo uriInfo;


    @Operation(description = "Get all files.", summary = "Get files.")
    @APIResponses({
        @APIResponse(responseCode = "200",
                description = "All files",
                content = @Content(schema = @Schema(implementation = File.class, type = SchemaType.ARRAY)),
                headers = {@Header(name = "X-Total-Count", description = "Files")}
        )})
    @GET
    public Response getFiles(){
        List<File> files = fileBean.getAllFiles();

        return Response.status(Response.Status.OK).entity(files).build();
    }


    @Operation(description = "Get specified file.", summary = "Get file.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Specified file",
                    content = @Content(schema = @Schema(implementation = File.class, type = SchemaType.OBJECT)),
                    headers = {@Header(name = "X-Total-Count", description = "File")}
            )})
    @GET
    @Path("{fileId}")
    public Response getFileById(@PathParam("fileId") long fileId){

        File file = fileBean.getFile(fileId);

        if (file == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.status(Response.Status.OK).entity(file).build();
    }


    @Operation(description = "Create new file", summary = "Create file.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "New file",
                    content = @Content(schema = @Schema(implementation = File.class, type = SchemaType.OBJECT)),
                    headers = {@Header(name = "X-Total-Count", description = "File")}
            )})
    @POST
    public Response createFile(File file){

        if (file.getName() == null || file.getPath() == null || file.getOwnerId() == null ||
                file.getType() == null || file.getContent() == null) {

            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        file = fileBean.createFile(file);

        return Response.status(Response.Status.OK).entity(file).build();
    }


    @Operation(description = "Update specified file.", summary = "Update file.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Updated file",
                    content = @Content(schema = @Schema(implementation = File.class, type = SchemaType.OBJECT)),
                    headers = {@Header(name = "X-Total-Count", description = "File")}
            )})
    @PUT
    @Path("{fileId}")
    public Response putFile(@PathParam("fileId") long fileId, File file){
        if (file.getName() == null || file.getPath() == null || file.getOwnerId() == null ||
                file.getType() == null || file.getContent() == null) {

            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        file = fileBean.updateFile(fileId, file);

        if (file == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(file).build();
    }


    @Operation(description = "Delete specified file.", summary = "Delete file.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Status",
                    content = @Content(schema = @Schema(implementation = File.class, type = SchemaType.BOOLEAN)),
                    headers = {@Header(name = "X-Total-Count", description = "Status")}
            )})
    @DELETE
    @Path("{fileId}")
    public Response deleteLiterature(@PathParam("fileId") long fileId) {

        boolean deleted = fileBean.deleteFile(fileId);

        if (deleted) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
