package service;

import dao.DepartmentDAO;
import rest.client.model.DepartmentRequest;
import service.dto.DepartmentDto;
import service.mapper.DepartmentMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DepartmentService {
    @Inject
    private DepartmentDAO departmentDAO;

    @Inject
    private DepartmentMapper departmentMapper;

    public List<DepartmentDto> getAll() {
        return departmentMapper.toDtos(departmentDAO.findAll());
    }

    public DepartmentDto findById(Integer id) {
        return departmentMapper.toDto(departmentDAO.findById(id));
    }

    public DepartmentDto save(DepartmentRequest departmentRequest) {
        return departmentMapper.toDto(departmentDAO.save(departmentRequest));
    }

    public void delete(Integer id) {
        departmentDAO.remove(id);
    }

    public DepartmentDto update(Integer id, DepartmentRequest request) {
        return departmentMapper.toDto(departmentDAO.update(id, request));
    }
}
