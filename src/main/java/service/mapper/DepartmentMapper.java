package service.mapper;

import entity.Department;
import org.mapstruct.Mapper;
import service.dto.DepartmentDto;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);

    List<DepartmentDto> toDtos(List<Department> departments);
}
