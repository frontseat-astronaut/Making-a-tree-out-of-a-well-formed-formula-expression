class tree
{
	node root;
	prop[] P;
	int p_count;
	static int idx=0;
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
	public node create(node r)
	{
		root=r;
		return r;
	}
	public node connect(node n1, node n2)
	{
		n1.addchild(n2);
		n2.addparent(n1);
		if(root==n2)
			root=n1;
		return n1;
	}
	public node maketree(String exp)
	{
		node alpha1, alpha2, alpha, op;

		if(exp.charAt(idx)=='(')
		{
			idx++;
			alpha1=maketree(exp);
		}
		else if(exp.charAt(idx)=='-')
		{
			unary_op neg=new unary_op();
			idx++;
			if(exp.charAt(idx)=='(')
			{
				idx++;
				alpha=maketree(exp);
			}
			else
			{
				alpha=new prop(exp.charAt(idx++));
			}
			connect(neg, alpha);
			idx++;
			if(isEmpty())
			{
				create(neg);
			}
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
			alpha2=maketree(exp);
		}
		else 
		{
			alpha2=new prop(exp.charAt(idx++));
		}
		connect(op, alpha1);
		connect(op, alpha2);
		idx++;
		if(isEmpty())
		{
			create(op);
		}
		return op;
	}
}