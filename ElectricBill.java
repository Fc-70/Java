import java.util.*;

class ElectricBill{
public static void main(String[] args){
double a1=0;

Scanner n1=new Scanner(System.in);
System.out.println("Enter consumer no:");
int d1=n1.nextInt();

Scanner s=new Scanner(System.in);
System.out.print("Enter name:");
String n=s.nextLine();

System.out.println("1.Domestic\n2.Commercial\nEnter(1/2):");
int d2=n1.nextInt();

System.out.println("Enter current reading:");
int b1=n1.nextInt();
System.out.println("Enter previous reading:");
int b2=n1.nextInt();
double a=b1-b2;
if(d2==1){

if(a<=100){
a1=a*1;
}else if(a>100 && a<=200){
a1=a*2.50;
}else if(a>200 && a<=500){
a1=a*4;
}else if(a>500){
a1=a*6;
}
System.out.print("Domestic Bill amount:"+a1);

}
else if(d2==2){
if(a<=100){
a1=a*2;
}else if(a>100 && a<=200){
a1=a*4.50;
}else if(a>200 && a<=500){
a1=a*6;
}else if(a>500){
a1=a*7;
}
System.out.print("CommercialBill amount:"+a1);
}
else{
System.out.print("Invalid choice");
}
}
}

