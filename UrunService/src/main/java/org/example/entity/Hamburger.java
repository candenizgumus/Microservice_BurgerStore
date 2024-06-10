package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblhamburger")
public class Hamburger
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
