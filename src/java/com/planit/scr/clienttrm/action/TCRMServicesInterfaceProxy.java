package com.planit.scr.clienttrm.action;

public class TCRMServicesInterfaceProxy implements co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface
{
	private String _endpoint = null;
	private co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface tCRMServicesInterface = null;

	public TCRMServicesInterfaceProxy()
	{
		_initTCRMServicesInterfaceProxy();
	}

	public TCRMServicesInterfaceProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initTCRMServicesInterfaceProxy();
	}

	private void _initTCRMServicesInterfaceProxy()
	{
		try
		{
			tCRMServicesInterface = (new co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesWebServiceLocator()) .getTCRMServicesWebServicePort();
			if (tCRMServicesInterface != null)
			{
				if (_endpoint != null)
					((javax.xml.rpc.Stub) tCRMServicesInterface)._setProperty( "javax.xml.rpc.service.endpoint.address",_endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) tCRMServicesInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		}
		catch (javax.xml.rpc.ServiceException serviceException)
		{
		}
	}

	public String getEndpoint()
	{
		return _endpoint;
	}

	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;
		if (tCRMServicesInterface != null)
			((javax.xml.rpc.Stub) tCRMServicesInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface getTCRMServicesInterface()
	{
		if (tCRMServicesInterface == null)
			_initTCRMServicesInterfaceProxy();
		return tCRMServicesInterface;
	}

	public co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse queryTCRM( java.util.Calendar tcrmQueryAssociatedDate) throws java.rmi.RemoteException
	{
		if (tCRMServicesInterface == null)
			_initTCRMServicesInterfaceProxy();
		return tCRMServicesInterface.queryTCRM(tcrmQueryAssociatedDate);
	}

}