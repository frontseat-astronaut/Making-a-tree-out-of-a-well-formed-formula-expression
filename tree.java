//The formula tree
import java.util.*;
class tree
{
	node root; 
	static int idx; // index of the expression String, the maketree function is currently at 
	HashSet<Character> P; // Set of propositions
	int p_count; // count of propositions
	public tree()
	{
		root=null;
		p_count=0;
		P= new HashSet<Character>();
	}
	public boolean isEmpty() // Check if the tree is empty
	{
		return root==null;
	}
	public node connect(node n1, node n2) //node 1 becomes parent of node 2
	{
		n1.addchild(n2);
		n2.addparent(n1);
		return n1;
	}
	public node maketree(String exp) // exp for expression
	{
		idx=1;
		p_count=0;
		P.clear();
		root=subtree(exp);
		return root;
	}
	private node subtree(String exp) // creates subtrees recursively
	{
		node alpha1, alpha2, alpha, op;

		//sub-formula can be of type (alpha1)op(alpha2) or -(alpha) [any of them can be propositions too] 
		if(exp.charAt(idx)=='(') 
		{
			idx++;
			alpha1=subtree(exp);
		}
		else if(exp.charAt(idx)=='-')
		{
			unary_op neg=new unary_op('-');
			idx++;
			if(exp.charAt(idx)=='(')
			{
				idx++;
				alpha=subtree(exp);
			}
			else
			{
				alpha=new prop(exp.charAt(idx++));
				p_count++;
				P.add(exp.charAt(idx-1));
			}
			connect(neg, alpha);
			idx++;
			return neg;
		}
		else 
		{
			alpha1=new prop(exp.charAt(idx++));
			p_count++;
			P.add(exp.charAt(idx-1));
		}
		op=new binary_op(exp.charAt(idx));
		idx++;
		if(exp.charAt(idx)=='(')
		{
			idx++;
			alpha2=subtree(exp);
		}
		else 
		{
			alpha2=new prop(exp.charAt(idx++));
			P.add(exp.charAt(idx-1));
			p_count++;
		}
		connect(op, alpha1);
		connect(op, alpha2);
		idx++;
		return op;
	}
	public void PrintPropositions()
	{
		System.out.println("Propostions used: ");
		System.out.println(P); 
	}
	public boolean Satisfy(node NODE, valuation v)
	{
		if(NODE.noc==0) //Proposition
		{
			return v.V.get(NODE.name);
		}
		else if(NODE.noc==1) //NOT operator
		{
			return !(Satisfy(NODE.child[0], v));
		}
		else
		{
			if(NODE.name=='^') //Conjuction
			{
				return (Satisfy(NODE.child[0], v)&Satisfy(NODE.child[1], v));
			}
			else if (NODE.name=='v') //Disjunction
			{
				return (Satisfy(NODE.child[0], v)|Satisfy(NODE.child[1], v));
			}
			else if (NODE.name=='>') //Implies
			{
				boolean left=Satisfy(NODE.child[0], v);
				boolean right=Satisfy(NODE.child[1], v);
				return ((!left)|right);
			}
			else if (NODE.name=='=') //Double implies
			{
				boolean left=Satisfy(NODE.child[0], v);
				boolean right=Satisfy(NODE.child[1], v);
				return (left==right);
			}
			else if (NODE.name=='+') //XOR
			{
				boolean left=Satisfy(NODE.child[0], v);
				boolean right=Satisfy(NODE.child[1], v);
				return (left^right);
			}

		}
		return false;
	}
	public Boolean Satisfiability()
	{
		valuation v=new valuation(P, false);
		Character[] vectP=new Character[p_count+1];
		int k=0;
		for(Character p:P)
		{
			vectP[k++]=p;
		}
		return CheckAllVal(v, vectP, 0);
	}
	private Boolean CheckAllVal(valuation v, Character vectP[], int i)
	{
		if(i==p_count)
		{
			return Satisfy(root, v);
		}
		Boolean issat;
		v.V.put(vectP[i], false);
		issat=CheckAllVal(v,vectP,i+1);
		if(issat)
			return true;
		v.V.put(vectP[i],true);
		issat=CheckAllVal(v,vectP,i+1);
		return issat;
	}
}