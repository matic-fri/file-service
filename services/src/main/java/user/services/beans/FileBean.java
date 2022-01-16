package user.services.beans;


import user.lib.File;
import user.models.converters.FileConverter;
import user.models.entities.FileEntity;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
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

    @Timeout(value = 3, unit = ChronoUnit.SECONDS)
    @CircuitBreaker(requestVolumeThreshold = 2)
    @Fallback(fallbackMethod = "getFileFallback")
    public File getFile(long id){

        if(id==2022){
            try{
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e){
                System.out.println("Time error:\n" + e);
            }
        }

        FileEntity FileEn = em.find(FileEntity.class, id);

        if (FileEn == null){
            throw new NotFoundException();
        }

        File u = FileConverter.toDto(FileEn);

        return u;

    }

    public File getFileFallback(long id){
        File u = new File();
        u.setId((long)-1);
        return u;
    }

    @Fallback(fallbackMethod = "createFileFallback")
    public File createFile(File file){

        FileEntity FileEn = FileConverter.toEntity(file);

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

    public File createFileFallback(File file){
        File f = new File();
        f.setId((long)-1);
        return f;
    }

    public File updateFile(long id, File file) {

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

    public boolean deleteFile(long id) {

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
