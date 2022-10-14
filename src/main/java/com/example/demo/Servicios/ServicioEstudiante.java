package com.example.demo.Servicios;

import com.example.demo.Model.Estudiante;

import java.util.List;

public interface ServicioEstudiante {

	public void insertarEstudiante(Estudiante e);
	public List<Estudiante> obtenerAllEstudiantes();
	public void eliminarEstudiante(Long id);
//	public boolean actualizarEstudiante(Estudiante e);
	public Estudiante getEstudiantePorNroLibreta(int lu);
	public List<Estudiante> getEstudiantesPorGenero(char genero);
	public List<Estudiante> getEstudiantesOrdenadoPorApellidoYNombre();
	public List<Estudiante> getEstudiantesPorCiudad(String nombreCarrera, String ciudad);
}
