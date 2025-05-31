//Basic calculator to perform basic operations( +,-,*,/ )
import java.util.*; 
class calculator
{
  static ArrayList<String> calc= new ArrayList<String>();
  static String div[]=new String[calc.size()];
  static String mul[]=new String[calc.size()];
  int i,j;//for number checking
  int ctr=0; // for 10 count
  String temp="";
  int x=0; double n1=0,n2=0,total=0;
  String ope="",temp2="";int error=0;
  Scanner sc= new Scanner(System.in);
  public static void main(String args[])
  {
    calculator c=new calculator();
    c.input();
  }
    
  void input()
  {
    Scanner sc= new Scanner(System.in); 
      while(ctr<10) //main loop
      { 
        heading();
        boolean numflag=true,opeflag=true;
        while(numflag)  //for valid input number
         {
            System.out.println( );
            System.out.println("-----------------------");
            System.out.println( );
            System.out.print("Enter number: ");
            temp=sc.nextLine();boolean numcheck=true;
            for(i=0;i<temp.length();i++) //to check number is digit based
            {
                if(Character.isDigit(temp.charAt(i)) || temp.charAt(i)=='.')
                  numcheck=true;
                else
                  numcheck=false;
            }
            if(numcheck==false)
            {
              temp="";
              heading();
              errmsg();
            }
            else
             {
              calc.add(temp);   
              numflag=false;
             }
         }
        while(opeflag)  //to check if the operator is valid
        {
            System.out.print("Enter operator: "); 
            temp=sc.nextLine();
            if(temp.equalsIgnoreCase("+") || temp.equalsIgnoreCase("-") || temp.equalsIgnoreCase("*") ||
               temp.equalsIgnoreCase("/"))
             {
              calc.add(temp);
              ctr++;temp="";
              heading();
              opeflag=false;
             }
            else if(temp.equalsIgnoreCase("="))
            {
              ctr=10;
              opeflag=false;
            }   
            else 
            {
               heading();
               errmsg();
               temp="";
               System.out.println("--------------------");
            } 
            
        }
      }
    heading();
    if(calc.size()==1)  // in case user input only one value and click equals to
    total=Double.valueOf(calc.get(0));
    else
    division();
    result();
  }
    
  void errmsg()  //to display error message in case of wrong input
  {
     System.out.println( );
     System.out.println("-----------------------");
     System.out.println("**INVALID INPUT**" );
     System.out.println("-----------------------");
     System.out.println( );
  }
    
  void heading()  //to display heading
  {
      System.out.println('\u000C');
      System.out.println("-----------------------");
      System.out.println("|  BASIC CALCULATOR   |");
      System.out.println("| (Maximum 10 inputs) |");
      System.out.println("-----------------------");
      System.out.println( );
      if(ctr>0)
        System.out.println(calc);
       
  }
     
  //to calculate according to BEDMAS rule
    
  void division()  //to calculate all divison part
  { 
    String div[]=new String[calc.size()];
      for(i=0;i<calc.size();i+=2)
      {
          n1=Double.valueOf(calc.get(i));
          if(temp2.equalsIgnoreCase("/"))
          {
              n1=total;
              temp2="";
          }
          if((i+1)>calc.size()-1 || (i+2)>calc.size()-1)
          {break;}
          ope=(calc.get(i+1));
          n2=Double.valueOf(calc.get(i+2));
          if(ope.equalsIgnoreCase("/"))
          {
              if(n2==0)
              {error=1;break;}
              total=n1/n2;
              temp2="/";
              div[x]=String.valueOf(total);
          }
          else
          {div[x++]=String.valueOf(n1); //entering the answer after divion along with other no.s and operators in div array
           div[x++]=ope;
           div[x]=String.valueOf(n2);
          }
      }
      if(error!=1) //to not perform further if no. is divided by zero
       multiply(div);
  }
    
  // to perform all multiplications after division has been completed
  void multiply(String div[])
  {   
    String mul[]=new String[calc.size()];
    ope="";temp2="";total=0;x=0;
    for(i=0;i<calc.size();i+=2)
    {
       if((i+1>calc.size()-1)|| (i+2>calc.size()-1)|| div[i+1]==null || div[i+2]==null)
        {break;}          
        n1=Double.valueOf(div[i]);
       if(temp2.equalsIgnoreCase("*"))
         {
            n1=total;
            temp2="";
         }
        ope=div[i+1];
        n2=Double.valueOf(div[i+2]);
       if(ope.equalsIgnoreCase("*"))
        {
           total=n1*n2;
           temp2="*";
           mul[x]=String.valueOf(total);
        }
        else
        {mul[x++]=String.valueOf(n1); //entering the answer after multiplication along with other no.s and operators in mul array
         mul[x++]=ope;
         mul[x]=String.valueOf(n2);
          
        }
    }
    sumdiff(mul);
  }
    
  // the mul array now consists of only addition and subtraction operation
  void sumdiff(String mul[])
  {
     total=Double.valueOf(mul[0]);
      for(i=0;i<calc.size();i+=2)
      {  if((i+1>calc.size()-1)|| (i+2>calc.size()-1)|| mul[i+1]==null || mul[i+2]==null)
          {break;} 
         n1=total;
         ope=mul[i+1];
         n2=Double.valueOf(mul[i+2]);
         if(ope.equalsIgnoreCase("+"))
         total=n1+n2;
         else
         total=n1-n2;
      }
  } 
     
  void result()
  {
    if(error==1) //this will show that divion by zero is not possible
    {
      System.out.println( );
      System.out.println("Division by zero not possible");
    }
    else
    {
     System.out.println( );
     System.out.println("-----------------------");
     System.out.println("   Result : "+ total);
     System.out.println("-----------------------");
    }
  }
}
    
       
