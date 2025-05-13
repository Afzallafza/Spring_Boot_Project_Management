package org.babor.boot_project_management.repository;

import org.babor.boot_project_management.dto.issue.IssueResponseDto;
import org.babor.boot_project_management.entity.Issue;
import org.babor.boot_project_management.enums.IssuePriority;
import org.babor.boot_project_management.enums.IssueStatus;
import org.babor.boot_project_management.enums.IssueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {
    @Query("""
            select i from Issue i where i.id=:id
            """)
    Issue findByIdCustom(@Param("id") int id);

    @Query("""
            select new org.babor.boot_project_management.dto.issue.IssueResponseDto(
                   i.id,i.name,i.type,i.priority,i.status,i.assignee.id,i.sprint.id,i.project.id
                   ) from Issue i where i.sprint.id in :sprints
                and i.type in :types
                        and i.status in :statuses
                        and i.priority in :priorities""")
    List<IssueResponseDto> filter(@Param("types") Set<IssueType> types,
                                  @Param("priorities") Set<IssuePriority> priorities,
                                  @Param("statuses") Set<IssueStatus> statuses, @Param("sprints") Set<Integer> sprints);

    @Query("""
            select new org.babor.boot_project_management.dto.issue.IssueResponseDto(
                   i.id,i.name,i.type,i.priority,i.status,i.assignee.id,i.sprint.id,i.project.id
                   ) from Issue i where i.sprint.id = :sprintId
            """)
    List<IssueResponseDto> findAllBySprintId(@Param("sprintId") int sprintId);

}
