package com.planit.scr.clienttrm.action;

public interface TCRMServicesWebService extends javax.xml.rpc.Service
{
	public java.lang.String getTCRMServicesWebServicePortAddress();

	public co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface getTCRMServicesWebServicePort() throws javax.xml.rpc.ServiceException;

	public co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface getTCRMServicesWebServicePort( java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
