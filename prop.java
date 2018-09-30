//Proposition
class prop extends node
{
	private boolean val;
	public prop(char name)
	{
		parent=null;
		this.name=name;
	}
	public void setv(boolean v)
	{
		val=v;
	}
}