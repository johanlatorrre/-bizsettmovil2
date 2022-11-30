package com.sena.splashscreenapp.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Publicaciones {


    private String id;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    private String imagen;
    private String emprendimiento_id;

    public Publicaciones() {

    }


    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEmprendimiento_id() {
        return emprendimiento_id;
    }

    public void setEmprendimiento_id(String emprendimiento_id) {
        this.emprendimiento_id = emprendimiento_id;
    }

    public Publicaciones(String id, String descripcion, String emprendimiento_id, String imagen) {
        this.id = id;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.emprendimiento_id = emprendimiento_id;
    }
    
}