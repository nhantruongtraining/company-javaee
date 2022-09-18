package dao.impl;

import dao.DepartmentDAO;
import entity.Department;
import rest.client.model.DepartmentRequest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DepartmentDAOImpl implements DepartmentDAO {
    @PersistenceContext(name = "Company")
    EntityManager em;

    @Override
    public List<Department> findAll() {
        Query allDepartmentQuery = em.createQuery("SELECT d FROM Department d", Department.class);
        return allDepartmentQuery.getResultList();
    }

    @Override
    public Department save(DepartmentRequest departmentRequest) {

        Department department = new Department();
        department.setDepartmentName(departmentRequest.getDepartmentName());
        department.setStartDate(departmentRequest.getStartDate());
        department = this.em.merge(department);
        return department;

    }

    @Override
    public Department update(Integer id, DepartmentRequest departmentRequest) {
        Department updateDepartment = findById(id);
        updateDepartment.setDepartmentName(departmentRequest.getDepartmentName());
        updateDepartment.setStartDate(departmentRequest.getStartDate());
        updateDepartment = this.em.merge(updateDepartment);
        return updateDepartment;
    }


    @Override
    public Department findById(Integer deptId) {
        return em.createQuery(
                        "SELECT d FROM Department d " +
                                "WHERE d.departmentId = :deptid", Department.class)
                .setParameter("deptid", deptId).getSingleResult();

    }


    @Override
    public void remove(Integer id) {
        Department department = findById(id);
        if (null != department) {
            this.em.remove(department);
        }
    }
}
