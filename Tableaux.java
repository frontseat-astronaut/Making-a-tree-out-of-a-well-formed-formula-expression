import java.util.*;
class Tableaux
{
	Tableaux(node root)
	{
		HashSet<node>set1=new  HashSet<node>();
		set1.add(root);
		System.out.println("Satisfiable: "+Recurse(set1));
		HashSet<node>set2=new HashSet<node>();
		node neg=new unary_op('-');
		connect(neg,root);
		set2.add(neg);
		System.out.println("Valid: "+(!Recurse(set2)));
	}
	node connect(node n1, node n2)
	{
		n1.addchild(n2);
		n2.addparent(n1);
		return n1;
	}
	boolean Recurse(HashSet<node> s)
	{
		List<HashSet<Character>> lit = new ArrayList<HashSet<Character>>();
		for ( int i = 0; i < 2; i++ ) {
  			lit.add(new HashSet<Character>());
		}		
		for(node n: s)
		{
			node p=n;
			int cnt=0;
			while(p.noc==1)
			{
				cnt++;
				p=p.child[0];
			}
			if(p.noc==0)
			{
				if(lit.get((cnt+1)%2).contains(p.name))
					return false;
				lit.get((cnt)%2).add(p.name);
			}
		}

		for(node n: s) //looks for alpha formulas
		{
			if(n.noc==2)
			{
				if(n.name=='^')
				{
					s.add(n.child[0]);
					s.add(n.child[1]);
					s.remove(n);
					return Recurse(s);
				}
				if(n.name=='=')
				{
					node left=new binary_op('v');
					node right=new binary_op('v');
					node notp=new unary_op('-');
					node notq=new unary_op('-');

					connect(notp,n.child[0]);
					connect(notq,n.child[1]);
					connect(left,n.child[1]);
					connect(left,notp);
					connect(right,n.child[0]);
					connect(right,notq);

					s.add(left);
					s.add(right);
					s.remove(n);
					return Recurse(s);
				}
			}
			else if (n.noc==1)
			{
				if(n.child[0].noc==0)
					continue;
				if(n.child[0].name=='v')
				{
					node notp=new unary_op('-');
					node notq=new unary_op('-');
					connect(notp,n.child[0].child[0]);
					connect(notq,n.child[0].child[1]);
					s.add(notp);
					s.add(notq);
					s.remove(n);
					return Recurse(s);
				}
				if(n.child[0].name=='>')
				{
					node notq=new unary_op('-');
					connect(notq,n.child[0].child[1]);
					s.add(n.child[0].child[0]);
					s.add(notq);
					s.remove(n);
					return Recurse(s);
				}
			}
		}
		HashSet<node>s_=new HashSet<node>();
		s_=(HashSet)s.clone();
		for(node n:s) //looks for beta formulas
		{
			if(n.noc==2)
			{
				if(n.name=='v')
				{
					s_.add(n.child[0]);
					s.add(n.child[1]);
					s.remove(n);
					s_.remove(n);
					boolean left=Recurse(s_);
					boolean right=Recurse(s);
					return left|right;
				}
				if(n.name=='>')
				{
					node notp=new unary_op('-');
					connect(notp, n.child[0]);
					s.add(notp);
					s_.add(n.child[1]);
					s.remove(n);
					s_.remove(n);
					boolean left=Recurse(s_);
					boolean right=Recurse(s);
					return left|right;
				}
			}
			else if (n.noc==1)
			{
				if(n.child[0].name=='^')
				{
					node notp=new unary_op('-');
					node notq=new unary_op('-');
					connect(notp,n.child[0].child[0]);
					connect(notq,n.child[0].child[1]);
					s_.add(notp);
					s.add(notq);
					s.remove(n);
					s_.remove(n);
					boolean left=Recurse(s_);
					boolean right=Recurse(s);
					return left|right;
				}
				if(n.child[0].name=='=')
				{
					node left=new binary_op('^');
					node right=new binary_op('^');
					node notp=new unary_op('-');
					node notq=new unary_op('-');

					connect(notp,n.child[0].child[0]);
					connect(notq,n.child[0].child[1]);
					connect(left,n.child[0].child[1]);
					connect(left,notp);
					connect(right,n.child[0].child[0]);
					connect(right,notq);

					s.add(left);
					s_.add(right);
					s.remove(n);
					s_.remove(n);
					boolean L=Recurse(s_);
					boolean R=Recurse(s);
					return L|R;
				}
			}
		}
		return true;
	}
}