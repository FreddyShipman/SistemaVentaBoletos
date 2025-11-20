/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventaboletos.dto;

/**
 * @author JONATHAN ROMERO OROZCO - 00000251632
 * DTO para manejar peticiones de usuarios(reembolsos,cambios,quejas)
 */
import java.util.Date;

public class SolicitudDTO {
    
    private String idSolicitud;
    private String idUsuario;
    private String idBoleto; // El boleto relacionado (si aplica)
    private String tipoSolicitud; // Ej: "REEMBOLSO", "CAMBIO_ASIENTO", "ACLARACION"
    private String descripcion;
    private String estado; // "PENDIENTE", "APROBADO", "RECHAZADO"
    private Date fechaSolicitud;

    public SolicitudDTO() {
    }

    // Getters y Setters
    public String getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(String idSolicitud) { this.idSolicitud = idSolicitud; }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getIdBoleto() { return idBoleto; }
    public void setIdBoleto(String idBoleto) { this.idBoleto = idBoleto; }

    public String getTipoSolicitud() { return tipoSolicitud; }
    public void setTipoSolicitud(String tipoSolicitud) { this.tipoSolicitud = tipoSolicitud; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(Date fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }
}