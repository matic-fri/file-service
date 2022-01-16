package org.rso.naloga.zapiski.graphql;

import user.lib.File;

public class FileResponse {

    public File file;
    public boolean status;

    public FileResponse(File f, boolean s){
        this.file = f;
        this.status = s;
    }
}
