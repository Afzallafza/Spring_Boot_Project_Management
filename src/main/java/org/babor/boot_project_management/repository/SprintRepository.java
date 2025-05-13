package org.babor.boot_project_management.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.babor.boot_project_management.dto.sprint.SprintResponseDto;
import org.babor.boot_project_management.entity.Project;
import org.babor.boot_project_management.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    @Query("""
            select s from Sprint s where s.id=:id
            """)
    Sprint findByIdCustom(@Param("id") int id);

    @Query("""
            select new org.babor.boot_project_management.dto.sprint.SprintResponseDto(
                   s.id,s.name,s.goal,s.duration,s.isActive
                   ) from Sprint s where s.project=:project
            """
    )
    List<SprintResponseDto> findAllByProject(@Param("project") Project project);
}
