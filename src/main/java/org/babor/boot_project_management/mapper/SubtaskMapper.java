package org.babor.boot_project_management.mapper;

import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.subtask.SubtaskResponseDto;
import org.babor.boot_project_management.dto.subtask.SubtaskUpdateRequestDto;
import org.babor.boot_project_management.entity.Subtask;
import org.babor.boot_project_management.entity.User;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueStatus;
import org.babor.boot_project_management.exception.SubtaskNotFoundException;
import org.babor.boot_project_management.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubtaskMapper {
    private final UserRepository userRepository;
    public SubtaskResponseDto toDto(Subtask subtask) {
        return new SubtaskResponseDto(
                subtask.getId(),
                subtask.getName(),
                subtask.getDescription(),
                subtask.getPriority(),
                subtask.getStatus(),
                subtask.getAssignee().getId(),
                subtask.getReporter().getId()
        );
    }

    public void mapUpdates(Subtask subtask, SubtaskUpdateRequestDto subtaskRequestDto) {
        if (subtaskRequestDto.getName() != null) {
            subtask.setName(subtaskRequestDto.getName());
        }
        if (subtaskRequestDto.getDescription() != null) {
            subtask.setDescription(subtaskRequestDto.getDescription());
        }
        if (subtaskRequestDto.getPriority() != null) {
            subtask.setPriority(IssuePriority.valueOf(subtaskRequestDto.getPriority()));
        }
        if (subtaskRequestDto.getStatus() != null) {
            subtask.setStatus(IssueStatus.valueOf(subtaskRequestDto.getStatus()));
        }
        if (subtaskRequestDto.getAssigneeId() != null) {
            User user = userRepository.findByIdCustom(subtaskRequestDto.getAssigneeId());
            subtask.setAssignee(user);
        }
    }
}
