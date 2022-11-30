package com.sena.splashscreenapp.modelos;

public class Multimedias {
    private String id;
    private String url_contenido;
    private String publicacion_id;

    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl_contenido() {
        return url_contenido;
    }

    public void setUrl_contenido(String url_contenido) {
        this.url_contenido = url_contenido;
    }

    public String getPublicacion_id() {
        return publicacion_id;
    }

    public void setPublicacion_id(String publicacion_id) {
        this.publicacion_id = publicacion_id;
    }

    public Multimedias(String id, String url_contenido, String publicacion_id) {
        this.id = id;
        this.url_contenido = url_contenido;
        this.publicacion_id = publicacion_id;
    }
}
