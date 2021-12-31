package user.services.beans;


import user.lib.File;
import user.models.converters.FileConverter;
import user.models.entities.FileEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class FileBean {

    private Logger log = Logger.getLogger(FileBean.class.getName());

    @Inject
    private EntityManager em;

    public List<File> getAllFiles() {
        TypedQuery<FileEntity> query = em.createNamedQuery(
                "FileEntity.getAll", FileEntity.class);

        List<FileEntity> resultList = query.getResultList();

        return resultList.stream().map(FileConverter::toDto).collect(Collectors.toList());

    }

    public File getFile(Integer id){
        FileEntity FileEn = em.find(FileEntity.class, id);

        if (FileEn == null){
            throw new NotFoundException();
        }

        File u = FileConverter.toDto(FileEn);

        return u;

    }

    public File createFile(File user){

        FileEntity FileEn = FileConverter.toEntity(user);

        try{
            beginTx();
            em.persist(FileEn);
            commitTx();
        }
        catch (Exception e){
            rollbackTx();
        }

        if (FileEn.getId() == null){
            throw new RuntimeException("Entity file was not persisted");
        }

        return FileConverter.toDto(FileEn);

    }

    public File updateFile(int id, File file) {

        FileEntity FileEn_old = em.find(FileEntity.class, id);

        if (FileEn_old == null) {
            return null;
        }

        FileEntity FileEn_new = FileConverter.toEntity(file);

        try{
            beginTx();
            FileEn_new.setId(FileEn_old.getId());
            FileEn_new = em.merge(FileEn_new);
            commitTx();
        }
        catch (Exception e){
            rollbackTx();
        }

        return FileConverter.toDto(FileEn_new);
    }

    public boolean deleteFile(int id) {

        FileEntity fileEntity = em.find(FileEntity.class, id);

        if (fileEntity != null) {
            try {
                beginTx();
                em.remove(fileEntity);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }




    private void beginTx(){
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
    }

    private void commitTx(){
        if (em.getTransaction().isActive()){
            em.getTransaction().commit();
        }
    }

    private void rollbackTx(){
        if(em.getTransaction().isActive()){
            em.getTransaction().rollback();
        }
    }

}
