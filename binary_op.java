class binary_op extends node
{
	public binary_op(char op)
	{
		name=op;
		noc=0;
		child=new node[2];
		parent=null;
	}

}