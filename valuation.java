import java.util.*;
public class valuation
{
	private HashMap<Character, Boolean> V;

	valuation(HashSet<Character> P, int pno)
	{
		V=new HashMap<Character, Boolean>();
		Scanner sc=new Scanner (System.in);
		char v;
		for(Character p: P)
		{
			System.out.print("Enter valuation for "+p+" (T/F): ");
			v= sc.next().charAt(0); 
			if(v=='T')
				V.put(p,true);
			else
				V.put(p,false);
		}
	}

	void Display()
	{
		System.out.println(V);
	}
}