package abm.abmjava.services;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExternalDateService {

    public Date getCurrentDate() {
        // Implementación local: devuelve la fecha actual del sistema
        return new Date();
    }
}
