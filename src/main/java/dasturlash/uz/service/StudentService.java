package dasturlash.uz.service;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.dto.StudentShortInfoDTO;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.mapper.StudentInfoMapper;
import dasturlash.uz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());

        studentRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public List<StudentDTO> getAllStudent() {
        Iterable<StudentEntity> entityList = studentRepository.findAll();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        StudentEntity entity = optional.get();

        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());

        return dto;
    }

    public Boolean update(StudentDTO dto, Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        StudentEntity entity = optional.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        studentRepository.save(entity);
        return true;
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    public PageImpl<StudentDTO> pagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<StudentEntity> result = studentRepository.findAll(pageRequest);

        long totalCount = result.getTotalElements();
        List<StudentEntity> entityList = result.getContent();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dtoList.add(dto);
        }
        return new PageImpl<StudentDTO>(dtoList, pageRequest, totalCount);
    }

    public List<StudentDTO> findAll() {
        List<StudentEntity> entityList = studentRepository.findAll();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<StudentDTO> getAllUsingSelect() {
        List<StudentEntity> entityList = studentRepository.getStudentListUsingSelect();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<StudentDTO> studentIdAndNameList() {
        List<Object[]> entityList = studentRepository.getStudentIdAndNameList();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (Object[] obj : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId((Integer) obj[0]);
            dto.setName((String) obj[1]);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<StudentDTO> studentPartialInfoList() {
        List<StudentEntity> entityList = studentRepository.getStudentPartialInfoUsingConstructor();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<StudentShortInfoDTO> studentListUsingNonEntityClass() {
        List<StudentShortInfoDTO> dtoList = studentRepository.getStudentUsingNotEntityConstructor();
        return dtoList;
    }

    public List<StudentDTO> getStudentShortInfo() {
        List<StudentInfoMapper> entityList = studentRepository.getStudentInfoList();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentInfoMapper entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        }
        return dtoList;
    }

}
