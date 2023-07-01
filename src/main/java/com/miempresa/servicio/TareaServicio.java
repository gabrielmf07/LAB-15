package com.miempresa.servicio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.miempresa.interfaceServicio.ITareaServicio;
import com.miempresa.interfaces.ITarea;
import com.miempresa.modelo.Empleado;
import com.miempresa.modelo.Tarea;

@Service
public class TareaServicio implements ITareaServicio {

    @Autowired
    private ITarea repot;

    @Override
    public List<Tarea> listar(String filtro) {
    	return (List<Tarea>)repot.findAll();

    }

    @Override
    public Tarea get(int id) {
        return (Tarea) repot.findById(id).get();
    }

    @Override
    public Optional<Tarea> listarId(int id) {
        return repot.findById(id);
    }

    @Override
    public int guardar(Tarea p) {
        Tarea ta = repot.save(p);
        if (!ta.equals(null)){
            return  1;
        }
        return 0;
    }
    @Override
    public void borrar(int id) {
        repot.deleteById(id);
    }
}
