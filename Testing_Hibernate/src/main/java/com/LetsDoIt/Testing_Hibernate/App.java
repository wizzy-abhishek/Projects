package com.LetsDoIt.Testing_Hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.confirmation_dao.AccountConfirmation;
import com.internalWork.Dao.Connecting_Buddy;
import com.pojo.CredInfo_internal;
import com.pojo.RealInfo;

public class App 
{
    public static void main( String[] args )throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Enter your user name");
        String uId = br.readLine();
        System.out.println("Enter your password");
        String password = br.readLine();
        
        RealInfo realinfo = new RealInfo();
    	CredInfo_internal credinfo_internal = new CredInfo_internal();
    	
		
		/*
		 * try {
		 * 
		 * if(AccountConfirmation.verify(uId , password)) { System.out.println("Hello "
		 * + uId); }else { System.out.println("Invalid"); } } catch (Exception e) {
		 * 
		 * e.printStackTrace(); }
		 */
		 
    	
    	System.out.print(new Connecting_Buddy().fetching(uId));
    }
}
