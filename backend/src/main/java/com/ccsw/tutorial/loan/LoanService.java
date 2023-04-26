package com.ccsw.tutorial.loan;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import com.ccsw.tutorial.model.ResultDto;

public interface LoanService {

    Page<Loan> findPage(LoanSearchDto dto);

    ResultDto save(LoanDto dto);

    void delete(Long id) throws Exception;

}
