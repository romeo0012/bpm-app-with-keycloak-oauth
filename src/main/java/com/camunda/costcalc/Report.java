package com.camunda.costcalc;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

public class Report implements JavaDelegate {

	private final static Logger LOGGER = Logger.getLogger("PROJECT-REQUESTS");

	public void execute(DelegateExecution execution) throws Exception {

		Workbook workbook = new XSSFWorkbook();
		DataFormat format = workbook.createDataFormat();
		Sheet sheet;
		Row row;
		Cell cell;
		Font headerFont = ((XSSFWorkbook) workbook).createFont();
		headerFont.setFontName("Arial");
		headerFont.setFontHeightInPoints((short) 11);
		headerFont.setBold(true);
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(headerFont);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		CellStyle headerStyle2 = workbook.createCellStyle();
		headerStyle2.setFont(headerFont);
		headerStyle2.setAlignment(HorizontalAlignment.CENTER);
		headerStyle2.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		headerStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		CellStyle labelStyle = workbook.createCellStyle();
		Font labelFont = ((XSSFWorkbook) workbook).createFont();
		labelFont.setBold(false);
		labelStyle.setFont(labelFont);
		CellStyle cellStyle = workbook.createCellStyle();
		//style.setDataFormat(format.getFormat("#,##0.0000")); //184 236,0000
		cellStyle.setDataFormat(format.getFormat("#,##0")); //184 236
		cellStyle.setAlignment(HorizontalAlignment.CENTER);

		Map < String, Object[] > data;
		Set < String > keyid;
		int rowid;
		int headerrow;
		int cellid;
		int rowpointer = 0;
		int tablerows = 0;

		sheet = workbook.createSheet(Config.ReportName + " Costs");
		sheet.setColumnWidth(0, 9000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		
		data = new TreeMap < String, Object[] >();
		data.put( "1", new Object[] { "Total costs", "Capex", "Opex", "3y Total" });
		data.put( "2", new Object[] { "Total in EUR", String.valueOf(Config.capex), String.valueOf(Config.opex), String.valueOf(Config.capexopex) });
		data.put( "3", new Object[] { "Total in EUR inc. VAT", String.valueOf(Config.capexVAT), String.valueOf(Config.opexVAT), String.valueOf(Config.capexopexVAT) });
		data.put( "4", new Object[] { "Total in CZK", String.valueOf(Config.capexCZ), String.valueOf(Config.opexCZ), String.valueOf(Config.capexopexCZ) });
		data.put( "5", new Object[] { "Total in CZK inc. VAT", String.valueOf(Config.capexCZVAT), String.valueOf(Config.opexCZVAT), String.valueOf(Config.capexopexCZVAT) });
		keyid = data.keySet();
		tablerows = 7;
		rowid = rowpointer;
		rowpointer = rowpointer + tablerows;
		headerrow = rowid + 1;
		for (String key : keyid) {
		    row = sheet.createRow(rowid++);
		    Object [] objectArr = data.get(key);
		    cellid = 0;
		    for (Object obj : objectArr) {
		    	if (rowid == headerrow) { 
				    cell = row.createCell(cellid++);
				    cell.setCellValue((String) obj);
				    cell.setCellStyle(headerStyle2);
		    	} else { 
				    cell = row.createCell(cellid++);
				    if (cellid == 1) {
					    cell.setCellValue((String) obj);
				    	cell.setCellStyle(labelStyle);
				    } else {
				    	if (Double.valueOf((String) obj) == 0) { cell.setCellValue("-"); } else { cell.setCellValue(Double.valueOf((String) obj)); };
					    cell.setCellStyle(cellStyle);
				    }
				    //LOGGER.info("Cell " + (rowid-1) + ":" + (cellid-1) + " " + obj);	
		    	};
		    }
		}
		data = new TreeMap < String, Object[] >();
		data.put( "1", new Object[] { "Costs per year", "1st year", "Year + 2", "Year + 3" });
		data.put( "2", new Object[] { "Capex in EUR (External)", String.valueOf(Config.capex1e), String.valueOf(Config.capex2e), String.valueOf(Config.capex3e) });
		data.put( "3", new Object[] { "Capex in EUR (Internal)", String.valueOf(Config.capex1i), String.valueOf(Config.capex2i), String.valueOf(Config.capex3i) });
		data.put( "4", new Object[] { "Opex in EUR", String.valueOf(Config.opex1), String.valueOf(Config.opex2), String.valueOf(Config.opex3) });
		data.put( "5", new Object[] { "Capex in CZK (External)", String.valueOf(Config.capex1eCZ), String.valueOf(Config.capex2eCZ), String.valueOf(Config.capex3eCZ) });
		data.put( "6", new Object[] { "Capex in CZK (Internal)", String.valueOf(Config.capex1iCZ), String.valueOf(Config.capex2iCZ), String.valueOf(Config.capex3iCZ) });
		data.put( "7", new Object[] { "Opex in CZK", String.valueOf(Config.opex1CZ), String.valueOf(Config.opex2CZ), String.valueOf(Config.opex3CZ) });
		keyid = data.keySet();
		tablerows = 9;
		rowid = rowpointer;
		rowpointer = rowpointer + tablerows;
		headerrow = rowid + 1;
		for (String key : keyid) {
		    row = sheet.createRow(rowid++);
		    Object [] objectArr = data.get(key);
		    cellid = 0;
		    for (Object obj : objectArr) {
		    	if (rowid == headerrow) { 
				    cell = row.createCell(cellid++);
				    cell.setCellValue((String) obj);
				    cell.setCellStyle(headerStyle2);
		    	} else { 
				    cell = row.createCell(cellid++);
				    if (cellid == 1) {
					    cell.setCellValue((String) obj);
				    	cell.setCellStyle(labelStyle);
				    } else {
				    	if (Double.valueOf((String) obj) == 0) { cell.setCellValue("-"); } else { cell.setCellValue(Double.valueOf((String) obj)); };
					    cell.setCellStyle(cellStyle);
				    }
				    //LOGGER.info("Cell " + rowid + ":" + cellid + " " + obj);	
		    	};
		    }
		}
		data = new TreeMap < String, Object[] >();
		data.put( "1", new Object[] { "SLA / IT Continuity Solution", "RTO in hours", "RPO in min", "Archiv in years" });
		data.put( "2", new Object[] { String.valueOf(Config.sla)+" / "+String.valueOf(Config.continuity), String.valueOf(Config.rto), String.valueOf(Config.rpo), String.valueOf(Config.archiv) });
		keyid = data.keySet();
		tablerows = 4;
		rowid = rowpointer;
		rowpointer = rowpointer + tablerows;
		headerrow = rowid + 1;
		for (String key : keyid) {
		    row = sheet.createRow(rowid++);
		    Object [] objectArr = data.get(key);
		    cellid = 0;
		    for (Object obj : objectArr) {
		    	if (rowid == headerrow) { 
				    cell = row.createCell(cellid++);
				    cell.setCellValue((String) obj);
				    cell.setCellStyle(headerStyle2);
		    	} else { 
				    cell = row.createCell(cellid++);
				    if (cellid == 1) {
					    cell.setCellValue((String) obj);
				    	cell.setCellStyle(labelStyle);
				    } else {
					    if (Double.valueOf((String) obj) == 0) { cell.setCellValue("-"); } else { cell.setCellValue(Double.valueOf((String) obj)); };
					    cell.setCellStyle(cellStyle);
				    }
				    //LOGGER.info("Cell " + rowid + ":" + cellid + " " + obj);	
		    	};
		    }
		}
		
		data = new TreeMap < String, Object[] >();
		data.put( "1", new Object[] { "Environments", "External Service", "Development", "Test", "Quality Assurance", "Bug Fix", "Production" });
		data.put( "2", new Object[] { "Load Balanced VIP", String.valueOf(Config.VipIsExt).replace("true", "√").replace("false", "-"), String.valueOf(Config.dvip), String.valueOf(Config.tvip), String.valueOf(Config.qvip), String.valueOf(Config.bvip), String.valueOf(Config.pvip) });
		data.put( "3", new Object[] { "Firewall NAT", String.valueOf(Config.NatIsExt).replace("true", "√").replace("false", "-"), String.valueOf(Config.dnat), String.valueOf(Config.tnat), String.valueOf(Config.qnat), String.valueOf(Config.bnat), String.valueOf(Config.pnat) });
		data.put( "4", new Object[] { "Server", String.valueOf(Config.SrvIsExt).replace("true", "√").replace("false", "-"), String.valueOf(Config.dsrv), String.valueOf(Config.tsrv), String.valueOf(Config.qsrv), String.valueOf(Config.bsrv), String.valueOf(Config.psrv) });
		data.put( "5", new Object[] { "Database", String.valueOf(Config.DbIsExt).replace("true", "√").replace("false", "-"), String.valueOf(Config.ddb), String.valueOf(Config.tdb), String.valueOf(Config.qdb), String.valueOf(Config.bdb), String.valueOf(Config.pdb) });
		data.put( "6", new Object[] { "Public Commercial Certificate", "-", String.valueOf(Config.dcerp), String.valueOf(Config.tcerp), String.valueOf(Config.qcerp), String.valueOf(Config.bcerp), String.valueOf(Config.pcerp) });
		data.put( "7", new Object[] { "Internally Issued Certificate", "-", String.valueOf(Config.dceri), String.valueOf(Config.tceri), String.valueOf(Config.qceri), String.valueOf(Config.bceri), String.valueOf(Config.pceri) });
		data.put( "8", new Object[] { "File Transfer Queues", "-", String.valueOf(Config.dftq), String.valueOf(Config.tftq), String.valueOf(Config.qftq), String.valueOf(Config.bftq), String.valueOf(Config.pftq) });
		data.put( "9", new Object[] { "Message Queues", "-", String.valueOf(Config.dmsq), String.valueOf(Config.tmsq), String.valueOf(Config.qmsq), String.valueOf(Config.bmsq), String.valueOf(Config.pmsq) });
		keyid = data.keySet();
		tablerows = 11;
		rowid = rowpointer;
		rowpointer = rowpointer + tablerows;
		headerrow = rowid + 1;
		for (String key : keyid) {
		    row = sheet.createRow(rowid++);
		    Object [] objectArr = data.get(key);
		    cellid = 0;
		    for (Object obj : objectArr) {
		    	if (rowid == headerrow) { 
				    cell = row.createCell(cellid++);
				    cell.setCellValue((String) obj);
				    cell.setCellStyle(headerStyle);
		    	} else { 
				    cell = row.createCell(cellid++);
				    if (cellid == 1) {
					    cell.setCellValue((String) obj);
				    	cell.setCellStyle(labelStyle);
				    }
				    if (cellid == 2) {
					    cell.setCellValue((String) obj);
					    cell.setCellStyle(cellStyle);
				    }
				    if (cellid > 2) {
				    	if (Double.valueOf((String) obj) == 0) { cell.setCellValue("-"); } else { cell.setCellValue(Double.valueOf((String) obj)); };
					    cell.setCellStyle(cellStyle);
				    }
				    //LOGGER.info("Cell " + rowid + ":" + cellid + " " + obj);	
		    	};
		    }
		}
		data = new TreeMap < String, Object[] >();
		data.put( "1", new Object[] { "Direct Resources", "1st year", "Year + 2", "Year + 3" });
		data.put( "2", new Object[] { "CPU Cores", String.valueOf(Config.cpu1), String.valueOf(Config.cpu2), String.valueOf(Config.cpu3) });
		data.put( "3", new Object[] { "Memory in GB", String.valueOf(Config.memory1), String.valueOf(Config.memory2), String.valueOf(Config.memory3) });
		data.put( "4", new Object[] { "Server Disk in GB", String.valueOf(Config.hdd1), String.valueOf(Config.hdd2), String.valueOf(Config.hdd3) });
		data.put( "5", new Object[] { "Database Size in GB", String.valueOf(Config.database1), String.valueOf(Config.database2), String.valueOf(Config.database3) });
		data.put( "6", new Object[] { "Individual VPN accounts", String.valueOf(Config.vpn1), String.valueOf(Config.vpn2), String.valueOf(Config.vpn3) });
		data.put( "7", new Object[] { "Site-to-Site VPN bandwidth in Mbps", String.valueOf(Config.ssvpn1), String.valueOf(Config.ssvpn2), String.valueOf(Config.ssvpn3) });
		data.put( "8", new Object[] { "Leased Line bandwidth in Mbps", String.valueOf(Config.llb1), String.valueOf(Config.llb2), String.valueOf(Config.llb3) });
		keyid = data.keySet();
		tablerows = 10;
		rowid = rowpointer;
		rowpointer = rowpointer + tablerows;
		headerrow = rowid + 1;
		for (String key : keyid) {
		    row = sheet.createRow(rowid++);
		    Object [] objectArr = data.get(key);
		    cellid = 0;
		    for (Object obj : objectArr) {
		    	if (rowid == headerrow) { 
				    cell = row.createCell(cellid++);
				    cell.setCellValue((String) obj);
				    cell.setCellStyle(headerStyle);
		    	} else { 
				    cell = row.createCell(cellid++);
				    if (cellid == 1) {
					    cell.setCellValue((String) obj);
				    	cell.setCellStyle(labelStyle);
				    }
				    if (cellid > 1) {
				    	if (Double.valueOf((String) obj) == 0) { cell.setCellValue("-"); } else { cell.setCellValue(Double.valueOf((String) obj)); };
					    cell.setCellStyle(cellStyle);
				    }
				    //LOGGER.info("Cell " + rowid + ":" + cellid + " " + obj);	
		    	};
		    }
		}
		data = new TreeMap < String, Object[] >();
		data.put( "1", new Object[] { "Support Resources", "1st year", "Year + 2", "Year + 3" });
		data.put( "2", new Object[] { "Transactions store in GB", String.valueOf(Config.trs1), String.valueOf(Config.trs2), String.valueOf(Config.trs3) });
		data.put( "3", new Object[] { "Business logging", String.valueOf(Config.buslog1).replace("true", "√").replace("false", "-"), String.valueOf(Config.buslog2).replace("true", "√").replace("false", "-"), String.valueOf(Config.buslog3).replace("true", "√").replace("false", "-") });
		data.put( "4", new Object[] { "Technical logging", String.valueOf(Config.techlog1).replace("true", "√").replace("false", "-"), String.valueOf(Config.techlog2).replace("true", "√").replace("false", "-"), String.valueOf(Config.techlog3).replace("true", "√").replace("false", "-") });
		keyid = data.keySet();
		tablerows = 6;
		rowid = rowpointer;
		rowpointer = rowpointer + tablerows;
		headerrow = rowid + 1;
		for (String key : keyid) {
		    row = sheet.createRow(rowid++);
		    Object [] objectArr = data.get(key);
		    cellid = 0;
		    for (Object obj : objectArr) {
		    	if (rowid == headerrow) { 
				    cell = row.createCell(cellid++);
				    cell.setCellValue((String) obj);
				    cell.setCellStyle(headerStyle);
		    	} else { 
		    		if (rowid == (headerrow + 1)) { 
					    cell = row.createCell(cellid++);
					    if (cellid == 1) {
						    cell.setCellValue((String) obj);
					    	cell.setCellStyle(labelStyle);
					    }
					    if (cellid > 1) {
					    	if (Double.valueOf((String) obj) == 0) { cell.setCellValue("-"); } else { cell.setCellValue(Double.valueOf((String) obj)); };
						    cell.setCellStyle(cellStyle);
					    }
		    		} else {
					    cell = row.createCell(cellid++);
					    if (cellid == 1) {
						    cell.setCellValue((String) obj);
					    	cell.setCellStyle(labelStyle);
					    }
					    if (cellid > 1) {
						    cell.setCellValue((String) obj);
					    	cell.setCellStyle(cellStyle);
					    }
		    		}
				    //LOGGER.info("Cell " + rowid + ":" + cellid + " " + obj);	
		    	};
		    }
		}
		data = new TreeMap < String, Object[] >();
		data.put( "1", new Object[] { "Network lines", "1st year", "Year + 2", "Year + 3" });
		data.put( "2", new Object[] { "PRG - Internet", String.valueOf(Config.PrgInt).replace("true", "√").replace("false", "-"), String.valueOf(Config.PrgInt).replace("true", "√").replace("false", "-"), String.valueOf(Config.PrgInt).replace("true", "√").replace("false", "-") });
		data.put( "3", new Object[] { "PRG - GPE", String.valueOf(Config.PrgGpe).replace("true", "√").replace("false", "-"), String.valueOf(Config.PrgGpe).replace("true", "√").replace("false", "-"), String.valueOf(Config.PrgGpe).replace("true", "√").replace("false", "-") });
		data.put( "4", new Object[] { "PRG - SIA CREDIT", String.valueOf(Config.PrgSia).replace("true", "√").replace("false", "-"), String.valueOf(Config.PrgSia).replace("true", "√").replace("false", "-"), String.valueOf(Config.PrgSia).replace("true", "√").replace("false", "-") });
		data.put( "5", new Object[] { "PRG Office - NUR", String.valueOf(Config.NurPrgDcMpls).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurPrgDcMpls).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurPrgDcMpls).replace("true", "√").replace("false", "-") });
		data.put( "6", new Object[] { "NUR - Internet", String.valueOf(Config.NurInt).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurInt).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurInt).replace("true", "√").replace("false", "-") });
		data.put( "7", new Object[] { "NUR - ITG Win", String.valueOf(Config.NurWin).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurWin).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurWin).replace("true", "√").replace("false", "-") });
		data.put( "8", new Object[] { "NUR - SIA DEBET", String.valueOf(Config.NurSia).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurSia).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurSia).replace("true", "√").replace("false", "-") });
		data.put( "9", new Object[] { "NUR - FIS", String.valueOf(Config.NurFis).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurFis).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurFis).replace("true", "√").replace("false", "-") });
		data.put( "10", new Object[] { "NUR - BNP WAN", String.valueOf(Config.NurWan).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurWan).replace("true", "√").replace("false", "-"), String.valueOf(Config.NurWan).replace("true", "√").replace("false", "-") });
		keyid = data.keySet();
		tablerows = 12;
		rowid = rowpointer;
		rowpointer = rowpointer + tablerows;
		headerrow = rowid + 1;
		for (String key : keyid) {
		    row = sheet.createRow(rowid++);
		    Object [] objectArr = data.get(key);
		    cellid = 0;
		    for (Object obj : objectArr) {
		    	if (rowid == headerrow) { 
				    cell = row.createCell(cellid++);
				    cell.setCellValue((String) obj);
				    cell.setCellStyle(headerStyle);
		    	} else { 
				    cell = row.createCell(cellid++);
				    if (cellid == 1) {
					    cell.setCellValue((String) obj);
				    	cell.setCellStyle(labelStyle);
				    }
				    if (cellid > 1) {
				    	cell.setCellValue((String) obj);
					    cell.setCellStyle(cellStyle);
				    }
				    //LOGGER.info("Cell " + rowid + ":" + cellid + " " + obj);	
		    	};
		    }
		}
		data = new TreeMap < String, Object[] >();
		data.put( "1", new Object[] { "High availability", "Public Web Zone", "Application Zone", "Middleware Zone", "Database Zone" });
		data.put( "2", new Object[] { "Firewall Rule or NAT", String.valueOf(Config.WebFirewall).replace("true", "√").replace("false", "-"), String.valueOf(Config.ApplicationFirewall).replace("true", "√").replace("false", "-"), String.valueOf(Config.MiddlewareFirewall).replace("true", "√").replace("false", "-"), String.valueOf(Config.DatabaseFirewall).replace("true", "√").replace("false", "-") });
		data.put( "3", new Object[] { "Apache Reverse Proxy", String.valueOf(Config.WebApacheReverseProxy).replace("true", "√").replace("false", "-"), String.valueOf(Config.ApplicationApacheReverseProxy).replace("true", "√").replace("false", "-"), String.valueOf(Config.MiddlewareApacheReverseProxy).replace("true", "√").replace("false", "-"), "-" });
		data.put( "4", new Object[] { "Application Server Active Active", "-", String.valueOf(Config.ApplicationServerActiveActive).replace("√", "Yes").replace("false", "-"), String.valueOf(Config.MiddlewareServerActiveActive).replace("true", "√").replace("false", "-"), "-" });
		data.put( "5", new Object[] { "EMC Autostart", String.valueOf(Config.WebEMCAutostart).replace("√", "Yes").replace("false", "-"), String.valueOf(Config.ApplicationEMCAutostart).replace("true", "√").replace("false", "-"), String.valueOf(Config.MiddlewareEMCAutostart).replace("true", "√").replace("false", "-"), String.valueOf(Config.DatabaseEMCAutostart).replace("true", "√").replace("false", "-") });
		data.put( "6", new Object[] { "VMware HA", String.valueOf(Config.WebVMwareHA).replace("true", "√").replace("false", "-"), String.valueOf(Config.ApplicationVMwareHA).replace("true", "√").replace("false", "-"), String.valueOf(Config.MiddlewareVMwareHA).replace("true", "√").replace("false", "-"), String.valueOf(Config.DatabaseVMwareHA).replace("true", "√").replace("false", "-") });
		data.put( "7", new Object[] { "Oracle Data Guard", "-", "-", "-", String.valueOf(Config.OracleDataGuard).replace("true", "√").replace("false", "-") });
		data.put( "8", new Object[] { "MS SQL Cluster", "-", "-", "-", String.valueOf(Config.MSSQLCluster).replace("true", "√").replace("false", "-") });
		data.put( "9", new Object[] { "MS Windows Cluster", "-", String.valueOf(Config.ApplicationMSWindowsCluster).replace("true", "√").replace("false", "-"), String.valueOf(Config.MiddlewareMSWindowsCluster).replace("true", "√").replace("false", "-"), "-" });
		data.put( "10", new Object[] { "Red Hat Cluster", String.valueOf(Config.WebRedHatCluster).replace("true", "√").replace("false", "-"), String.valueOf(Config.ApplicationRedHatCluster).replace("true", "√").replace("false", "-"), String.valueOf(Config.MiddlewareRedHatCluster).replace("true", "√").replace("false", "-"), "-" });
		data.put( "11", new Object[] { "Redis Sentinel Cluster", "-", String.valueOf(Config.ApplicationRedisSentinelCluster).replace("true", "√").replace("false", "-"), String.valueOf(Config.MiddlewareRedisSentinelCluster).replace("true", "√").replace("false", "-"), "-" });
		keyid = data.keySet();
		tablerows = 13;
		rowid = rowpointer;
		rowpointer = rowpointer + tablerows;
		headerrow = rowid + 1;
		for (String key : keyid) {
		    row = sheet.createRow(rowid++);
		    Object [] objectArr = data.get(key);
		    cellid = 0;
		    for (Object obj : objectArr) {
		    	if (rowid == headerrow) { 
				    cell = row.createCell(cellid++);
				    cell.setCellValue((String) obj);
				    cell.setCellStyle(headerStyle);
		    	} else { 
				    cell = row.createCell(cellid++);
				    cell.setCellValue((String) obj);
			    	cell.setCellStyle(labelStyle);
				    if (cellid == 1) {
					    cell.setCellValue((String) obj);
				    	cell.setCellStyle(labelStyle);
				    }
				    if (cellid > 1) {
					    cell.setCellValue((String) obj);
					    cell.setCellStyle(cellStyle);
				    }
				    //LOGGER.info("Cell " + rowid + ":" + cellid + " " + obj);	
		    	};
		    }
		}
		data = new TreeMap < String, Object[] >();
		data.put( "1", new Object[] { "Infrastructure", "External Service", "PSD", "DEV", "TST", "QA", "BFX", "PROD" });
		data.put( "2", new Object[] { "Technical Design (MD)", "-", String.valueOf(Config.tdes1), String.valueOf(Config.tdes2), String.valueOf(Config.tdes3), String.valueOf(Config.tdes4), String.valueOf(Config.tdes5), String.valueOf(Config.tdes6) });
		data.put( "3", new Object[] { "Production Design (MD)", "-", String.valueOf(Config.pdes1), String.valueOf(Config.pdes2), String.valueOf(Config.pdes3), String.valueOf(Config.pdes4), String.valueOf(Config.pdes5), String.valueOf(Config.pdes6) });
		data.put( "4", new Object[] { "Programming (MD)", String.valueOf(Config.PrgIsExt).replace("true", "√").replace("false", "-"), String.valueOf(Config.prg1), String.valueOf(Config.prg2), String.valueOf(Config.prg3), String.valueOf(Config.prg4), String.valueOf(Config.prg5), String.valueOf(Config.prg6) });
		data.put( "5", new Object[] { "Deployment (MD)", String.valueOf(Config.DplIsExt).replace("true", "√").replace("false", "-"), String.valueOf(Config.dpl1), String.valueOf(Config.dpl2), String.valueOf(Config.dpl3), String.valueOf(Config.dpl4), String.valueOf(Config.dpl5), String.valueOf(Config.dpl6) });
		data.put( "6", new Object[] { "License (EUR/y)", "-", String.valueOf(Config.lic1), String.valueOf(Config.lic2), String.valueOf(Config.lic3), String.valueOf(Config.lic4), String.valueOf(Config.lic5), String.valueOf(Config.lic6) });
		data.put( "7", new Object[] { "Maintenance (MD/y)", "-", String.valueOf(Config.mtn1), String.valueOf(Config.mtn2), String.valueOf(Config.mtn3), String.valueOf(Config.mtn4), String.valueOf(Config.mtn5), String.valueOf(Config.mtn6) });
		keyid = data.keySet();
		tablerows = 9;
		rowid = rowpointer;
		rowpointer = rowpointer + tablerows;
		headerrow = rowid + 1;
		for (String key : keyid) {
		    row = sheet.createRow(rowid++);
		    Object [] objectArr = data.get(key);
		    cellid = 0;
		    for (Object obj : objectArr) {
		    	if (rowid == headerrow) { 
				    cell = row.createCell(cellid++);
				    cell.setCellValue((String) obj);
				    cell.setCellStyle(headerStyle);
		    	} else { 
				    cell = row.createCell(cellid++);
				    if (cellid == 1) {
					    cell.setCellValue((String) obj);
				    	cell.setCellStyle(labelStyle);
				    }
				    if (cellid == 2) {
					    cell.setCellValue((String) obj);
					    cell.setCellStyle(cellStyle);
				    }
				    if (cellid > 2) {
				    	if (Double.valueOf((String) obj) == 0) { cell.setCellValue("-"); } else { cell.setCellValue(Double.valueOf((String) obj)); };
					    cell.setCellStyle(cellStyle);
				    }
				    //LOGGER.info("Cell " + rowid + ":" + cellid + " '" + obj + "'");	
		    	};
		    }
		}

		String fileLocation = Config.ReportDir + Config.ReportName + ".xlsx";
		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
		workbook.close();
		execution.setVariable("EXCEL_REPORT", Variables.fileValue(new File(fileLocation)));
	    
		LOGGER.info("Report Written...");	
		
	}
}