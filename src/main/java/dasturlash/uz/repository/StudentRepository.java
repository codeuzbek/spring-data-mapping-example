package dasturlash.uz.repository;

import dasturlash.uz.dto.StudentShortInfoDTO;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.mapper.StudentInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>,
        PagingAndSortingRepository<StudentEntity, Integer> {

    Page<StudentEntity> findByName(String name, Pageable pageable);

    Page<StudentEntity> findByNameAndSurname(String name, String surname, Pageable pageable);

    @Query("Select s FROM  StudentEntity s")
    List<StudentEntity> findAll();

    @Query("Select s.id, s.name, s.surname, s.age FROM  StudentEntity s")
    List<StudentEntity> getStudentListUsingSelect();

    @Query("Select s.id, s.name FROM  StudentEntity s")
    List<Object[]> getStudentIdAndNameList();

    @Query("Select new StudentEntity (s.id, s.name) FROM  StudentEntity s")
    List<StudentEntity> getStudentPartialInfoUsingConstructor();

    @Query("Select new dasturlash.uz.dto.StudentShortInfoDTO(s.id, s.name, s.surname) FROM  StudentEntity s")
    List<StudentShortInfoDTO> getStudentUsingNotEntityConstructor();

    @Query("Select s.id, s.name, s.surname FROM  StudentEntity s")
    List<StudentInfoMapper> getStudentInfoList();


}
