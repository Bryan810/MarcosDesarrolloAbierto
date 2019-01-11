/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.Beans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import uce.edu.ec.Entidades.Producto;
import uce.edu.ec.SessionsBeans.ProductoFacadeLocal;

/**
 *
 * @author Usuario1
 */
@ManagedBean
@RequestScoped
public class ProductoBean {

    @EJB
    private ProductoFacadeLocal productoFacade;
    private Integer id;
    private String nombre;
    private String marca;
    private String descripcion;
    private short existencias;
    private Producto producto;

    public ProductoBean() {
    }

    public List<Producto> getProductos() {
        return productoFacade.findAll();
    }

    public String crearProducto() {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setMarca(marca);
        p.setExistencias(existencias);
        p.setDescripcion(descripcion);
        productoFacade.create(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Creado"));
        return "Producto";
    }

    public void actualizar(RowEditEvent event) {
        Producto p = (Producto) event.getObject();
        p.getNombre();
        p.getMarca();
        p.getExistencias();
        p.getDescripcion();
        p.setNombre(nombre);
        p.setMarca(marca);
        p.setExistencias(existencias);
        p.setDescripcion(descripcion);
        productoFacade.edit(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos Actualizados"));
        if (p.getExistencias() < 10) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El Item: " + p.getNombre() + " Tiene menos de 10 existencias"));
        }
    }

    public void cancelar(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }

    public void buscarProducto() {
        getProductos();
        Producto p = new Producto();
        p = productoFacade.find(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
        id = p.getIdProducto();
        nombre = p.getNombre();
        marca = p.getMarca();
        existencias = p.getExistencias();
        descripcion = p.getDescripcion();


    }

    public void eliminarProducto() {
        Producto p = new Producto();
        p = productoFacade.find(id);
        if (p != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seguro Quiere Eliminar"));
            productoFacade.remove(p);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existe"));
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void leer(Producto pro) {
        producto = pro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getExistencias() {
        return existencias;
    }

    public void setExistencias(short existencias) {
        this.existencias = existencias;
    }
}
