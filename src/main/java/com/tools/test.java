package com.tools;

import java.util.ArrayList;





public class test {

	
	public static boolean verifier(String name, ArrayList<String> list) {
		for (int i=0; i<list.size(); i++) {
			if(list.get(i).equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> Usernames = new ArrayList<String>();
	
		
		Usernames.add("Vi");
		Usernames.add("ArisLord");
		Usernames.add("Cappuchino");
		Usernames.add("Pinol");
		Usernames.add("Pinol1");
		Usernames.add("Pinol2");

		
		System.out.println( verifier("ArisLord", Usernames));
		System.out.println( Tools.HashPassword("LuzEmpire10"));
	
	}

}
