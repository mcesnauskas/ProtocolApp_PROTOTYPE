package lt.mindaugas.ProtoApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participant")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Integer participantId;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "tasks")
    private String tasks;
    @Column(name = "representatives_name")
    private String representativesName;
    @Column(name = "initials")
    private String initials;
    @Column(name = "position")
    private String position;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    @Column(name = "project_id")
    private Integer projectId;

}
