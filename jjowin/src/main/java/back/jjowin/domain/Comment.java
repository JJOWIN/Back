package back.jjowin.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prent_comment_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> childs;
    //==연관관계 메서드==//
    public void addChildComment(Comment child){
        this.childs.add(child);
        child.setParent(this);
    }
}
