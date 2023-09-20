package com.domrade.balance.repository;

import com.domrade.balance.models.Balance;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IBalanceRepository extends JpaRepository<Balance, Long> {

    @Query(value = "SELECT * FROM balance_table b WHERE b.user_id = :userId ORDER BY b.updatedDTM DESC", nativeQuery = true)
    public Optional<List<Balance>> getBalanceUpdatesByUserId(Long userId);

   // @Query(value = "SELECT * FROM balance_table b WHERE b.user_id = ?1 ORDER BY ?#{#pageable}",
   @Query(value = "SELECT * FROM balance_table b WHERE b.user_id = :userId ORDER BY b.updatedDTM DESC",
            countQuery = "SELECT count(*) FROM balance_table",
            nativeQuery = true)
    public Optional<List<Balance>> getLatestBalanceByUserId(Long userId);

}
