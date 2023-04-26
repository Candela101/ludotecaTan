package com.ccsw.tutorial.loan;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.common.criteria.AlreadyExistsException;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    GameService gameService;

    @Autowired
    ClientService clientService;

    @Autowired
    DozerBeanMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Loan> findPage(LoanSearchDto dto) {

        return (Page<Loan>) loanRepository.findAll(dto.getGame(), dto.getClient(), dto.getDate(),
                dto.getPageable().getPageable());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(LoanDto dto) {
        try {
            if (dto.getDateLoan().after(dto.getDateReturn())) {
                throw new AlreadyExistsException("La fecha de inicio no puede ser posterior a la fecha de fin", HttpStatus.BAD_REQUEST);
            }
    
            if (!checkLoanPeriod(dto.getDateLoan(), dto.getDateReturn())) {
                throw new AlreadyExistsException("El periodo de préstamo excede el máximo permitido", HttpStatus.BAD_REQUEST);
            }
    
            if (!checkGameAvailability(dto.getGame(), dto.getDateLoan(), dto.getDateReturn())) {
                throw new AlreadyExistsException("El juego no está disponible en las fechas seleccionadas", HttpStatus.CONFLICT);
            }
    
            if (!checkClientAvailability(dto.getClient(), dto.getDateLoan(), dto.getDateReturn())) {
                throw new AlreadyExistsException("El cliente tiene otro préstamo en las fechas seleccionadas", HttpStatus.CONFLICT);
            }
    
            Loan loan = new Loan();
            BeanUtils.copyProperties(dto, loan, "id", "game", "client");
            loan.setGame(gameService.get(dto.getGame().getId()));
            loan.setClient(clientService.get(dto.getClient().getId()));
            loanRepository.save(loan);
        } catch (AlreadyExistsException e) {
            System.out.println("Error al guardar el préstamo: " + e.getMessage());
            System.out.println("Código de error HTTP: " + e.getStatus().value());
        }
    }

    private boolean checkLoanPeriod(Date dateLoan, Date dateReturn) {
        long loanDays = TimeUnit.DAYS.convert(Math.abs(dateReturn.getTime() - dateLoan.getTime()),
                TimeUnit.MILLISECONDS);
        return loanDays <= 14;
    }

    private boolean checkClientAvailability(ClientDto client, Date dateLoan, Date dateReturn) {
        List<Loan> loans = loanRepository.clientOnDates(client.getId(), dateLoan, dateReturn);
        return loans.isEmpty();
    }

    private boolean checkGameAvailability(GameDto game, Date dateLoan, Date dateReturn) {
        List<Loan> loans = loanRepository.gameOnDates(game.getId(), dateLoan, dateReturn);
        return loans.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.loanRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.loanRepository.deleteById(id);
    }

}
