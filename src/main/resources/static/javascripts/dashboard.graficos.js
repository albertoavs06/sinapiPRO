var SinapiPRO = SinapiPRO || {};

Brewer.GraficoOrcamentoPorMes = (function() {
	
	function GraficoOrcamentoPorMes() {
		this.ctx = $('#graficoOrcamentosPorMes')[0].getContext('2d');
	}
	
	GraficoOrcamentoPorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'orcamentos/totalPorMes',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(orcamentoMes) {
		var meses = [];
		var valores = [];
		vendaMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoOrcamentosPorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Vendas por mês',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		});
	}
	
	return GraficoOrcamentoPorMes;
	
}());

SinapiPRO.GraficoOrcamentoPorOrigem = (function() {
	
	function GraficoOrcamentoPorOrigem() {
		this.ctx = $('#graficoOrcamentosPorOrigem')[0].getContext('2d');
	}
	
	GraficoVendaPorOrigem.prototype.iniciar = function() {
		$.ajax({
			url: 'orcamentos/porOrigem',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(vendaOrigem) {
		var meses = [];
		var composicoesNacionais = [];
		var composicoesInternacionais = [];
		
		vendaOrigem.forEach(function(obj) {
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
	
	return GraficoVendaPorOrigem;
	
}());


$(function() {
	var graficoOrcamentoPorMes = new SinapiPRO.GraficoVendaPorMes();
	graficoVendaPorMes.iniciar();
	
	var graficoOrcamentoPorOrigem = new SinapiPRO.GraficoVendaPorOrigem();
	graficoVendaPorOrigem.iniciar();
});
