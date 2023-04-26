package com.ccsw.tutorial.loan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorial.loan.model.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    @Query("select l from Loan l where (:game is null or l.game.id = :game)"
            + " and (:client is null or l.client.id = :client)"
            + " and (:date is null or ( (l.dateLoan<=:date) and (:date <= l.dateReturn)))")

    Page<Loan> findAll(@Param("game") Long game, @Param("client") Long client, @Param("date") Date date,
            Pageable pageable);

    @Query("select l from Loan l where l.game.id = :game and ((l.dateReturn between :dateLoan and :dateReturn)"
            + " or (l.dateLoan between :dateLoan and :dateReturn) "
            + " or (l.dateLoan <= :dateLoan and l.dateReturn >= :dateReturn))")

    List<Loan> gameOnDates(@Param("game") Long game, @Param("dateLoan") Date dateLoan,
            @Param("dateReturn") Date dateReturn);

    @Query("select l from Loan l where l.client.id = :client and ((l.dateReturn between :dateLoan and :dateReturn)"
            + " or (l.dateLoan between :dateLoan and :dateReturn)"
            + " or (l.dateLoan <= :dateLoan and l.dateReturn >= :dateReturn))")
    List<Loan> clientOnDates(@Param("client") Long client, @Param("dateLoan") Date dateLoan,
            @Param("dateReturn") Date dateReturn);

}
