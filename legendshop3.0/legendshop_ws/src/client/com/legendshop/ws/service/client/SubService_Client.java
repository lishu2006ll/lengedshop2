
package com.legendshop.ws.service.client;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.net.URL;

import javax.xml.namespace.QName;

import com.legendshop.ws.service.sub.SubService;
import com.legendshop.ws.service.sub.SubService_Service;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2012-08-06T22:58:21.304+08:00
 * Generated source version: 2.6.1
 * 
 */
public final class SubService_Client {

    private static final QName SERVICE_NAME = new QName("http://sub.service.ws.legendshop.com", "SubService");

    public SubService_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
    	SubService_Client client = new SubService_Client();
        
        {
        System.out.println("Invoking exportOrderService...");
        com.legendshop.ws.service.sub.Sub sub = new com.legendshop.ws.service.sub.Sub();
       
        System.out.println("exportOrderService.result=" + client.exportOrderService(sub));


        }

        System.exit(0);
    }
    
    public String exportOrderService(com.legendshop.ws.service.sub.Sub sub){
        URL wsdlURL = SubService_Service.WSDL_LOCATION;
        SubService_Service ss = new SubService_Service(wsdlURL, SERVICE_NAME);
        SubService port = ss.getSubServicePort();  
        String result = port.exportOrderService(sub);
    	return result;
    }

}
