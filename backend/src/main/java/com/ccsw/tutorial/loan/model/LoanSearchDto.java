package com.ccsw.tutorial.loan.model;

import java.util.Date;

import com.ccsw.tutorial.pagination.PageableRequest;

public class LoanSearchDto {
    private PageableRequest pageable;
    private Long game;
    private Long client;
    private Date date;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public Long getGame() {
        return game;
    }

    public void setGame(Long game) {
        this.game = game;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LoanSearchDto() {
        super();
        // TODO Auto-generated constructor stub
    }

}
