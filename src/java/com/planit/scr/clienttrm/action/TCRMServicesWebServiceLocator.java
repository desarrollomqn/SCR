package com.planit.scr.clienttrm.action;

public class TCRMServicesWebServiceLocator extends org.apache.axis.client.Service implements TCRMServicesWebService
{

	private static final long serialVersionUID = 1L;

	public TCRMServicesWebServiceLocator()
	{
	}

	public TCRMServicesWebServiceLocator(org.apache.axis.EngineConfiguration config)
	{
		super(config);
	}

	public TCRMServicesWebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException
	{
		super(wsdlLoc, sName);
	}

	private java.lang.String TCRMServicesWebServicePort_address = "http://AlexChacon:8080/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService";

	public java.lang.String getTCRMServicesWebServicePortAddress()
	{
		return TCRMServicesWebServicePort_address;
	}

	private java.lang.String TCRMServicesWebServicePortWSDDServiceName = "TCRMServicesWebServicePort";

	public java.lang.String getTCRMServicesWebServicePortWSDDServiceName()
	{
		return TCRMServicesWebServicePortWSDDServiceName;
	}

	public void setTCRMServicesWebServicePortWSDDServiceName(java.lang.String name)
	{
		TCRMServicesWebServicePortWSDDServiceName = name;
	}

	public co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface getTCRMServicesWebServicePort()throws javax.xml.rpc.ServiceException
	{
		java.net.URL endpoint;
		try
		{
			endpoint = new java.net.URL(TCRMServicesWebServicePort_address);
		}
		catch (java.net.MalformedURLException e)
		{
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getTCRMServicesWebServicePort(endpoint);
	}

	public co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface getTCRMServicesWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException
	{
		try
		{
			co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesWebServiceSoapBindingStub _stub = new co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesWebServiceSoapBindingStub(portAddress, this);
			_stub.setPortName(getTCRMServicesWebServicePortWSDDServiceName());
			return _stub;
		}
		catch (org.apache.axis.AxisFault e)
		{
			return null;
		}
	}

	public void setTCRMServicesWebServicePortEndpointAddress(java.lang.String address)
	{
		TCRMServicesWebServicePort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException
	{
		try
		{
			if (co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface.class.isAssignableFrom(serviceEndpointInterface))
			{
				co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesWebServiceSoapBindingStub _stub = new co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesWebServiceSoapBindingStub(new java.net.URL(TCRMServicesWebServicePort_address),this);
				_stub.setPortName(getTCRMServicesWebServicePortWSDDServiceName());
				return _stub;
			}
		}
		catch (java.lang.Throwable t)
		{
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException( "There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException
	{
		if (portName == null)
		{
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("TCRMServicesWebServicePort".equals(inputPortName))
		{
			return getTCRMServicesWebServicePort();
		}
		else
		{
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName()
	{
		return new javax.xml.namespace.QName( "http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/", "TCRMServicesWebService");
	}

	private java.util.HashSet<javax.xml.namespace.QName> ports = null;

	public java.util.Iterator<javax.xml.namespace.QName> getPorts()
	{
		if (ports == null)
		{
			ports = new java.util.HashSet<javax.xml.namespace.QName>();
			ports.add(new javax.xml.namespace.QName( "http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/", "TCRMServicesWebServicePort"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName,java.lang.String address) throws javax.xml.rpc.ServiceException
	{

		if ("TCRMServicesWebServicePort".equals(portName))
		{
			setTCRMServicesWebServicePortEndpointAddress(address);
		}
		else
		{ // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName,java.lang.String address) throws javax.xml.rpc.ServiceException
	{
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
