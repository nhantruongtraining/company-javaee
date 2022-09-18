package service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {
    private Integer departmentId;
    private String departmentName;
    private LocalDate startDate;
}
