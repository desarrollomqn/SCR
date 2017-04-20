package com.planit.scr.clienttrm.action;

public interface TCRMServicesInterface extends java.rmi.Remote 
{
    public co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse queryTCRM(java.util.Calendar tcrmQueryAssociatedDate) throws java.rmi.RemoteException;
}
