package org.babor.boot_project_management.validatior;


import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.exception.SubtaskNotFoundException;
import org.babor.boot_project_management.repository.SubtaskRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubtaskValidator {
    private final SubtaskRepository subtaskRepository;

    public void isPresent(int subtaskId) {
        if (subtaskRepository.findByIdCustom(subtaskId) == null) {
            throw new SubtaskNotFoundException("Subtask with id " + subtaskId + " not found");
        }
    }
}
