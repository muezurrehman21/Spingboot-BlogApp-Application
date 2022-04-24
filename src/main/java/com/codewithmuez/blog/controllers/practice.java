package com.codewithmuez.blog.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class practice {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
////		try {
////		    Scanner sc=new Scanner(System.in);
////		    int t=sc.nextInt();
////		    while(t-->0)
////		    {
////		        int d=sc.nextInt();
////		        String s=sc.next();
////		        boolean found=false;
////		        for(int i=0;i<d;i++)
////		    {
////		        char ch=s.charAt(i);
////		        int num=Character.getNumericValue(ch);
////		        if(num==0 || num==5) found=true;
////		    }
////		         if(found) System.out.println("YES");
////		         else System.out.println("NO");
////		    }
////		} catch(Exception e) {
////		}  
//	}
	    public List<List<Integer>> main(String[] args) {
	    	int[] nums1 = {1,2,3}; int[] nums2= {2,4,5};
	        List<List<Integer>> result = new ArrayList<>();
	        for(int i=1; i<3; i++){
	            for(int j=1; j<3; j++){
	                if(nums1[i] == nums2[j]){
	                    result.get(1).add(nums1[i]);
	                }
	            }
	        }
	        // List<List<Integer>>
	        return result;
	    }
}
