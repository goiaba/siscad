/**
 * WscepLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package br.com.r2jb.siscad.ws.tempuri;

import java.util.HashSet;

public class WSCepLocator extends org.apache.axis.client.Service implements WSCep {

    private HashSet ports = null;
    private String wscepSoapWSDDServiceName = "wscepSoap";
    private String wscepSoap_address = "http://www.bronzebusiness.com.br/webservices/wscep.asmx";

    public WSCepLocator() {}

    public WSCepLocator(org.apache.axis.EngineConfiguration config) {

        super(config);

    }

    public WSCepLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {

        super(wsdlLoc, sName);

    }

    @Override
    public java.lang.String getWSCepSoapAddress() {

        return wscepSoap_address;

    }

    public java.lang.String getwscepSoapWSDDServiceName() {

        return wscepSoapWSDDServiceName;

    }

    public void setwscepSoapWSDDServiceName(java.lang.String name) {

        wscepSoapWSDDServiceName = name;

    }

    @Override
    public WSCepSoap getWSCepSoap() throws javax.xml.rpc.ServiceException {

        java.net.URL endpoint;

        try {

            endpoint = new java.net.URL(wscepSoap_address);

        } catch (java.net.MalformedURLException e) {

            throw new javax.xml.rpc.ServiceException(e);

        }

        return getWSCepSoap(endpoint);

    }

    @Override
    public WSCepSoap getWSCepSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {

        try {

            WSCepSoapStub _stub = new WSCepSoapStub(portAddress, this);

            _stub.setPortName(getwscepSoapWSDDServiceName());

            return _stub;

        } catch (org.apache.axis.AxisFault e) {

            return null;

        }
        
    }

    public void setwscepSoapEndpointAddress(java.lang.String address) {
        wscepSoap_address = address;
    }

    @Override
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {

        try {

            if (WSCepSoap.class.isAssignableFrom(serviceEndpointInterface)) {

                WSCepSoapStub _stub = new WSCepSoapStub(new java.net.URL(wscepSoap_address), this);
                _stub.setPortName(getwscepSoapWSDDServiceName());

                return _stub;

            }

        } catch (java.lang.Throwable t) {

            throw new javax.xml.rpc.ServiceException(t);

        }

        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));

    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        
        if (portName == null) {

            return getPort(serviceEndpointInterface);

        }

        String inputPortName = portName.getLocalPart();

        if ("wscepSoap".equals(inputPortName)) {

            return getWSCepSoap();

        } else {

            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);

            return _stub;

        }
        
    }

    @Override
    public javax.xml.namespace.QName getServiceName() {

        return new javax.xml.namespace.QName("http://tempuri.org/", "wscep");

    }


    @Override
    public java.util.Iterator getPorts() {

        if (ports == null) {

            ports = new java.util.HashSet();

            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "wscepSoap"));

        }

        return ports.iterator();

    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, String address) throws javax.xml.rpc.ServiceException {

        if ("wscepSoap".equals(portName)) {

            setwscepSoapEndpointAddress(address);

        } else { // Unknown Port Name

            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);

        }

    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {

        setEndpointAddress(portName.getLocalPart(), address);
        
    }
}
