package com.gmail.luchyk.viktoriia.glovodb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    private String phone;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registration_address_id")
    private AddressEntity registrationAddress;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aux_customer_delivery_addresses",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id")
    )
    private Set<AddressEntity> deliveryAddresses = new HashSet<>();
}
