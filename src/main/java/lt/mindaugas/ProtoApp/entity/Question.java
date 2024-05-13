package lt.mindaugas.ProtoApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "question")
    private String question;
    @Column(name = "responsible_person")
    private String responsiblePerson;
    @Column(name = "create_date")
    private LocalDate createDate;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "status")
    private Byte status;
    @Column(name = "comment")
    private String comment;

    @Column(name = "question_number")
    private Integer questionNumber;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDate.now();
    }
}
