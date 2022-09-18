package dao;

import entity.Department;
import rest.client.model.DepartmentRequest;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public interface DepartmentDAO {
    List<Department> findAll();

    Department save(DepartmentRequest departmentRequest);

    Department update(Integer id, DepartmentRequest departmentRequest);


    Department findById(Integer id);

    void remove(Integer id);

}
