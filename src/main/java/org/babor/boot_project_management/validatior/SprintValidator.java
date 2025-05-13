package org.babor.boot_project_management.validatior;

import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.exception.SprintNotFoundException;
import org.babor.boot_project_management.service.SprintService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SprintValidator {
    private final SprintService sprintService;

    public void isPresent(int sprintId) {
        if (sprintService.findByIdCustom(sprintId) == null) {
            throw new SprintNotFoundException("No Sprint with id " + sprintId + " exists!!!");
        }
    }
}
