class tree
{
	node root;
	prop[] P;
	int p_count;
	static int idx;
	public tree()
	{
		root=null;
		P=new prop[0];
		p_count=0;
	}
	public boolean isEmpty()
	{
		return root==null;
	}
	public node connect(node n1, node n2)
	{
		n1.addchild(n2);
		n2.addparent(n1);
		return n1;
	}
	public node maketree(String exp)
	{
		idx=1;
		root=subtree(exp);
		return root;
	}
	private node subtree(String exp)
	{
		node alpha1, alpha2, alpha, op;

		if(exp.charAt(idx)=='(')
		{
			idx++;
			alpha1=subtree(exp);
		}
		else if(exp.charAt(idx)=='-')
		{
			unary_op neg=new unary_op();
			idx++;
			if(exp.charAt(idx)=='(')
			{
				idx++;
				alpha=subtree(exp);
			}
			else
			{
				alpha=new prop(exp.charAt(idx++));
			}
			connect(neg, alpha);
			idx++;
			return neg;
		}
		else 
		{
			alpha1=new prop(exp.charAt(idx++));
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
		}
		connect(op, alpha1);
		connect(op, alpha2);
		idx++;
		return op;
	}
}