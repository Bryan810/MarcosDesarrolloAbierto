/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.Beans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import uce.edu.ec.Entidades.Departamento;
import uce.edu.ec.SessionsBeans.DepartamentoFacadeLocal;

/**
 *
 * @author Usuario1
 */
@ManagedBean
@RequestScoped
public class DepartamentoBean {

    @EJB
    private DepartamentoFacadeLocal departamentoFacade;
    private String nombre;
    private String ubicacion;

    public DepartamentoBean() {
    }

    public List<Departamento> getDepartamentos() {
        return departamentoFacade.findAll();
    }

    public String crear() {
        Departamento d = new Departamento();
        d.setNombre(nombre);
        d.setUbicacion(ubicacion);
        departamentoFacade.create(d);
        return "Departamento";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
