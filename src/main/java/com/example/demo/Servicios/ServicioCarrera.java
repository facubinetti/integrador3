package com.example.demo.Servicios;

import com.example.demo.DTO.CarreraDTO;
import com.example.demo.Model.Carrera;

import java.util.List;
import java.util.Optional;

public interface ServicioCarrera {

	public void insertarCarrera(Carrera c);
	public List<Carrera> listarCarreras();
	public void eliminarCarrera(Long id);
//	public Carrera actualizarCarrera(Carrera c);
	public Optional<Carrera> getCarrera(Long id);
	public List<Carrera> getCarrerasConEstudiantes();
	public List<CarreraDTO> getReporteCarreras();
}
