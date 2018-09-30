class unary_op extends node
{
	public unary_op(char name)
	{
		noc=0;
		child=new node[1];
		parent=null;
		this.name=name;
	}
}