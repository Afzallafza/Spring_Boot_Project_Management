package org.babor.boot_project_management.repository;

import org.babor.boot_project_management.dto.project.ProjectResponseDto;
import org.babor.boot_project_management.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("""
                 select p from Project p where p.id=:projectId
            """)
    Project findByIdCustom(@Param("projectId") int projectId);

    @Query("""
            select new org.babor.boot_project_management.dto.project.ProjectResponseDto(
            p.id,p.name
            ) from Project p
            """)
    List<ProjectResponseDto> findAllCustom();
}
