package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas{

    public void validar(DatosAgendarConsulta datos){

        var ahora= LocalDateTime.now();
        var horaDeConcsulta=datos.fecha();

        var diferenciade30Min= Duration.between(ahora,horaDeConcsulta).toMinutes()<30;
        // si es menor a treinta
        if(diferenciade30Min){
            throw  new ValidationException("las consultas deben tener al menos treinta minutos de anticipación");
        }
    }


}
