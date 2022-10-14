package com.example.demo.Repository;

import com.example.demo.Model.Carrera;
import com.example.demo.Model.Estudiante;
import com.example.demo.Model.Matriculacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculacionRepository extends JpaRepository<Matriculacion,Long> {

//	boolean actualizarMatriculacion(Matriculacion m);
}
