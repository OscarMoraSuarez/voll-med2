package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas{

    public void validar(DatosAgendarConsulta datos){
        // verificar que no sea domingo y que nos e encuentre fuera del horario de atencion
        //verificar que no sea antes de las 7:00 ni despues de las 19:00
        var domingo= DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeApertura=datos.fecha().getHour()<7;
        var despuesDeCierre=datos.fecha().getHour()>19;
        if(domingo || antesDeApertura || despuesDeCierre){
            throw new ValidationException("El horario de atencion es de lunes a Sabado de 7:00 a 19:00");
        }



    };

}
