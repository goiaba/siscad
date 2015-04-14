package br.com.r2jb.siscad.ws.tempuri;

public class WSCepSoapProxy implements WSCepSoap {

    private String _endpoint = null;
    private WSCepSoap wscepSoap = null;

    public WSCepSoapProxy() {
        _initWscepSoapProxy();
    }

    public WSCepSoapProxy(String endpoint) {
        _endpoint = endpoint;
        _initWscepSoapProxy();
    }

    private void _initWscepSoapProxy() {
        try {
            wscepSoap = (new WSCepLocator()).getWSCepSoap();
            if (wscepSoap != null) {
                if (_endpoint != null) {
                    ((javax.xml.rpc.Stub) wscepSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
                } else {
                    _endpoint = (String) ((javax.xml.rpc.Stub) wscepSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
                }
            }

        } catch (javax.xml.rpc.ServiceException serviceException) {
        }
    }

    public String getEndpoint() {
        return _endpoint;
    }

    public void setEndpoint(String endpoint) {
        _endpoint = endpoint;
        if (wscepSoap != null) {
            ((javax.xml.rpc.Stub) wscepSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        }

    }

    public WSCepSoap getWscepSoap() {
        if (wscepSoap == null) {
            _initWscepSoapProxy();
        }
        return wscepSoap;
    }

    public CepResponseCepResult cep(java.lang.String strcep) throws java.rmi.RemoteException {
        if (wscepSoap == null) {
            _initWscepSoapProxy();
        }
        return wscepSoap.cep(strcep);
    }
}
