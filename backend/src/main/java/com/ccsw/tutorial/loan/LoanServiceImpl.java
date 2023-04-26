package com.ccsw.tutorial.loan;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import com.ccsw.tutorial.model.ResultDto;

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
    public ResultDto save(LoanDto dto) {

        ResultDto result = new ResultDto();

        if (dto.getDateLoan().before(dto.getDateReturn())) {

            if (TimeUnit.DAYS.convert(Math.abs(dto.getDateReturn().getTime() - dto.getDateLoan().getTime()),
                    TimeUnit.MILLISECONDS) <= 14) {

                if (availableGame(dto.getGame(), dto.getDateLoan(), dto.getDateReturn())) {

                    if (availableClient(dto.getClient(), dto.getDateLoan(), dto.getDateReturn())) {

                        Loan loan = new Loan();

                        BeanUtils.copyProperties(dto, loan, "id", "game", "client");
                        loan.setGame(gameService.get(dto.getGame().getId()));
                        loan.setClient(clientService.get(dto.getClient().getId()));

                        loanRepository.save(loan);

                        result.setResult(1);
                        result.setDescription("Todo correcto: nuevo préstamo creado con ID: " + loan.getId());
                    } else {
                        result.setResult(-4);
                        result.setDescription("Error: Mismo cliente con 2 juegos en el mismo periodo");
                    }
                } else {
                    result.setResult(-3);
                    result.setDescription("Error: Mismo juego con 2 clientes en el mismo periodo");
                }

            } else {
                result.setResult(-2);
                result.setDescription("Error: El préstamo supera el plazo máximo de 14 días");
            }
        } else {
            result.setResult(-1);
            result.setDescription("Error: La fecha de inicio no es anterior a la fecha final");
        }

        return result;
    }

    private boolean availableClient(ClientDto client, Date dateLoan, Date dateReturn) {
        List<Loan> aux = loanRepository.clientOnDates(client.getId(), dateLoan, dateReturn);

        if (aux.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean availableGame(GameDto game, Date dateLoan, Date dateReturn) {
        List<Loan> aux = loanRepository.gameOnDates(game.getId(), dateLoan, dateReturn);

        if (aux.size() > 0) {
            return false;
        } else {
            return true;
        }
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
