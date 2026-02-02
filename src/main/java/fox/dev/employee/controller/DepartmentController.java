package fox.dev.employee.controller;

import fox.dev.employee.dto.DepartmentDto;
import fox.dev.employee.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departements")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long id){
        DepartmentDto departmentDto = departmentService.getDepartment(id);
        return ResponseEntity.ok(departmentDto);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto,
                                                          @PathVariable Long id){
        return ResponseEntity.ok(departmentService.updateDepartment(departmentDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartement(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department delete successfully");
    }
}
