package org.babor.boot_project_management.repository;

import org.babor.boot_project_management.dto.user.UserResponseDto;
import org.babor.boot_project_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("""
            select u from User u where u.username=:username
            """
    )
    User findByUsernameCustom(@Param("username") String username);

    @Query("""
            select u from User u where u.id=:id
            """)
    User findByIdCustom(@Param("id") int id);

    @Query("""
            select new org.babor.boot_project_management.dto.user.UserResponseDto(
                   up.member.id,up.member.name,up.member.email,up.member.username,up.role
                   ) from UserProject up where up.project.id = :projectId
            """)
    List<UserResponseDto> findAllByProjectId(@Param("projectId") int projectId);
}
