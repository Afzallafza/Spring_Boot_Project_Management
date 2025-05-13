package org.babor.boot_project_management.validatior;

import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.exception.ProjectNotFoundException;
import org.babor.boot_project_management.service.ProjectService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectValidator {
    private final ProjectService projectService;

    public void isPresent(int projectId) {
        if (projectService.findByIdCustom(projectId) == null) {
            throw new ProjectNotFoundException("Project with id " + projectId + " not found");
        }
    }
}
