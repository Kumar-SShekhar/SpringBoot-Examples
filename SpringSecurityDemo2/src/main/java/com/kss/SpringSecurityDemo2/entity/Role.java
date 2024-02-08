package com.kss.SpringSecurityDemo2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


//@Entity
//@Table(name = "roles")
public enum Role {

    USER,
    ADMIN
}
