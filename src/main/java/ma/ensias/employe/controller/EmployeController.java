package ma.ensias.employe.controller;

import ma.ensias.employe.reponse.EmployeReponse;
import ma.ensias.employe.service.EmployeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeController {
    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping("/employes/{id}")
    public EmployeReponse getEmployeDetails(@PathVariable("id") int id) {
        return employeService.getEmployeById(id);
    }
}
