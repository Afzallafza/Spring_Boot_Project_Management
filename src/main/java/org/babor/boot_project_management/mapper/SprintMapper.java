package org.babor.boot_project_management.mapper;


import org.babor.boot_project_management.dto.sprint.SprintUpdateRequestDto;
import org.babor.boot_project_management.entity.Sprint;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {

    public void mapUpdates(Sprint sprint, SprintUpdateRequestDto sprintUpdateRequestDto) {
        if (sprintUpdateRequestDto.getGoal() != null) {
            sprint.setGoal(sprintUpdateRequestDto.getGoal());
        }
        if (sprintUpdateRequestDto.getName() != null) {
            sprint.setName(sprintUpdateRequestDto.getName());
        }
        if (sprintUpdateRequestDto.getDuration() != null) {
            sprint.setDuration(sprintUpdateRequestDto.getDuration());
        }
    }
}
