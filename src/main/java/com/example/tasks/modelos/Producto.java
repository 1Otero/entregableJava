package com.example.tasks.modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Producto")
@Entity
public class Producto implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "Id")
   @JsonProperty(value = "Id")
   private Long Id;
   @Column(name = "Nombre")
   @JsonProperty(value = "Nombre")
   private String Nombre;
   @Column(name = "Description")
   @JsonProperty(value = "Description")
   private String Description;
   @JsonProperty(value = "Precio")
   @Column(name = "Precio")
   private int Precio;
}
