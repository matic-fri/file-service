package user.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "file")
@NamedQueries(value =
        {
                @NamedQuery(name = "FileEntity.getAll",
                        query = "SELECT im FROM FileEntity im")
        })
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(
//            name = "file_user_fk",
//            foreignKey = @ForeignKey(name = "file_user_fk", value = ConstraintMode.NO_CONSTRAINT)
//    )
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "type")
    private String type;

    @Column(name = "content")
    private Byte[] content;

    // getters
    public Long getId() {
        return this.id;
    }
    public Long getUserId() { return this.userId; }
    public String getName() { return this.name; }
    public String getPath() {
        return this.path;
    }
    public String getType() {
        return this.type;
    }
    public Byte[] getContent() {
        return this.content;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setName(String fileName) {
        this.name = fileName;
    }
    public void setPath(String filePath) {
        this.path = filePath;
    }
    public void setType(String fileType) {
        this.type = fileType;
    }
    public void setContent(Byte[] fileContent) {
        this.content = fileContent;
    }
}
