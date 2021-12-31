package user.models.converters;


import user.lib.File;
import user.models.entities.FileEntity;

public class FileConverter {

    public static File toDto(FileEntity entity) {
        File dto = new File();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPath(entity.getPath());
        dto.setType(entity.getType());
        dto.setContent(entity.getContent());

        return dto;

    }

    public static FileEntity toEntity(File dto){
        FileEntity entity = new FileEntity();
        entity.setName(dto.getName());
        entity.setPath(dto.getPath());
        entity.setType(dto.getType());
        entity.setContent(dto.getContent());

        return entity;

    }
}
