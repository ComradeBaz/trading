package com.domrade.balance.models;

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
@Table(name = "balance_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column( name = "user_id")
    private Long userId;

    @Column( name = "amount")
    private Float amount;

    @Column(name = "total_balance")
    private Float totalBalance;

    @Column( name = "updatedDTM")
    private LocalDateTime updatedDTM;

    public Balance(Long userId, Float amount, Float totalBalance, LocalDateTime updatedDTM) {
        this.userId = userId;
        this.amount = amount;
        this.totalBalance = totalBalance;
        this.updatedDTM = updatedDTM;
    }
}
