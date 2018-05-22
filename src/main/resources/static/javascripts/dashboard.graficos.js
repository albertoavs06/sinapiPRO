var SinapiPRO = SinapiPRO || {};

SinapiPRO.GraficoOrcamentosPorMes = (function() {
	
	function GraficoOrcamentosPorMes() {
		this.ctx = $('#graficoOrcamentosPorMes')[0].getContext('2d');
	}
	
	GraficoOrcamentosPorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'orcamentos/totalPorMes',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(orcamentoMes) {
		var meses = [];
		var valores = [];
		orcamentoMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoOrcamentosPorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Orcamentos por mês',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		});
	}
	
	return GraficoOrcamentosPorMes;
	
}());

SinapiPRO.GraficoOrcamentosPorBase = (function() {
	
	function GraficoOrcamentosPorBase() {
		this.ctx = $('#graficoOrcamentosPorBase')[0].getContext('2d');
	}
	
	GraficoOrcamentosPorBase.prototype.iniciar = function() {
		$.ajax({
			url: 'orcamentos/porBase',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(orcamentoBase) {
		var meses = [];
		var composicoesNacionais = [];
		var composicoesInternacionais = [];
		
		orcamentoBase.forEach(function(obj) {
			meses.unshift(obj.mes);
			composicoesNacionais.unshift(obj.totalNacional);
			composicoesInternacionais.unshift(obj.totalInternacional)
		});
		
		var graficoOrcamentosPorBase = new Chart(this.ctx, {
		    type: 'bar',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Nacional',
		    		backgroundColor: "rgba(220,220,220,0.5)",
	                data: composicoesNacionais
		    	},
		    	{
		    		label: 'Internacional',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                data: composicoesInternacionais
		    	}]
		    },
		});
	}
	
	return GraficoOrcamentosPorBase;
	
}());


$(function() {
	var graficoOrcamentosPorMes = new SinapiPRO.GraficoOrcamentosPorMes();
	graficoOrcamentosPorMes.iniciar();
	
	var graficoOrcamentosPorBase = new SinapiPRO.GraficoOrcamentosPorBase();
	graficoOrcamentosPorBase.iniciar();
});
