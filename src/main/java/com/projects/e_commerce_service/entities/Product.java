package com.projects.e_commerce_service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.DataOutput;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Product extends AuditEntity{
    @Id
    @Column(nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;
    private UUID customerId;
    private UUID shoppingCardId;
    private UUID orderId;
    private String description;

    private Double unitPrice;
    //TODO url
    private String img;

    private Integer variability;

//    @OneToOne
//    private ProductCategory categories;

//    @ManyToOne
//    @JoinColumn(name = "cart_id", nullable = false)
//    private ShoppingCart cart;

    private List<CategoryType> categories;
    private String characteristics;

    private Integer rating;
    private String size;

}
