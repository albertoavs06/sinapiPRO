package br.edu.ifrn.sinapiPRO.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.transaction.annotation.Transactional;

import br.edu.ifrn.sinapiPRO.model.SinapiInsumo;
import br.edu.ifrn.sinapiPRO.repository.SinapiInsumos;


public class SinapiToMysqlController {
		
	
	private static SinapiInsumos sinapiInsumos; 
	
	@Transactional
	public static void salvar(SinapiInsumo insumo) {		
		sinapiInsumos.save(insumo);
	}
	public static String valueString; 
	public static Double valueNumeric; 
	
	private static HSSFWorkbook readFile(String filename) throws IOException {
		try (FileInputStream fis = new FileInputStream(filename)) {
			return new HSSFWorkbook(fis);        // NOSONAR - should not be closed here
		}
	}
	
    public static BigDecimal StrToBig (String numero, Integer qtdeCasasDecimais) {
        String casasDecimais = "";
        String num = numero;
        DecimalFormat df = null;
        try {
            if (qtdeCasasDecimais > 0) {
                for (int i = 0; i < qtdeCasasDecimais; i++) {
                    casasDecimais = casasDecimais.concat("0");
                }
                if (num.equals("")) {
                    num = "0.".concat(casasDecimais);
                }
                df = new DecimalFormat("#,##0.".concat(casasDecimais), new DecimalFormatSymbols(new Locale("pt", "BR")));
                df.setParseBigDecimal(true); // aqui o pulo do gato
                df.setRoundingMode(RoundingMode.DOWN);
                return (BigDecimal) df.parse(num); // deve voltar o BigDecimal "1234.56"
            } else {
                if (num.equals("")) {
                    num = "0";
                }
                df = new DecimalFormat("###########");
                df.setParseBigDecimal(true);
                df.setRoundingMode(RoundingMode.DOWN);
                return new BigDecimal(((BigDecimal) df.parse(num)).intValue());
            }
        } catch (ParseException ex) {
            // Logger.getLogger(Utilitarios.class.getName()).log(Level.SEVERE, null, ex);
            return new BigDecimal("0");
        }
    }

	public static void main(String[] args) throws IOException {

		String fileName = "/home/sergio/sinapiDownload/SINAPI_Preco_Ref_Insumos_RN_022018_NaoDesonerado.XLS";
		SinapiInsumo insumo = new SinapiInsumo(); 
		try (HSSFWorkbook wb = SinapiToMysqlController.readFile(fileName)) {
			System.out.println("Data dump:\n");
			for (int k = 0; k < wb.getNumberOfSheets(); k++) {
				HSSFSheet sheet = wb.getSheetAt(k);
				int rows = sheet.getPhysicalNumberOfRows();
				System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows + " row(s).");
				for (int r = 0; r < rows; r++) {
					HSSFRow row = sheet.getRow(r);
					if (row == null)  {
						continue;
					}
					if ( row.getPhysicalNumberOfCells() < 5 ) {
						continue;
					}
					
					if (row.getRowNum() ==6) { 
						continue; 
					}
					
					System.out.println("\nROW " + row.getRowNum() + " has " + row.getPhysicalNumberOfCells() + " cell(s).");
					insumo = new SinapiInsumo(); 
					
					for (int c = 0; c < row.getLastCellNum(); c++) {
						HSSFCell cell = row.getCell(c);
						String value;
						if (cell != null) {
							switch (cell.getCellTypeEnum()) {
								case FORMULA:
									value = "FORMULA value=" + cell.getCellFormula();
									break;
									
								case NUMERIC:
									value = "NUMERIC value=" + cell.getNumericCellValue();
									valueNumeric = cell.getNumericCellValue();
									break;

								case STRING:
									value = "STRING value=" + cell.getStringCellValue();
									valueString = cell.getStringCellValue(); 
									break;

								case BLANK:
									value = "<BLANK>";
									break;

								case BOOLEAN:
									value = "BOOLEAN value-" + cell.getBooleanCellValue();
									break;
								case ERROR:
									value = "ERROR value=" + cell.getErrorCellValue();
									break;

								default:
									value = "UNKNOWN value of type " + cell.getCellTypeEnum();
							}
							
							System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="+ value);
							
							
							
							switch (cell.getColumnIndex()) {
								case 0:
									insumo.setCodigo(valueNumeric.longValue()); 
									break;
								case 1:
									insumo.setDescricao(valueString.trim()); 
									break;
								case 2:
									insumo.setUnidade(valueString.trim());
									break;
								case 3:
									insumo.setOrigem(valueString.trim());
									break;
								case 4:
									insumo.setPreco(StrToBig(valueString, 2)); 
									break;
								default:
									insumo = null; 
							
							}
						}
					}
					System.out.println(insumo.toString());	
					
					salvar(insumo); 
					
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
 
}



