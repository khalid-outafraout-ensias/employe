package ma.ensias.employe.service;

import ma.ensias.employe.entite.Employe;
import ma.ensias.employe.reponse.EmployeReponse;
import ma.ensias.employe.repository.EmployeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeService {
    private final EmployeRepo employeRepo;
    private final ModelMapper mapper;

    public EmployeService(EmployeRepo employeRepo, ModelMapper mapper) {
        this.employeRepo = employeRepo;
        this.mapper = mapper;
    }

    public EmployeReponse getEmployeById(int id) {
        Employe employe = employeRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employe introuvable"));
        return mapper.map(employe, EmployeReponse.class);
    }
}
