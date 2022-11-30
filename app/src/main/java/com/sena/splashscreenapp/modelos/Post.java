package com.sena.splashscreenapp.modelos;

public class Post {
        private int imagen;
        private String descripcion;

        public Post(int imagen, String descripcion) {
            this.imagen = imagen;
            this.descripcion = descripcion;
        }


        public int getImagen() {
            return imagen;
        }

        public void setImagen(int imagen) {
            this.imagen = imagen;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
    }


