package com.example.demo.Controller;

import com.example.demo.DTO.CarreraDTO;
import com.example.demo.Model.Carrera;
import com.example.demo.Servicios.ServicioCarreraImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

	@Qualifier("servicioCarreraImpl")
	@Autowired
	private ServicioCarreraImpl sc;

	public CarreraController(@Qualifier("servicioCarreraImpl") ServicioCarreraImpl sc) {
		this.sc = sc;
	}

	@PostMapping(value="/insertar" )
	public void insertarCarrera(@RequestBody Carrera c) {this.sc.insertarCarrera(c);}
//
//	@RequestMapping(value="/actualizar" )
//	public boolean actualizarCarrera(@RequestBody Carrera c) {
//		this.sc.actualizarCarrera(c);
//		return true;
//	};
//
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminarCarrera(Long id){
		this.sc.eliminarCarrera(id);
	}
	
	@GetMapping(value="/")
	public List<Carrera> listarCarreras(){
		return this.sc.listarCarreras();
	}
//
	@GetMapping(value="/{id}")
	public Optional<Carrera> getCarrera(Long id) {
		return this.sc.getCarrera(id);
	}

	@GetMapping(value="/conEstudiantes")
	public List<Carrera> getCarrerasConEstudiantes(){
		return this.sc.getCarrerasConEstudiantes();
	}

	@GetMapping(value="/reporte")
	public List<CarreraDTO> getReporteCarreras(){
		return this.sc.getReporteCarreras();
	}


}
