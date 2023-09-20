package com.domrade.withdrawal.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "withdrawals_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Withdrawal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column( name = "user_id")
    private Long userId;

    @Column( name = "amount")
    private Float amount;

    @Column( name = "createdDTM")
    private LocalDateTime createdDTM;
}
