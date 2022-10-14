package com.example.demo.Controller;

import com.example.demo.Model.Matriculacion;
import com.example.demo.Servicios.ServicioMatriculacionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculaciones")
public class MatriculacionController {

	@Qualifier("servicioMatriculacionImpl")
	@Autowired
	private ServicioMatriculacionImpl sm;

	public MatriculacionController(@Qualifier("servicioMatriculacionImpl") ServicioMatriculacionImpl sm) {
		this.sm = sm;
	}

	@PostMapping(value="/insertar")
	public void insertarMatriculacion(@RequestBody Matriculacion m) {this.sm.insertarMatriculacion(m);}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminarMatriculacion(@PathVariable Long id) {
		this.sm.eliminarMatriculacion(id);
	}
	
//	@PutMapping(value="/actualizar" )
//	public boolean actualizarMatriculacion(@RequestBody Matriculacion m) {
//		return this.sm.actualizarMatriculacion(m);
//	}


}
