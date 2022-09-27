package com.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoanDTO {
	private int Totalno;
	private int Curno;
	private int principal;
	private int rate;
	private int curprincipal;
	private int curprincipalpayment;
	private int curinterestpayment;

@Builder
public LoanDTO(int Totalno, int principal, int rate) {
	this.Totalno = Totalno;
	this.principal = principal;
	this.rate = rate;
}
}
