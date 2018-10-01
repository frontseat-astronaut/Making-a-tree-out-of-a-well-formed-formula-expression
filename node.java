//Represents nodes in a tree
class node
{
	public node child[];
	public int noc;
	public node parent;
	public Character name;

	void addchild(node c)
	{
		child[noc++]=c;
	}

	void addparent(node p)
	{
		parent=p;
	}

}