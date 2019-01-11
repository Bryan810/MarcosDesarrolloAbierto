/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.Beans;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import uce.edu.ec.Entidades.Departamento;
import uce.edu.ec.Entidades.Empleado;
import uce.edu.ec.SessionsBeans.DepartamentoFacadeLocal;
import uce.edu.ec.SessionsBeans.EmpleadoFacadeLocal;

/**
 *
 * @author Usuario1
 */
@ManagedBean
@RequestScoped
public class EmpleadoBean {

    @EJB
    private DepartamentoFacadeLocal departamentoFacade;
    @EJB
    private EmpleadoFacadeLocal empleadoFacade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private Integer id;
    private String nombre;
    private String apellido;
    private String cedula;
    private BigDecimal salario;
    private String cargo;
    private String clave;
    private Departamento departamento;
    private Empleado empleado;

    @PostConstruct
    public void init() {
        empleado = new Empleado();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadoBean() {
        departamento = new Departamento();
    }

    public List<Empleado> getEmpleados() {
        return empleadoFacade.findAll();
    }

    public String crearEmpleado() {
        Empleado e = new Empleado();
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setCedula(cedula);
        e.setSalario(salario);
        e.setCargo(cargo);
        e.setClave(clave);
        e.setIdDepartamento(departamentoFacade.find(departamento.getIdDepartamento()));
        empleadoFacade.create(e);
        return "Empleado";
    }

    public void actualizarEmpleado(RowEditEvent event) {
        Empleado e = (Empleado) event.getObject();
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setCedula(cedula);
        e.setCargo(cargo);
        e.setSalario(salario);
        e.setIdDepartamento(departamentoFacade.find(departamento.getIdDepartamento()));
        empleadoFacade.edit(e);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos Actualizados"));
    }

    public void cancelar() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }

    public String iniciarSesion() {
        Empleado emp;
        String redireccion = null;
        try {
            emp = empleadoFacade.iniciarSesion(empleado);

            if (emp != null) {
                if ("Administrador".equals(empleadoFacade.cargoEmpleado(emp))) {
                    redireccion = "Empleado?faces-redirect=true";
                } else {
                    if ("Inventario".equals(empleadoFacade.cargoEmpleado(emp))) {
                        redireccion = "Producto?faces-redirect=true";
                    } else {
                        redireccion = "Usuario?faces-redirect=true";
                    }

                }


            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Error!"));
        }
        return redireccion;
    }
     public void buscarEmpleado() {
        getEmpleados();
        Empleado e = new Empleado();
        e = empleadoFacade.find(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
        id = e.getIdEmpleado();
        nombre = e.getNombre();
        apellido = e.getApellido();
        cedula=e.getCedula();
        salario=e.getSalario();
        departamento=e.getIdDepartamento();


    }
        public void eliminarEmpleado() {
        Empleado e = new Empleado();
        e = empleadoFacade.find(id);
        if (e != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seguro Quiere Eliminar"));
            empleadoFacade.remove(e);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existe"));
        }
    }
    
    public String verInventario(){
        return "Usuario";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String contraseña) {
        this.clave = contraseña;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public DepartamentoFacadeLocal getDepartamentoFacade() {
        return departamentoFacade;
    }

    public void setDepartamentoFacade(DepartamentoFacadeLocal departamentoFacade) {
        this.departamentoFacade = departamentoFacade;
    }
}
