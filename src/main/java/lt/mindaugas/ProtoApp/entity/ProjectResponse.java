package lt.mindaugas.ProtoApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ProjectResponse {
    private int page;
    private int perPage;
    private int totalPage;
    private List<Project> data;
}
