package fox.dev.employee.controller;

import fox.dev.employee.dto.EmployeeDto;
import fox.dev.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departements")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("{departmentId}/employees")
    public ResponseEntity<EmployeeDto> addEmployee(@PathVariable Long departmentId,
                                                   @RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployeeDto = employeeService.addEmployee(departmentId, employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }
}
