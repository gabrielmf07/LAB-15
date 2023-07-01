package com.miempresa.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.interfaceServicio.IEmpleadoServicio;
import com.miempresa.interfaceServicio.ITareaServicio;
import com.miempresa.interfaces.IEmpleado;
import com.miempresa.interfaces.ITarea;
import com.miempresa.modelo.Empleado;
import com.miempresa.modelo.Tarea;

@Controller
@RequestMapping
public class controlador {
	
	@Autowired
	private IEmpleadoServicio servicio;
	
	@Autowired
	private ITareaServicio TServicio;
	
	@Autowired
	private IEmpleado empleado;
	
	@Autowired
	private ITarea tarea;
	
	@GetMapping("/listarEmpleados")
	public String listarEmpleados(Model model) {
		List<Empleado> empleados = servicio.listar();
		model.addAttribute("empleados", empleados);
		return "empleados";
	}
	
	@GetMapping("/listarTareas")
	public String listarTareas(Model model, @Param("filtro")String filtro) {
		List<Tarea> tareas = TServicio.listar(filtro);
		model.addAttribute("tareas", tareas);
		model.addAttribute("filtro", filtro);
		return "tareas";
	}
	
	@GetMapping("/buscarempleado")
    public String buscarEmpleado(@RequestParam("nombre") String nombre, Model model) {
        List<Empleado> empleados = empleado.findByNombre(nombre);

        if (empleados.isEmpty()) {
            model.addAttribute("noCoincidencia", true);
        } else {
            model.addAttribute("empleados", empleados);
        }

        return "buscarEmpleado";
    }
	
	@GetMapping("/buscartarea")
    public String buscarTarea(@RequestParam("descripcion") String descripcion, Model model) {
        List<Tarea> tareas = tarea.findByDescripcion(descripcion);

        if (tareas.isEmpty()) {
            model.addAttribute("noCoincidencia", true);
        } else {
            model.addAttribute("tareas", tareas);
        }

        return "buscarTarea";
    }
	
	@GetMapping("/agregarEmpleado")
	public String agregarEmpleado(Model model) {
		model.addAttribute("empleado", new Empleado());
		return "agregarEmpleado";
	}
	
	@GetMapping("/agregarTarea")
	public String agregarTarea(Model model) {
		model.addAttribute("tarea", new Tarea());
		return "agregarTarea";
	}
	
	@PostMapping("/guardarEmpleado")
	public String guardarEmpleado(Empleado p) {
		servicio.guardar(p);
		return "redirect:/listarEmpleados";
	}
	
	@PostMapping("/guardarTarea")
	public String guardarTarea(Tarea p) {
		TServicio.guardar(p);
		return "redirect:/listarTareas";
	}
	
	@GetMapping("/editarEmpleado/{id}")
	public String editarEmpleado(@PathVariable int id, RedirectAttributes atributos) {
		Optional<Empleado> empleado = servicio.listarId(id);
		atributos.addFlashAttribute("empleado", empleado);
		return "redirect:/mostrarEmpleado";
	}
	
	@GetMapping("/editarTarea/{id}")
	public String editarTarea(@PathVariable int id, RedirectAttributes atributos) {
		Optional<Tarea> tarea = TServicio.listarId(id);
		atributos.addFlashAttribute("tarea", tarea);
		return "redirect:/mostrarTarea";
	}
	
	@GetMapping("/mostrarEmpleado")
	public String mostrarEmpleado(@ModelAttribute("empleado")Empleado p, Model model) {
		model.addAttribute("empleado", p);
		return "agregarEmpleado";
	}
	
	@GetMapping("/mostrarTarea")
	public String mostrarTarea(@ModelAttribute("tarea")Tarea p, Model model) {
		model.addAttribute("tarea", p);
		return "agregarTarea";
	}
	
	@GetMapping("/eliminarEmpleado/{id}")
	public String eliminarEmpleado(@PathVariable int id) {
		servicio.borrar(id);
		return "redirect:/listarEmpleados";
	}
	
	@GetMapping("/eliminarTarea/{id}")
	public String eliminarTarea(@PathVariable int id) {
		TServicio.borrar(id);
		return "redirect:/listarTareas";
	}
	
	
	
	
}











