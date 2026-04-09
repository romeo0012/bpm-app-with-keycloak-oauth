package com.camunda.costcalc;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalculateCost implements JavaDelegate {

	private final static Logger LOGGER = Logger.getLogger("PROJECT-REQUESTS");

	public void execute(DelegateExecution execution) throws Exception {

		LOGGER.info("Calc Start...");
		
		Config.dvip = (long) execution.getVariable("dvip");
		Config.tvip = (long) execution.getVariable("tvip");
		Config.qvip = (long) execution.getVariable("qvip");
		Config.bvip = (long) execution.getVariable("bvip");
		Config.pvip = (long) execution.getVariable("pvip");

		Config.dsrv = (long) execution.getVariable("dsrv");
		Config.tsrv = (long) execution.getVariable("tsrv");
		Config.qsrv = (long) execution.getVariable("qsrv");
		Config.bsrv = (long) execution.getVariable("bsrv");
		Config.psrv = (long) execution.getVariable("psrv");

		Config.ddb = (long) execution.getVariable("ddb");
		Config.tdb = (long) execution.getVariable("tdb");
		Config.qdb = (long) execution.getVariable("qdb");
		Config.bdb = (long) execution.getVariable("bdb");
		Config.pdb = (long) execution.getVariable("pdb");

		Config.ienc = (boolean) execution.getVariable("ienc");
		Config.penc = (boolean) execution.getVariable("penc");
		Config.dcerp = (long) execution.getVariable("dcerp");
		Config.tcerp = (long) execution.getVariable("tcerp");
		Config.qcerp = (long) execution.getVariable("qcerp");
		Config.bcerp = (long) execution.getVariable("bcerp");
		Config.pcerp = (long) execution.getVariable("pcerp");
		Config.dceri = (long) execution.getVariable("dceri");
		Config.tceri = (long) execution.getVariable("tceri");
		Config.qceri = (long) execution.getVariable("qceri");
		Config.bceri = (long) execution.getVariable("bceri");
		Config.pceri = (long) execution.getVariable("pceri");

		Config.dftq = (long) execution.getVariable("dftq");
		Config.tftq = (long) execution.getVariable("tftq");
		Config.qftq = (long) execution.getVariable("qftq");
		Config.bftq = (long) execution.getVariable("bftq");
		Config.pftq = (long) execution.getVariable("pftq");		
		Config.dmsq = (long) execution.getVariable("dmsq");
		Config.tmsq = (long) execution.getVariable("tmsq");
		Config.qmsq = (long) execution.getVariable("qmsq");
		Config.bmsq = (long) execution.getVariable("bmsq");
		Config.pmsq = (long) execution.getVariable("pmsq");		
		
		Config.cpu1 = Long.valueOf((Long) execution.getVariable("cpu1"));
		Config.cpu2 = Long.valueOf((Long) execution.getVariable("cpu2"));
		Config.cpu3 = Long.valueOf((Long) execution.getVariable("cpu3"));
		Config.memory1 = Long.valueOf((Long) execution.getVariable("memory1"));
		Config.memory2 = Long.valueOf((Long) execution.getVariable("memory2"));
		Config.memory3 = Long.valueOf((Long) execution.getVariable("memory3"));
		Config.hdd1 = Long.valueOf((Long) execution.getVariable("hdd1"));
		Config.hdd2 = Long.valueOf((Long) execution.getVariable("hdd2"));
		Config.hdd3 = Long.valueOf((Long) execution.getVariable("hdd3"));
		Config.database1 = Long.valueOf((Long) execution.getVariable("database1"));
		Config.database2 = Long.valueOf((Long) execution.getVariable("database2"));
		Config.database3 = Long.valueOf((Long) execution.getVariable("database3"));

		Config.trs1 = Long.valueOf((Long) execution.getVariable("trs1"));
		Config.trs2 = Long.valueOf((Long) execution.getVariable("trs2"));
		Config.trs3 = Long.valueOf((Long) execution.getVariable("trs3"));
		Config.buslog1 = (boolean) execution.getVariable("buslog1");
		Config.buslog2 = (boolean) execution.getVariable("buslog2");
		Config.buslog3 = (boolean) execution.getVariable("buslog3");
		Config.techlog1 = (boolean) execution.getVariable("techlog1");
		Config.techlog2 = (boolean) execution.getVariable("techlog2");
		Config.techlog3 = (boolean) execution.getVariable("techlog3");

		Config.NurInt = (boolean) execution.getVariable("NurInt");
		Config.NurWin = (boolean) execution.getVariable("NurWin");
		Config.NurSia = (boolean) execution.getVariable("NurSia");
		Config.NurFis = (boolean) execution.getVariable("NurFis");
		Config.NurWan = (boolean) execution.getVariable("NurWan");
		Config.NurPrgDcMpls = (boolean) execution.getVariable("NurPrgDcMpls");
		Config.PrgInt = (boolean) execution.getVariable("PrgInt");
		Config.PrgGpe = (boolean) execution.getVariable("PrgGpe");
		Config.PrgSia = (boolean) execution.getVariable("PrgSia");

		Config.sla = (String) execution.getVariable("sla");
		Config.continuity = (String) execution.getVariable("continuity");
		Config.rto = Long.valueOf((Long) execution.getVariable("rto"));
		Config.rpo = Long.valueOf((Long) execution.getVariable("rpo"));
		Config.archiv = Long.valueOf((Long) execution.getVariable("archiv"));

		Config.vpn1 = Long.valueOf((Long) execution.getVariable("vpn1"));
		Config.vpn2 = Long.valueOf((Long) execution.getVariable("vpn2"));
		Config.vpn3 = Long.valueOf((Long) execution.getVariable("vpn3"));
		Config.ssvpn1 = Long.valueOf((Long) execution.getVariable("ssvpn1"));
		Config.ssvpn2 = Long.valueOf((Long) execution.getVariable("ssvpn2"));
		Config.ssvpn3 = Long.valueOf((Long) execution.getVariable("ssvpn3"));
		Config.llb1 = Long.valueOf((Long) execution.getVariable("llb1"));
		Config.llb2 = Long.valueOf((Long) execution.getVariable("llb2"));
		Config.llb3 = Long.valueOf((Long) execution.getVariable("llb3"));

		Config.WebFirewall = (boolean) execution.getVariable("WebFirewall");
		Config.ApplicationFirewall = (boolean) execution.getVariable("ApplicationFirewall");
		Config.MiddlewareFirewall = (boolean) execution.getVariable("MiddlewareFirewall");
		Config.DatabaseFirewall = (boolean) execution.getVariable("DatabaseFirewall");
		Config.WebApacheReverseProxy = (boolean) execution.getVariable("WebApacheReverseProxy");
		Config.ApplicationApacheReverseProxy = (boolean) execution.getVariable("ApplicationApacheReverseProxy");
		Config.MiddlewareApacheReverseProxy = (boolean) execution.getVariable("MiddlewareApacheReverseProxy");
		Config.ApplicationServerActiveActive = (boolean) execution.getVariable("ApplicationServerActiveActive");
		Config.MiddlewareServerActiveActive = (boolean) execution.getVariable("MiddlewareServerActiveActive");
		Config.WebEMCAutostart = (boolean) execution.getVariable("WebEMCAutostart");
		Config.ApplicationEMCAutostart = (boolean) execution.getVariable("ApplicationEMCAutostart");
		Config.MiddlewareEMCAutostart = (boolean) execution.getVariable("MiddlewareEMCAutostart");
		Config.DatabaseEMCAutostart = (boolean) execution.getVariable("DatabaseEMCAutostart");
		Config.WebVMwareHA = (boolean) execution.getVariable("WebVMwareHA");
		Config.ApplicationVMwareHA = (boolean) execution.getVariable("ApplicationVMwareHA");
		Config.MiddlewareVMwareHA = (boolean) execution.getVariable("MiddlewareVMwareHA");
		Config.DatabaseVMwareHA = (boolean) execution.getVariable("DatabaseVMwareHA");
		Config.OracleDataGuard = (boolean) execution.getVariable("OracleDataGuard");
		Config.MSSQLCluster = (boolean) execution.getVariable("MSSQLCluster");
		Config.ApplicationMSWindowsCluster = (boolean) execution.getVariable("ApplicationMSWindowsCluster");
		Config.MiddlewareMSWindowsCluster = (boolean) execution.getVariable("MiddlewareMSWindowsCluster");
		Config.WebRedHatCluster = (boolean) execution.getVariable("WebRedHatCluster");
		Config.ApplicationRedHatCluster = (boolean) execution.getVariable("ApplicationRedHatCluster");
		Config.MiddlewareRedHatCluster = (boolean) execution.getVariable("MiddlewareRedHatCluster");
		Config.ApplicationRedisSentinelCluster = (boolean) execution.getVariable("ApplicationRedisSentinelCluster");
		Config.MiddlewareRedisSentinelCluster = (boolean) execution.getVariable("MiddlewareRedisSentinelCluster");
		
		Config.tdes1 = (long) execution.getVariable("tdes1");
		Config.tdes2 = (long) execution.getVariable("tdes2");
		Config.tdes3 = (long) execution.getVariable("tdes3");
		Config.tdes4 = (long) execution.getVariable("tdes4");
		Config.tdes5 = (long) execution.getVariable("tdes5");
		Config.tdes6 = (long) execution.getVariable("tdes6");
		Config.pdes1 = (long) execution.getVariable("pdes1");
		Config.pdes2 = (long) execution.getVariable("pdes2");
		Config.pdes3 = (long) execution.getVariable("pdes3");
		Config.pdes4 = (long) execution.getVariable("pdes4");
		Config.pdes5 = (long) execution.getVariable("pdes5");
		Config.pdes6 = (long) execution.getVariable("pdes6");
		Config.prg1 = (long) execution.getVariable("prg1");
		Config.prg2 = (long) execution.getVariable("prg2");
		Config.prg3 = (long) execution.getVariable("prg3");
		Config.prg4 = (long) execution.getVariable("prg4");
		Config.prg5 = (long) execution.getVariable("prg5");
		Config.prg6 = (long) execution.getVariable("prg6");
		Config.PrgIsExt = (boolean) execution.getVariable("PrgIsExt");
		Config.dpl1 = (long) execution.getVariable("dpl1");
		Config.dpl2 = (long) execution.getVariable("dpl2");
		Config.dpl3 = (long) execution.getVariable("dpl3");
		Config.dpl4 = (long) execution.getVariable("dpl4");
		Config.dpl5 = (long) execution.getVariable("dpl5");
		Config.dpl6 = (long) execution.getVariable("dpl6");
		Config.DplIsExt = (boolean) execution.getVariable("DplIsExt");
		Config.lic1 = (long) execution.getVariable("lic1");
		Config.lic2 = (long) execution.getVariable("lic2");
		Config.lic3 = (long) execution.getVariable("lic3");
		Config.lic4 = (long) execution.getVariable("lic4");
		Config.lic5 = (long) execution.getVariable("lic5");
		Config.lic6 = (long) execution.getVariable("lic6");
		Config.mtn1 = (long) execution.getVariable("mtn1");
		Config.mtn2 = (long) execution.getVariable("mtn2");
		Config.mtn3 = (long) execution.getVariable("mtn3");
		Config.mtn4 = (long) execution.getVariable("mtn4");
		Config.mtn5 = (long) execution.getVariable("mtn5");
		Config.mtn6 = (long) execution.getVariable("mtn6");

		NumberFormat formatter = new DecimalFormat("#,###,###.##");

		// price in EUR - disposable cost
		Config.capex = 0;
		Config.capexVAT = 0;
		Config.capexCZVAT = 0;
		Config.capex1 = 0;
		Config.capex2 = 0;
		Config.capex3 = 0;
		Config.capex1e = 0;
		Config.capex2e = 0;
		Config.capex3e = 0;
		Config.capex1i = 0;
		Config.capex2i = 0;
		Config.capex3i = 0;
		Config.capexCZ = 0;
		Config.capex1CZ = 0;
		Config.capex2CZ = 0;
		Config.capex3CZ = 0;
		Config.capex1eCZ = 0;
		Config.capex2eCZ = 0;
		Config.capex3eCZ = 0;
		Config.capex1iCZ = 0;
		Config.capex2iCZ = 0;
		Config.capex3iCZ = 0;		
		
		// Environments
		Config.tmp = (Config.dvip + Config.tvip + Config.qvip + Config.bvip + Config.pvip) * Config.VipDeployCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		if (Config.VipIsExt) { Config.capex1e = Config.capex1e + Config.tmp; } else { Config.capex1i = Config.capex1i + Config.tmp; };		
		Config.tmp = (Config.dnat + Config.tnat + Config.qnat + Config.bnat + Config.pnat) * Config.NatDeployCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		if (Config.NatIsExt) { Config.capex1e = Config.capex1e + Config.tmp; } else { Config.capex1i = Config.capex1i + Config.tmp; };
		Config.tmp = (Config.dsrv + Config.tsrv + Config.qsrv + Config.bsrv + Config.psrv) * Config.SrvDeployCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		Config.capex1i = Config.capex1i + Config.tmp;
		Config.tmp = (Config.ddb + Config.tdb + Config.qdb + Config.bdb + Config.pdb) * Config.DbDeployCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		if (Config.DbIsExt) { Config.capex1e = Config.capex1e + Config.tmp; } else { Config.capex1i = Config.capex1i + Config.tmp; };
		Config.tmp = (Config.dcerp + Config.tcerp + Config.qcerp + Config.bcerp + Config.pcerp) * Config.PublicCertCost;
		Config.tmp = Config.tmp + ((Config.dcerp + Config.tcerp + Config.qcerp + Config.bcerp + Config.pcerp) * Config.CertDeployCost);
		Config.capex1 = Config.capex1 + Config.tmp;
		Config.capex1i = Config.capex1i + Config.tmp;
		Config.tmp = (Config.dceri + Config.tceri + Config.qceri + Config.bceri + Config.pceri) * Config.InternalCertCost;
		Config.tmp = Config.tmp + ((Config.dceri + Config.tceri + Config.qceri + Config.bceri + Config.pceri) * Config.CertDeployCost);
		Config.capex1 = Config.capex1 + Config.tmp;
		Config.capex1i = Config.capex1i + Config.tmp;
		Config.tmp = (Config.dftq + Config.tftq + Config.qftq + Config.bftq + Config.pftq) * Config.FtqDeployCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		Config.capex1i = Config.capex1i + Config.tmp;
		Config.tmp = (Config.dmsq + Config.tmsq + Config.qmsq + Config.bmsq + Config.pmsq) * Config.MsqDeployCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		Config.capex1i = Config.capex1i + Config.tmp;
		
		// Direct Resources
		Config.tmp = Config.vpn1 * Config.IndividualVpnCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		Config.capex1i = Config.capex1i + Config.tmp;
		
		// High availability
		Config.tmp = 0;
		if (Config.WebFirewall) { Config.tmp = Config.tmp + Config.FirewallRuleCost; };
		if (Config.ApplicationFirewall) { Config.tmp = Config.tmp + Config.FirewallRuleCost; };
		if (Config.MiddlewareFirewall) { Config.tmp = Config.tmp + Config.FirewallRuleCost; };
		if (Config.DatabaseFirewall) { Config.tmp = Config.tmp + Config.FirewallRuleCost; };
		if (Config.WebApacheReverseProxy) { Config.tmp = Config.tmp + Config.ApacheReverseProxyCost; };
		if (Config.ApplicationApacheReverseProxy) { Config.tmp = Config.tmp + Config.ApacheReverseProxyCost; };
		if (Config.MiddlewareApacheReverseProxy) { Config.tmp = Config.tmp + Config.ApacheReverseProxyCost; };
		if (Config.ApplicationServerActiveActive) { Config.tmp = Config.tmp + Config.ApplicationServerActiveActiveCost; };
		if (Config.MiddlewareServerActiveActive) { Config.tmp = Config.tmp + Config.ApplicationServerActiveActiveCost; };
		if (Config.WebEMCAutostart) { Config.tmp = Config.tmp + Config.EMCAutostartCost; };
		if (Config.ApplicationEMCAutostart) { Config.tmp = Config.tmp + Config.EMCAutostartCost; };
		if (Config.MiddlewareEMCAutostart) { Config.tmp = Config.tmp + Config.EMCAutostartCost; };
		if (Config.DatabaseEMCAutostart) { Config.tmp = Config.tmp + Config.EMCAutostartCost; };
		if (Config.WebVMwareHA) { Config.tmp = Config.tmp + Config.VMwareHACost; };
		if (Config.ApplicationVMwareHA) { Config.tmp = Config.tmp + Config.VMwareHACost; };
		if (Config.MiddlewareVMwareHA) { Config.tmp = Config.tmp + Config.VMwareHACost; };
		if (Config.DatabaseVMwareHA) { Config.tmp = Config.tmp + Config.VMwareHACost; };
		if (Config.OracleDataGuard) { Config.tmp = Config.tmp + Config.OracleDataGuardCost; };
		if (Config.MSSQLCluster) { Config.tmp = Config.tmp + Config.MSSQLClusterCost; };
		if (Config.ApplicationMSWindowsCluster) { Config.tmp = Config.tmp + Config.MSWindowsClusterCost; };
		if (Config.MiddlewareMSWindowsCluster) { Config.tmp = Config.tmp + Config.MSWindowsClusterCost; };
		if (Config.WebRedHatCluster) { Config.tmp = Config.tmp + Config.RedHatClusterCost; };
		if (Config.ApplicationRedHatCluster) { Config.tmp = Config.tmp + Config.RedHatClusterCost; };
		if (Config.MiddlewareRedHatCluster) { Config.tmp = Config.tmp + Config.RedHatClusterCost; };
		if (Config.ApplicationRedisSentinelCluster) { Config.tmp = Config.tmp + Config.RedisSentinelClusterCost; };
		if (Config.MiddlewareRedisSentinelCluster) { Config.tmp = Config.tmp + Config.RedisSentinelClusterCost; };
		Config.capex1 = Config.capex1 + Config.tmp;
		Config.capex1i = Config.capex1i + Config.tmp;

		// Infrastructure Design
		Config.tmp = (Config.tdes1 + Config.tdes2 + Config.tdes3 + Config.tdes4 + Config.tdes5 + Config.tdes6) * Config.DesignCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		Config.capex1i = Config.capex1i + Config.tmp;
		Config.tmp = (Config.pdes1 + Config.pdes2 + Config.pdes3 + Config.pdes4 + Config.pdes5 + Config.pdes6) * Config.DesignCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		Config.capex1i = Config.capex1i + Config.tmp;

		// Service recovery time and allowed data lost
		LOGGER.info("SLA = " + Config.sla);
		LOGGER.info("CAPEX1.0 = " + Config.capex1);
		LOGGER.info("Sla8x5costRatio = " + Config.Sla8x5costRatio);
		LOGGER.info("Sla24x7costRatio = " + Config.Sla24x7costRatio);
		LOGGER.info("IctPassiveCostRatio = " + Config.IctPassiveCostRatio);
		LOGGER.info("IctActiveCostRatio = " + Config.IctActiveCostRatio);
		
		if (Config.sla.indexOf("8x5") > -1) { Config.capex1 = Config.capex1 * Config.Sla8x5costRatio / 100 + Config.capex1; LOGGER.info("CAPEX1.8 = " + Config.capex1); }
		if (Config.sla.indexOf("24x7") > -1) { Config.capex1 = Config.capex1 * Config.Sla24x7costRatio / 100 + Config.capex1; LOGGER.info("CAPEX1.24 = " + Config.capex1); }
		if (Config.continuity.indexOf("passive") > -1) { Config.capex1 = Config.capex1 * Config.IctPassiveCostRatio / 100 + Config.capex1; LOGGER.info("CAPEX1.AP = " + Config.capex1); }
		if (Config.continuity.indexOf("active") > -1) { Config.capex1 = Config.capex1 * Config.IctActiveCostRatio / 100 + Config.capex1; LOGGER.info("CAPEX1.AA = " + Config.capex1); }
		
		if (Config.sla.indexOf("8x5") > -1) { Config.capex1i = Config.capex1i * Config.Sla8x5costRatio / 100 + Config.capex1i; LOGGER.info("CAPEX1.8 = " + Config.capex1i); }
		if (Config.sla.indexOf("24x7") > -1) { Config.capex1i = Config.capex1i * Config.Sla24x7costRatio / 100 + Config.capex1i; LOGGER.info("CAPEX1.24 = " + Config.capex1i); }
		if (Config.continuity.indexOf("passive") > -1) { Config.capex1i = Config.capex1i * Config.IctPassiveCostRatio / 100 + Config.capex1i; LOGGER.info("CAPEX1.AP = " + Config.capex1i); }
		if (Config.continuity.indexOf("active") > -1) { Config.capex1i = Config.capex1i * Config.IctActiveCostRatio / 100 + Config.capex1i; LOGGER.info("CAPEX1.AA = " + Config.capex1i); }

		if (Config.sla.indexOf("8x5") > -1) { Config.capex1e = Config.capex1e * Config.Sla8x5costRatio / 100 + Config.capex1e; LOGGER.info("CAPEX1.8 = " + Config.capex1e); }
		if (Config.sla.indexOf("24x7") > -1) { Config.capex1e = Config.capex1e * Config.Sla24x7costRatio / 100 + Config.capex1e; LOGGER.info("CAPEX1.24 = " + Config.capex1e); }
		if (Config.continuity.indexOf("passive") > -1) { Config.capex1e = Config.capex1e * Config.IctPassiveCostRatio / 100 + Config.capex1e; LOGGER.info("CAPEX1.AP = " + Config.capex1e); }
		if (Config.continuity.indexOf("active") > -1) { Config.capex1e = Config.capex1e * Config.IctActiveCostRatio / 100 + Config.capex1e; LOGGER.info("CAPEX1.AA = " + Config.capex1e); }

		// Other Costs
		Config.tmp = Config.prg1 * Config.ProgramingCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		if (Config.PrgIsExt) { Config.capex1e = Config.capex1e + Config.tmp; } else { Config.capex1i = Config.capex1i + Config.tmp; };
		Config.tmp = Config.dpl1 * Config.AppDeploymentCost;
		Config.capex1 = Config.capex1 + Config.tmp;
		if (Config.DplIsExt) { Config.capex1e = Config.capex1e + Config.tmp; } else { Config.capex1i = Config.capex1i + Config.tmp; };
		
		Config.tmp = Config.prg2 * Config.ProgramingCost;
		Config.capex2 = Config.capex2 + Config.tmp;
		if (Config.PrgIsExt) { Config.capex2e = Config.capex2e + Config.tmp; } else { Config.capex2i = Config.capex2i + Config.tmp; }
		Config.tmp = Config.dpl2 * Config.AppDeploymentCost;
		Config.capex2 = Config.capex2 + Config.tmp;
		if (Config.DplIsExt) { Config.capex2e = Config.capex2e + Config.tmp; } else { Config.capex2i = Config.capex2i + Config.tmp; };
		
		Config.tmp = Config.prg3 * Config.ProgramingCost;
		Config.capex3 = Config.capex3 + Config.tmp;
		if (Config.PrgIsExt) { Config.capex3e = Config.capex3e + Config.tmp; } else { Config.capex3i = Config.capex3i + Config.tmp; };
		Config.tmp = Config.dpl3 * Config.AppDeploymentCost;
		Config.capex3 = Config.capex3 + Config.tmp;
		if (Config.DplIsExt) { Config.capex3e = Config.capex3e + Config.tmp; } else { Config.capex3i = Config.capex3i + Config.tmp; };

		Config.capex = Config.capex1 + Config.capex2 + Config.capex3;
		Config.capexVAT = Config.capex * (100 + Config.VAT)/100;
		
		execution.setVariable("capex1e", formatter.format(Config.capex1e));
		execution.setVariable("capex2e", formatter.format(Config.capex2e));
		execution.setVariable("capex3e", formatter.format(Config.capex3e));
		execution.setVariable("capex1i", formatter.format(Config.capex1i));
		execution.setVariable("capex2i", formatter.format(Config.capex2i));
		execution.setVariable("capex3i", formatter.format(Config.capex3i));
		execution.setVariable("capex1", formatter.format(Config.capex1));
		execution.setVariable("capex2", formatter.format(Config.capex2));
		execution.setVariable("capex3", formatter.format(Config.capex3));
		execution.setVariable("capex", formatter.format(Config.capex));
		execution.setVariable("capexVAT", formatter.format(Config.capexVAT));
		
		// price in EUR - per year cost
		Config.opex = 0;
		Config.opexVAT = 0;
		Config.opexCZVAT = 0;
		Config.opex1 = 0;
		Config.opex2 = 0;
		Config.opex3 = 0;
		Config.capexopex = 0;
		Config.capexopexVAT = 0;
		Config.opexCZ = 0;
		Config.opex1CZ = 0;
		Config.opex2CZ = 0;
		Config.opex3CZ = 0;
		Config.capexopexCZ = 0;
		Config.capexopexCZVAT = 0;
		
		// Direct Resources
		Config.opex1 = Config.opex1 + (Config.ServerCost * Config.cpu1 / Config.VmRatio / Config.CpuBlock) + (Config.StorageCost * Config.hdd1 / Config.StorageBlock) + (Config.StorageCost * Config.database1 / Config.StorageBlock);
		Config.opex2 = Config.opex2 + (Config.ServerCost * Config.cpu2 / Config.VmRatio / Config.CpuBlock) + (Config.StorageCost * Config.hdd2 / Config.StorageBlock) + (Config.StorageCost * Config.database2 / Config.StorageBlock);
		Config.opex3 = Config.opex3 + (Config.ServerCost * Config.cpu3 / Config.VmRatio / Config.CpuBlock) + (Config.StorageCost * Config.hdd3 / Config.StorageBlock) + (Config.StorageCost * Config.database3 / Config.StorageBlock);
		Config.opex1 = Config.opex1 + (Config.lic1 * Config.LicenseCost) + (Config.mtn1 * Config.MaintanaceCost);
		Config.opex2 = Config.opex2 + (Config.lic2 * Config.LicenseCost) + (Config.mtn2 * Config.MaintanaceCost);
		Config.opex3 = Config.opex3 + (Config.lic3 * Config.LicenseCost) + (Config.mtn3 * Config.MaintanaceCost);
		Config.opex1 = Config.opex1 + (Config.dcerp + Config.tcerp + Config.qcerp + Config.bcerp + Config.pcerp + Config.dceri + Config.tceri + Config.qceri + Config.bceri + Config.pceri) * Config.PkiMgmtCost;
		Config.opex2 = Config.opex2 + (Config.dcerp + Config.tcerp + Config.qcerp + Config.bcerp + Config.pcerp + Config.dceri + Config.tceri + Config.qceri + Config.bceri + Config.pceri) * Config.PkiMgmtCost;
		Config.opex3 = Config.opex3 + (Config.dcerp + Config.tcerp + Config.qcerp + Config.bcerp + Config.pcerp + Config.dceri + Config.tceri + Config.qceri + Config.bceri + Config.pceri) * Config.PkiMgmtCost;
		Config.opex1 = Config.opex1 + (Config.LeasedLineCostPerMbps * Config.llb1);
		Config.opex2 = Config.opex2 + (Config.LeasedLineCostPerMbps * Config.llb2);
		Config.opex3 = Config.opex3 + (Config.LeasedLineCostPerMbps * Config.llb3);
		if (Config.ssvpn1 > 0) { Config.opex1 = Config.opex1 + (Config.ssvpn1 * Config.Site2SsiteVpnCostPerMbps); }
		if (Config.ssvpn2 > 0) { Config.opex2 = Config.opex2 + (Config.ssvpn2 * Config.Site2SsiteVpnCostPerMbps); }
		if (Config.ssvpn3 > 0) { Config.opex3 = Config.opex3 + (Config.ssvpn3 * Config.Site2SsiteVpnCostPerMbps); }		
		if (Config.llb1 > 0) { Config.opex1 = Config.llb1 * Config.LeasedLineCostPerMbps; }
		if (Config.llb2 > 0) { Config.opex2 = Config.llb2 * Config.LeasedLineCostPerMbps; }
		if (Config.llb2 > 0) { Config.opex3 = Config.llb3 * Config.LeasedLineCostPerMbps; }

		// Support Resources
		if (Config.trs1 > 0) { Config.opex1 = Config.opex1 + (Config.trs1 * Config.TransactionCost); }
		if (Config.trs2 > 0) { Config.opex2 = Config.opex2 + (Config.trs2 * Config.TransactionCost); }
		if (Config.trs3 > 0) { Config.opex3 = Config.opex3 + (Config.trs3 * Config.TransactionCost); }
		if (Config.buslog1) { Config.opex1 = Config.opex1 + Config.BusLogCost; }
		if (Config.buslog2) { Config.opex2 = Config.opex2 + Config.BusLogCost; }
		if (Config.buslog3) { Config.opex3 = Config.opex3 + Config.BusLogCost; }
		if (Config.techlog1) { Config.opex1 = Config.opex1 + Config.TechLogCost; }
		if (Config.techlog2) { Config.opex2 = Config.opex2 + Config.TechLogCost; }
		if (Config.techlog3) { Config.opex3 = Config.opex3 + Config.TechLogCost; }
		
		// Network lines
		if (Config.NurInt) { Config.opex1 = Config.opex1 + Config.NurIntCost; };
		if (Config.NurWin) { Config.opex1 = Config.opex1 + Config.NurWinCost; };
		if (Config.NurSia) { Config.opex1 = Config.opex1 + Config.NurSiaCost; };
		if (Config.NurFis) { Config.opex1 = Config.opex1 + Config.NurFisCost; };
		if (Config.NurWan) { Config.opex1 = Config.opex1 + Config.NurWanCost; };
		if (Config.NurPrgDcMpls) { Config.opex1 = Config.opex1 + Config.NurPrgDcMplsCost; };
		if (Config.PrgInt) { Config.opex1 = Config.opex1 + Config.PrgIntCost; };
		if (Config.PrgGpe) { Config.opex1 = Config.opex1 + Config.PrgGpeCost; };
		if (Config.PrgSia) { Config.opex1 = Config.opex1 + Config.PrgSiaCost; };		
		if (Config.NurInt) { Config.opex2 = Config.opex2 + Config.NurIntCost; };
		if (Config.NurWin) { Config.opex2 = Config.opex2 + Config.NurWinCost; };
		if (Config.NurSia) { Config.opex2 = Config.opex2 + Config.NurSiaCost; };
		if (Config.NurFis) { Config.opex2 = Config.opex2 + Config.NurFisCost; };
		if (Config.NurWan) { Config.opex2 = Config.opex2 + Config.NurWanCost; };
		if (Config.NurPrgDcMpls) { Config.opex2 = Config.opex2 + Config.NurPrgDcMplsCost; };
		if (Config.PrgInt) { Config.opex2 = Config.opex2 + Config.PrgIntCost; };
		if (Config.PrgGpe) { Config.opex2 = Config.opex2 + Config.PrgGpeCost; };
		if (Config.PrgSia) { Config.opex2 = Config.opex2 + Config.PrgSiaCost; };
		if (Config.NurInt) { Config.opex3 = Config.opex3 + Config.NurIntCost; };
		if (Config.NurWin) { Config.opex3 = Config.opex3 + Config.NurWinCost; };
		if (Config.NurSia) { Config.opex3 = Config.opex3 + Config.NurSiaCost; };
		if (Config.NurFis) { Config.opex3 = Config.opex3 + Config.NurFisCost; };
		if (Config.NurWan) { Config.opex3 = Config.opex3 + Config.NurWanCost; };
		if (Config.NurPrgDcMpls) { Config.opex3 = Config.opex3 + Config.NurPrgDcMplsCost; };
		if (Config.PrgInt) { Config.opex3 = Config.opex3 + Config.PrgIntCost; };
		if (Config.PrgGpe) { Config.opex3 = Config.opex3 + Config.PrgGpeCost; };
		if (Config.PrgSia) { Config.opex3 = Config.opex3 + Config.PrgSiaCost; };
		
		Config.opex = Config.opex1 + Config.opex2 + Config.opex3;
		Config.opexVAT = Config.opex * (100 + Config.VAT)/100;
		execution.setVariable("opex1", formatter.format(Config.opex1));
		execution.setVariable("opex2", formatter.format(Config.opex2));
		execution.setVariable("opex3", formatter.format(Config.opex3));
		execution.setVariable("opex", formatter.format(Config.opex));
		execution.setVariable("opexVAT", formatter.format(Config.opexVAT));
		
		// 3y total cost
		Config.capexopex = Config.capex + Config.opex;
		Config.capexopexVAT = (Config.capex + Config.opex) * (100 + Config.VAT)/100;
		execution.setVariable("capexopex", formatter.format(Config.capexopex));
		execution.setVariable("capexopexVAT", formatter.format(Config.capexopexVAT ));
		
		// price in CZK
		Config.capex1eCZ = Config.capex1e * Config.Eur2Czk;
		Config.capex2eCZ = Config.capex2e * Config.Eur2Czk;
		Config.capex3eCZ = Config.capex3e * Config.Eur2Czk;
		Config.capex1iCZ = Config.capex1i * Config.Eur2Czk;
		Config.capex2iCZ = Config.capex2i * Config.Eur2Czk;
		Config.capex3iCZ = Config.capex3i * Config.Eur2Czk;
		Config.capexCZ = Config.capex * Config.Eur2Czk;
		Config.capex1CZ = Config.capexCZ;
		Config.capex2CZ = 0;
		Config.capex3CZ = 0;
		Config.capexCZVAT = Config.capexCZ * (100 + Config.VAT)/100;
		execution.setVariable("capex1eCZ", formatter.format(Config.capex1eCZ));
		execution.setVariable("capex2eCZ", formatter.format(Config.capex2eCZ));
		execution.setVariable("capex3eCZ", formatter.format(Config.capex3eCZ));
		execution.setVariable("capex1iCZ", formatter.format(Config.capex1iCZ));
		execution.setVariable("capex2iCZ", formatter.format(Config.capex2iCZ));
		execution.setVariable("capex3iCZ", formatter.format(Config.capex3iCZ));
		execution.setVariable("capex1CZ", formatter.format(Config.capex1CZ));
		execution.setVariable("capex2CZ", formatter.format(Config.capex2CZ));
		execution.setVariable("capex3CZ", formatter.format(Config.capex3CZ));
		execution.setVariable("capexCZ", formatter.format(Config.capexCZ));
		execution.setVariable("capexCZVAT", formatter.format(Config.capexCZVAT));

		Config.opex1CZ = Config.opex1 * Config.Eur2Czk;
		Config.opex2CZ = Config.opex2 * Config.Eur2Czk;
		Config.opex3CZ = Config.opex3 * Config.Eur2Czk;
		Config.opexCZ = Config.opex * Config.Eur2Czk;
		Config.opexCZVAT = Config.opexCZ * (100 + Config.VAT)/100;
		execution.setVariable("opex1CZ", formatter.format(Config.opex1CZ));
		execution.setVariable("opex2CZ", formatter.format(Config.opex2CZ));
		execution.setVariable("opex3CZ", formatter.format(Config.opex3CZ));
		execution.setVariable("opexCZ", formatter.format(Config.opexCZ));
		execution.setVariable("opexCZVAT", formatter.format(Config.opexCZVAT));
		
		Config.capexopexCZ = Config.capexCZ + Config.opexCZ;
		Config.capexopexCZVAT = (Config.capexCZ + Config.opexCZ) * (100 + Config.VAT)/100;
		execution.setVariable("capexopexCZ", formatter.format(Config.capexopexCZ));
		execution.setVariable("capexopexCZVAT", formatter.format(Config.capexopexCZVAT));
	}
}