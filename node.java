//Represents nodes in a tree
class node
{
	protected node child[];
	protected int noc;
	protected node parent;
	char name;

	void addchild(node c)
	{
		child[noc++]=c;
	}

	void addparent(node p)
	{
		parent=p;
	}

}