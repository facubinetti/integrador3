package com.example.demo.Utils;


import com.example.demo.Model.Carrera;
import com.example.demo.Model.Estudiante;
import com.example.demo.Model.Matriculacion;
import com.example.demo.Repository.CarreraRepository;
import com.example.demo.Repository.EstudianteRepository;
import com.example.demo.Repository.MatriculacionRepository;
import com.example.demo.Servicios.ServicioCarreraImpl;
import com.example.demo.Servicios.ServicioEstudianteImpl;
import com.example.demo.Servicios.ServicioMatriculacionImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

@Configuration
@Slf4j
public class CargarDB {

    @Bean
    CommandLineRunner initDatabase(@Qualifier("carreraRepository") CarreraRepository cr , @Qualifier("estudianteRepository") EstudianteRepository er , @Qualifier("matriculacionRepository") MatriculacionRepository mr,@Qualifier("servicioCarreraImpl") ServicioCarreraImpl sc,@Qualifier("servicioEstudianteImpl") ServicioEstudianteImpl se,@Qualifier("servicioMatriculacionImpl") ServicioMatriculacionImpl sm) {
        return args -> {
//            log.info("Cargue la carrera 1"+ cr.save(new Carrera("Tudai",5)));
                cargarEstudiantes(se,sm,sc);
        };
    }

    public void cargarEstudiantes(ServicioEstudianteImpl svcEstudiante, ServicioMatriculacionImpl svcMatriculacion, ServicioCarreraImpl svcCarrera) {
        List<Carrera> carreras = leerCarreras();
        try {
            @SuppressWarnings("deprecation")
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/java/com/example/demo/assets/estudiantes100.csv"));
            System.out.println("Estoy cargando los estudiantes...");
            for(CSVRecord row: parser) {
                Estudiante tmp = new Estudiante(parseInt(row.get("dni")),parseInt(row.get("nrolibreta")),row.get("nombre"),row.get("apellido"),parseInt(row.get("edad")),generarGenero(),row.get("ciudad"));
                altaEstudiante(tmp, carreras.get((int) (Math.random()*20+1)),svcEstudiante,svcMatriculacion,svcCarrera);
            }
            System.out.println("No se me da nada mal");

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Carrera> leerCarreras(){
        List<Carrera> car = new ArrayList<>();
        try {
            @SuppressWarnings("deprecation")
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/java/com/example/demo/assets/carreras20.csv"));
            System.out.println("Estoy cargando las carreras...");
            for(CSVRecord row: parser) {
                Carrera tmp = new Carrera(row.get("nombre"),parseInt(row.get("duracion")));
                car.add(tmp);
            }
            System.out.println("No se me da nada mal");
            return car;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return car;
    }

    public boolean altaEstudiante(@RequestBody Estudiante e, @RequestBody Carrera c,ServicioEstudianteImpl svcEstudiante, ServicioMatriculacionImpl svcMatriculacion , ServicioCarreraImpl svcCarrera) {
        if(e != null && c != null) {
            Random random = new Random();
            //40% chance of true
            boolean chances40true = (random.nextInt(5) < 2) ? true : false;
            int anioRandomIngreso=0;//null
            int anioRandomEgreso = 0;
            int duracionTemp= c.getDuracion();

            if(chances40true) {
                anioRandomIngreso= generateRandomInt(2010, 2022-duracionTemp);
                anioRandomEgreso= generateRandomInt(2022-duracionTemp, 2022);
            }else {
                anioRandomIngreso=generateRandomInt(2010, 2022);
            }

//			Matriculacion mat = new Matriculacion(e,c,anioRandomEgreso,anioRandomIngreso);
            Matriculacion mat = svcMatriculacion.crearMatriculacion(e,c,anioRandomEgreso,anioRandomIngreso);

//			if(svcCarrera.actualizarCarrera(c) && svcEstudiante.insertarEstudiante(e)) {
//            svcCarrera.actualizarCarrera(c);
            svcEstudiante.insertarEstudiante(e);
            svcMatriculacion.insertarMatriculacion(mat);
//				return true;
//			}
        }
        return false;
    }

    public int generateRandomInt(int min, int max){
        return (int) Math.floor((Math.random() * (max+1 -min)) +min);}

    public char generarGenero() {
        Random random = new Random();
        //50% chance of true
        boolean chances50true = (random.nextInt(4) < 2) ? true : false;
        if(chances50true) {
            return 'f';
        }
        return 'm';
    }
}
