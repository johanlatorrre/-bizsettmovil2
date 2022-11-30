package com.sena.splashscreenapp.modelos;

public class Empleos {

    private String id;
    private String evidencia;
    private String mensaje_trabajo;
    private String emprendimiento_id;
    private String user_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public String getMensaje_trabajo() {
        return mensaje_trabajo;
    }

    public void setMensaje_trabajo(String mensaje_trabajo) {
        this.mensaje_trabajo = mensaje_trabajo;
    }

    public String getEmprendimiento_id() {
        return emprendimiento_id;
    }

    public void setEmprendimiento_id(String emprendimiento_id) {
        this.emprendimiento_id = emprendimiento_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Empleos(String id, String evidencia, String mensaje_trabajo, String emprendimiento_id, String user_id) {
        this.id = id;
        this.evidencia = evidencia;
        this.mensaje_trabajo = mensaje_trabajo;
        this.emprendimiento_id = emprendimiento_id;
        this.user_id = user_id;
    }
}
