//The formula tree
class tree
{
	node root; 
	int p_count; // Count of the propositions present in the tree
	static int idx; // index of the expression String, the maketree function is currently at 
	public tree()
	{
		root=null;
		p_count=0;
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
			}
			connect(neg, alpha);
			idx++;
			return neg;
		}
		else 
		{
			alpha1=new prop(exp.charAt(idx++));
			p_count++;
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
			p_count++;
		}
		connect(op, alpha1);
		connect(op, alpha2);
		idx++;
		return op;
	}
}