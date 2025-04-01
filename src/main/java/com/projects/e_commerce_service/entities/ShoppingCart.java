package com.projects.e_commerce_service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="shopping_cart")
public class ShoppingCart extends AuditEntity{
    @Id
    @Column(nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private UUID customerId;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = false)

    private List<Product> products;

}
