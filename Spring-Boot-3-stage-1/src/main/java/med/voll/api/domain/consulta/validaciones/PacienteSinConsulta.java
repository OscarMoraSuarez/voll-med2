package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;
    public void validar(DatosAgendarConsulta datos){

        var primerHorario= datos.fecha().withHour(7);// modificamos la fecha a las siete
        var ultimoHorario=datos.fecha().withHour(18);
        //validar si existe un IS en la DB con una fecha entre ese intervalo  que son las 7 y las 6 de la tarde
        // si existe una agenda dentro del repo de consultas se tiejne que enviar un msn de error
        var pacienteConConsulta=repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(),primerHorario,ultimoHorario);

        if(pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para ese día");
        }

    }
}
