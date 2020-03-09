package com.br.Meeting.helpers;

import java.util.Date;

public class DetailsError {
	
	public class DetalhesErro {
		private Date date;
		private int status;
		private String mensagem;
		private String origem;
		public DetalhesErro(Date date, int status, String mensagem, String origem) {
			super();
			this.date = date;
			this.status = status;
			this.mensagem = mensagem;
			this.origem = origem;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		public String getOrigem() {
			return origem;
		}
		public void setOrigem(String origem) {
			this.origem = origem;
		}
		
		
}
}