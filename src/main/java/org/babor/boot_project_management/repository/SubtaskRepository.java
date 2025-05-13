package org.babor.boot_project_management.repository;


import org.babor.boot_project_management.dto.subtask.SubtaskResponseDto;
import org.babor.boot_project_management.entity.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Integer> {
    @Query("""
            select s from Subtask s where s.id=:id
            """)
    Subtask findByIdCustom(@Param("id") Integer id);

    @Query("""
             select new org.babor.boot_project_management.dto.subtask.SubtaskResponseDto(
                    s.id,s.name,s.description,s.priority,s.status,s.assignee.id,
                    s.reporter.id) from Subtask s where s.issue.id=:issueId
            """)
    List<SubtaskResponseDto> findAllByIssueId(@Param("issueId") Integer issueId);
}
