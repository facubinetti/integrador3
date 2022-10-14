package com.example.demo.Servicios;

import com.example.demo.Model.Carrera;
import com.example.demo.Model.Estudiante;
import com.example.demo.Model.Matriculacion;
import com.example.demo.Repository.MatriculacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioMatriculacionImpl implements ServicioMatriculacion{

	@Autowired
	private MatriculacionRepository mr;

	public ServicioMatriculacionImpl(MatriculacionRepository mr) {
		this.mr = mr;
	}
	@Override
	public void insertarMatriculacion(Matriculacion m) {
		 mr.save(m);
	}
	@Override
	public void eliminarMatriculacion(Long id) {
		mr.deleteById(id);
	}

//	public boolean actualizarMatriculacion(Matriculacion m) {
//		return mr.actualizarMatriculacion(m);
//	}

	public Matriculacion crearMatriculacion(Estudiante e, Carrera c, int anioEgreso , int anioIngreso){
		Matriculacion mat = new Matriculacion(e,c,anioEgreso,anioIngreso);
		e.agregarMatriculacion(mat);
		c.agregarMatriculacion(mat);
		return mat;
	}

}
