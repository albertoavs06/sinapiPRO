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

SinapiPRO.GraficoOrcamentosPorOrigem = (function() {
	
	function GraficoOrcamentosPorOrigem() {
		this.ctx = $('#graficoOrcamentosPorOrigem')[0].getContext('2d');
	}
	
	GraficoOrcamentosPorOrigem.prototype.iniciar = function() {
		$.ajax({
			url: 'orcamentos/porOrigem',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(orcamentoOrigem) {
		var meses = [];
		var composicoesNacionais = [];
		var composicoesInternacionais = [];
		
		orcamentoOrigem.forEach(function(obj) {
			meses.unshift(obj.mes);
			composicoesNacionais.unshift(obj.totalNacional);
			composicoesInternacionais.unshift(obj.totalInternacional)
		});
		
		var graficoOrcamentosPorOrigem = new Chart(this.ctx, {
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
	
	return GraficoOrcamentosPorOrigem;
	
}());


$(function() {
	var graficoOrcamentosPorMes = new SinapiPRO.GraficoOrcamentosPorMes();
	graficoOrcamentosPorMes.iniciar();
	
	var graficoOrcamentosPorOrigem = new SinapiPRO.GraficoOrcamentosPorOrigem();
	graficoOrcamentosPorOrigem.iniciar();
});
