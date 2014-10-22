/*   
     Development Task from Comcast
	 class DeveT
     By Yefu Zheng , at Oct/7th/14
*/
/*
  Implication 1:
  After reading the description, the first character of every line should not be space(' '),
  that means for every word except the last one, when we count its length to check whether it will 
  violate the maximum 13, we must count another space character included.

  Doubt1:  What if the length of single word is longer than 13 characters? 
     sol:  Divide the long word into several parts , print out for every 13 characters.
  Doubt2:  How to handle out the characters that are neither letters nor spaces?
     sol:  Generally, I consider to separate the sentence only by identify the space(' '), So the other syntaxes like , . ; : ' " ( ) 
	       will be considered as same as the letters. 
		   
  Algorithm:
           Create a function--Transfer(String s). Iterate all the characters in String. 
		   Using the int count to record the length of the current word, 
		         the StringBuffer to hold the current text line.
		   To identify whether a word is over by finding the space,all the words are separated by the spaces.
		   For Boundary Condition , keep the sense of the last char in the String , insert it into last text line and directly print out.
		   Reason has been mentioned in the below code part.
		   For regular Condition,
		    So if the current char is ' ': if inside the text line,just insert it into line and keep count; 
		                                  else if at the end, print the line; 
										  else at the first , don't insert, make the count to zero.
		      else the current char !=' ': if the word length is longer than 13, print the first 13, go the next as the normal step;
			                              else : if inside the line, insert into the line, increase the count;
										         else at the end, remove the characters from the (line length-count) to the last one, move the i pointer back to COUNT times,
												         print out the reduced text line.
										
   Test and Proceed:
          BufferedReader has been imported to better realizing the program(in main function). Once process starts, type in any String and enjoy it~
*/
import java.io.*;
import java.lang.String;
import java.lang.StringBuffer;

public class DevelopmentTask{
    
	//process of converting the input String into multiple text lines
	public static void Transfer(String s){
         
	  int count=0;//keep the record of the length of the current word
      
	  StringBuffer buf=new StringBuffer();//keep the current text line
	  
	  //using for loop to iterate every character in the given String s
	  for(int i=0;i<s.length();i++){
	     
		 /* when i represents the last character,since it can be any character(not only space),there will be two conditions
		    being the 13th or not.Even it is the 13th, we can still write it down no matter what the character is since it's the last one of the String.  */
		 if(i==s.length()-1){
		         buf.append(s.charAt(i));
				 System.out.println(buf.toString());//print out the last text line.
		 }
		 
		 //for the condition when current character is space(' ')
		 if(s.charAt(i)==' '){
		    //if it is in a new line,when the StringBuffer is empty        
			if(buf.length()==0){

        		  count=1;//just ignore this space since for every new line the first character must not be the space.
				  buf.append(' ');
			
			//if the current space occurs at the last one of the maximum length of the line,just print the this line
			}else if(buf.length()==12){
			
				  System.out.println(buf.toString());//print the current line
			      count=0;//current word is finished
				  buf=new StringBuffer();//renew the StringBuffer(text line)
			
			//if the current space occurs among the current text line
			}else{
			
			      count=1;//Here count should be 1 for the later checking and counting back if the new word cannot be fully filled into this line
                  buf.append(' ');//insert the current space into the text line				  
            }					 
		 
		 //for the condition when current character is not space
		 }else{
			/*
			  for the condition when the single word is longer than 13,
			  we just cut the word to several part, print them out for every 13 characters.	
			 */
			if(count==12){
			      buf.append(s.charAt(i));//insert the last char of the line
				  System.out.println(buf.toString());//print out the current line
				  buf=new StringBuffer();//renew the Buffer
				  count=0;//recount the new word
			
			}else{
			     //for the mostly common condition, current character among the text line
		         if(buf.length()<12){
			     
			          buf.append(s.charAt(i));//insert the current character into StringBuffer(current line)
				      count++;//count the length of the current word
					  
			     /* if the current character is the last one at the max length of the text line(13)
			     and I know this one is not the space(' '),that means the current unfinished word can't not be written in this line
			     must be removed */
				 }else{
			     		          
				      buf.delete(buf.length()-count,buf.length());//delete all characters belong to the current word
				 
				      i=i-count;//count back to the start of the current word
				      count=0;//word mark back to 0 , ready for the new word in new line 				 
				      System.out.println(buf.toString());//print out the current text line and move the cursor to the next line
				      buf=new StringBuffer();//renew the current StringBuffer(text line)				   
			     }
			}
		}
				 
		 
	  }
	  
	  
    }
	//main function, type the String ,use the BufferedReader to read the input and convert into the output
	public static void main(String[] arg) throws Exception{
     BufferedReader keybd = new BufferedReader(new InputStreamReader(System.in));
	 
	 String s=keybd.readLine(); 
	 
	 Transfer(s);//doing the process
	 }
}