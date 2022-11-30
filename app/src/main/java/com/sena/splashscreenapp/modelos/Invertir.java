package com.sena.splashscreenapp.modelos;

public class Invertir {

    private String id;
    private String propuesta;
    private String user_id;
    private String emprendimiento_id;

    public String getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmprendimiento_id() {
        return emprendimiento_id;
    }

    public void setEmprendimiento_id(String emprendimiento_id) {
        this.emprendimiento_id = emprendimiento_id;
    }








    public Invertir(String id, String user_id, String propuesta, String emprendimiento_id) {
        this.id = id;
        this.user_id = user_id;
        this.propuesta = propuesta;
        this.emprendimiento_id = emprendimiento_id;
    }

}
