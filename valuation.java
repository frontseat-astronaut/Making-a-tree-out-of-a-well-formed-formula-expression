import java.util.HashMap; 
import java.util.Map;
import java.util.Scanner;
public class valuation
{
	private HashMap<String, Boolean> V;

	valuation(String[] P, int pno)
	{
		V=new HashMap<String, Boolean>();
		for (int i=0;i<pno;++i) 
		{
			V.put(P[i],false);
		}
	}

	public void defineVal(String[] P, int pno)
	{
		Scanner sc=new Scanner (System.in);
		char v;
		for(int i=0; i<pno; ++i)
		{
			System.out.print("Enter valuation for "+P[i]+" (T/F): ");
			v= sc.next().charAt(0); 
			if(v=='T')
				V.replace(P[i],true);
		}
	}
}