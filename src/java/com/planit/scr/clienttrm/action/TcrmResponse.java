
package com.planit.scr.clienttrm.action;

/**
 * Business TCRM response 
 * @author Alex Vicente Chacon Jimenez (alex.chacon@software-colombia.com)
 *
 */
public class TcrmResponse extends co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.Tcrm implements java.io.Serializable
{
	
	private static final long serialVersionUID = 1L;

	private java.lang.String message;

	private java.lang.Boolean success;

	public TcrmResponse()
	{
	}

	public TcrmResponse(java.lang.Boolean displayToUser, java.lang.Long id, java.lang.String unit, java.util.Calendar validityFrom, java.util.Calendar validityTo, java.lang.Float value, java.lang.String message, java.lang.Boolean success)
	{
		super(displayToUser, id, unit, validityFrom, validityTo, value);
		this.message = message;
		this.success = success;
	}

	/**
	 * Gets the message value for this TcrmResponse.
	 * 
	 * @return message
	 */
	public java.lang.String getMessage()
	{
		return message;
	}

	/**
	 * Sets the message value for this TcrmResponse.
	 * 
	 * @param message
	 */
	public void setMessage(java.lang.String message)
	{
		this.message = message;
	}

	/**
	 * Gets the success value for this TcrmResponse.
	 * 
	 * @return success
	 */
	public java.lang.Boolean getSuccess()
	{
		return success;
	}

	/**
	 * Sets the success value for this TcrmResponse.
	 * 
	 * @param success
	 */
	public void setSuccess(java.lang.Boolean success)
	{
		this.success = success;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj)
	{
		if (obj == null)
			return false;
		
		if (!(obj instanceof TcrmResponse))
			return false;
		
		TcrmResponse other = (TcrmResponse) obj;
		
		if (this == obj)
			return true;
		if (__equalsCalc != null)
		{
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = super.equals(obj)
				&& ((this.message == null && other.getMessage() == null) || (this.message != null && this.message
						.equals(other.getMessage())))
				&& ((this.success == null && other.getSuccess() == null) || (this.success != null && this.success
						.equals(other.getSuccess())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode()
	{
		if (__hashCodeCalc)
		{
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = super.hashCode();
		if (getMessage() != null)
		{
			_hashCode += getMessage().hashCode();
		}
		if (getSuccess() != null)
		{
			_hashCode += getSuccess().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			TcrmResponse.class, true);

	static
	{
		typeDesc.setXmlType(new javax.xml.namespace.QName( "http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/", "tcrmResponse"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("message");
		elemField.setXmlName(new javax.xml.namespace.QName("", "message"));
		elemField.setXmlType(new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("success");
		elemField.setXmlName(new javax.xml.namespace.QName("", "success"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc()
	{
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer( java.lang.String mechType, java.lang.Class<?> _javaType, javax.xml.namespace.QName _xmlType)
	{
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer( java.lang.String mechType, java.lang.Class<?> _javaType, javax.xml.namespace.QName _xmlType)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
