package org.rso.naloga.zapiski.graphql;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import user.lib.File;
import user.services.beans.FileBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@GraphQLClass
@ApplicationScoped
public class FileMutations {

    @Inject
    private FileBean fileBean;

    @GraphQLMutation
    public FileResponse createFile(@GraphQLArgument(name = "file") File file){

        if (file.getName() == null || file.getPath() == null || file.getOwnerId() == null ||
                file.getType() == null || file.getContent() == null) {

            return new FileResponse(file, false);
        }

        file = fileBean.createFile(file);

        return new FileResponse(file, true);

    }

    @GraphQLMutation
    public FileResponse changeFile(@GraphQLArgument(name = "fileId") long fileId,
                                   @GraphQLArgument(name = "file") File file){

        if (file.getName() == null || file.getPath() == null || file.getOwnerId() == null ||
                file.getType() == null || file.getContent() == null) {

            return new FileResponse(file, false);
        }

        File nF = fileBean.updateFile(fileId, file);

        if (nF == null) {
            return new FileResponse(file, false);
        }

        return new FileResponse(file, true);
    }

    @GraphQLMutation
    public DeleteResponse deleteFile(@GraphQLArgument(name = "fileId") long fileId){
        return new DeleteResponse(fileBean.deleteFile(fileId));
    }
}
