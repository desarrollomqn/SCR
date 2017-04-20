
package com.planit.scr.clienttrm.action;

/**
 * TCRM business class
 * @author Alex Vicente Chacon Jimenez (alex.chacon@software-colombia.com)
 */
public class Tcrm implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	private java.lang.Boolean displayToUser;

	private java.lang.Long id;

	private java.lang.String unit;

	private java.util.Calendar validityFrom;

	private java.util.Calendar validityTo;

	private java.lang.Float value;

	public Tcrm()
	{
		
	}

	public Tcrm(java.lang.Boolean displayToUser, java.lang.Long id, java.lang.String unit, java.util.Calendar validityFrom, java.util.Calendar validityTo, java.lang.Float value)
	{
		this.displayToUser = displayToUser;
		this.id = id;
		this.unit = unit;
		this.validityFrom = validityFrom;
		this.validityTo = validityTo;
		this.value = value;
	}

	/**
	 * Gets the displayToUser value for this Tcrm.
	 * 
	 * @return displayToUser
	 */
	public java.lang.Boolean getDisplayToUser()
	{
		return displayToUser;
	}

	/**
	 * Sets the displayToUser value for this Tcrm.
	 * 
	 * @param displayToUser
	 */
	public void setDisplayToUser(java.lang.Boolean displayToUser)
	{
		this.displayToUser = displayToUser;
	}

	/**
	 * Gets the id value for this Tcrm.
	 * 
	 * @return id
	 */
	public java.lang.Long getId()
	{
		return id;
	}

	/**
	 * Sets the id value for this Tcrm.
	 * 
	 * @param id
	 */
	public void setId(java.lang.Long id)
	{
		this.id = id;
	}

	/**
	 * Gets the unit value for this Tcrm.
	 * 
	 * @return unit
	 */
	public java.lang.String getUnit()
	{
		return unit;
	}

	/**
	 * Sets the unit value for this Tcrm.
	 * 
	 * @param unit
	 */
	public void setUnit(java.lang.String unit)
	{
		this.unit = unit;
	}

	/**
	 * Gets the validityFrom value for this Tcrm.
	 * 
	 * @return validityFrom
	 */
	public java.util.Calendar getValidityFrom()
	{
		return validityFrom;
	}

	/**
	 * Sets the validityFrom value for this Tcrm.
	 * 
	 * @param validityFrom
	 */
	public void setValidityFrom(java.util.Calendar validityFrom)
	{
		this.validityFrom = validityFrom;
	}

	/**
	 * Gets the validityTo value for this Tcrm.
	 * 
	 * @return validityTo
	 */
	public java.util.Calendar getValidityTo()
	{
		return validityTo;
	}

	/**
	 * Sets the validityTo value for this Tcrm.
	 * 
	 * @param validityTo
	 */
	public void setValidityTo(java.util.Calendar validityTo)
	{
		this.validityTo = validityTo;
	}

	/**
	 * Gets the value value for this Tcrm.
	 * 
	 * @return value
	 */
	public java.lang.Float getValue()
	{
		return value;
	}

	/**
	 * Sets the value value for this Tcrm.
	 * 
	 * @param value
	 */
	public void setValue(java.lang.Float value)
	{
		this.value = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj)
	{
		if (obj == null)
			return false;
		
		if (!(obj instanceof Tcrm))
			return false;
		
		Tcrm other = (Tcrm) obj;
		
		if (this == obj)
			return true;
		if (__equalsCalc != null)
		{
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true
				&& ((this.displayToUser == null && other.getDisplayToUser() == null) || (this.displayToUser != null && this.displayToUser
						.equals(other.getDisplayToUser())))
				&& ((this.id == null && other.getId() == null) || (this.id != null && this.id
						.equals(other.getId())))
				&& ((this.unit == null && other.getUnit() == null) || (this.unit != null && this.unit
						.equals(other.getUnit())))
				&& ((this.validityFrom == null && other.getValidityFrom() == null) || (this.validityFrom != null && this.validityFrom
						.equals(other.getValidityFrom())))
				&& ((this.validityTo == null && other.getValidityTo() == null) || (this.validityTo != null && this.validityTo
						.equals(other.getValidityTo())))
				&& ((this.value == null && other.getValue() == null) || (this.value != null && this.value
						.equals(other.getValue())));
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
		int _hashCode = 1;
		if (getDisplayToUser() != null)
		{
			_hashCode += getDisplayToUser().hashCode();
		}
		if (getId() != null)
		{
			_hashCode += getId().hashCode();
		}
		if (getUnit() != null)
		{
			_hashCode += getUnit().hashCode();
		}
		if (getValidityFrom() != null)
		{
			_hashCode += getValidityFrom().hashCode();
		}
		if (getValidityTo() != null)
		{
			_hashCode += getValidityTo().hashCode();
		}
		if (getValue() != null)
		{
			_hashCode += getValue().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc( Tcrm.class, true);

	static
	{
		typeDesc.setXmlType(new javax.xml.namespace.QName( "http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/", "tcrm"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("displayToUser");
		elemField.setXmlName(new javax.xml.namespace.QName("", "displayToUser"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("id");
		elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("unit");
		elemField.setXmlName(new javax.xml.namespace.QName("", "unit"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("validityFrom");
		elemField.setXmlName(new javax.xml.namespace.QName("", "validityFrom"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("validityTo");
		elemField.setXmlName(new javax.xml.namespace.QName("", "validityTo"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("value");
		elemField.setXmlName(new javax.xml.namespace.QName("", "value"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
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
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType, java.lang.Class<?> _javaType, javax.xml.namespace.QName _xmlType)
	{
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType,_xmlType, typeDesc);
	}

}
