import java.util.Scanner;
class main
{
	public static void main(String argvs[])
	{
		Scanner sc=new Scanner (System.in);

		String exp="(p^p)V(p^p)";
		tree T=new tree();
		T.maketree(exp);
		System.out.print(T.root.name);
	}
}